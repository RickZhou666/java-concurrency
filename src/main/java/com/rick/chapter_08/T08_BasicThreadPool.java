package com.rick.chapter_08;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Rick
 * @Date: 2022/10/5 00:00
 */
public class T08_BasicThreadPool extends Thread implements T01_ThreadPool {
    // 初始化线程数量
    private final int initSize;
    // 线程池最大线程数量
    private final int maxSize;
    // 线程池核心线程数量
    private final int coreSize;
    // 当前活跃的线程数量
    private int activeCount;

    // 创建线程所需的工厂
    private final T03_ThreadFactory threadFactory;

    // 任务队列
    private final T02_RunnableQueue runnableQueue;

    // 线程池是否已经被shutdown
    private volatile boolean isShutdown = false;

    // 工作线程队列
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();

    private final static T04_DenyPolicy DEFAULT_DENY_POLICY = new T04_DenyPolicy.DiscardDenyPolicy();

    private final static T03_ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    private final long keepAliveTime;

    private final TimeUnit timeUnit;

    public T08_BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
        this(initSize, maxSize, coreSize, DEFAULT_THREAD_FACTORY,
                queueSize, DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
    }

    // 构造线程池时需要传入的参数，该构造函数参数比较多
    public T08_BasicThreadPool(int initSize, int maxSize, int coreSize,
                               T03_ThreadFactory threadFactory, int queueSize,
                               T04_DenyPolicy denyPolicy, long keepAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new T07_LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    // 初始化时，先创建initSize个线程
    private void init() {
        start();
        for (int i = 0; i < initSize; i++) {
            newThread();
        }
    }

    private void newThread() {
        T06_InternalTask internalTask = new T06_InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadQueue.offer(threadTask);
        this.activeCount++;
        thread.start();
    }

    private void removeThread() {
        // 从线程池中移除某个线程
        ThreadTask threadTask = threadQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }

    @Override
    public void execute(Runnable runnable) {
        if (this.isShutdown)
            throw new IllegalStateException("The thread pool is destroy");
        // 提交任务只是简单地网任务队列中插入Runnable
        this.runnableQueue.offer(runnable);
    }

    @Override
    public void run() {
        // run方法继承来自Thread，主要用于维护线程数量，比如扩容、回收等工作
        while (!isShutdown && !isInterrupted()) {
            try {
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                isShutdown = true;
                break;
            }
            synchronized (this) {
                if (isShutdown)
                    break;

                // 当前队列中有任务尚未处理，并且activeCount < coreSize则继续扩容
                if (runnableQueue.size() > 0 && activeCount < coreSize) {
                    for (int i = initSize; i < coreSize; i++) {
                        newThread();
                    }
                    // continue 的目的在于不想让线程的扩容直接达到maxsize
                    continue;
                }
                // 当前队列中有任务尚未处理，并且activeCount < maxSize则继续扩容
                if (runnableQueue.size() > 0 && activeCount < maxSize) {
                    for (int i = coreSize; i < maxSize; i++) {
                        newThread();
                    }
                }

                // 如果任务队列中没有任务，则需要回收，回收至coreSize即可
                if (runnableQueue.size() == 0 && activeCount > coreSize) {
                    for (int i = coreSize; i < activeCount; i++) {
                        removeThread();
                    }
                }
            }
        }
    }

    private static class ThreadTask {
        Thread thread;
        T06_InternalTask internalTask;

        public ThreadTask(Thread thread, T06_InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }

    @Override
    public void shutdown() {
        synchronized (this) {
            if (isShutdown) return;
            isShutdown = true;
            threadQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });
            this.interrupt();
        }
    }

    @Override
    public int getInitSize() {
        if (isShutdown)
            throw new IllegalStateException("The thread pool is destroy");
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if (isShutdown)
            throw new IllegalStateException("The thread pool is destroy");
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        if (isShutdown)
            throw new IllegalStateException("The thread pool is destroy");
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if (isShutdown)
            throw new IllegalStateException("The thread pool is destroy");
        return runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        synchronized (this){
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }

    private static class DefaultThreadFactory implements T03_ThreadFactory{
        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);
        private static final ThreadGroup group = new ThreadGroup("MyThreadPool-" + GROUP_COUNTER.getAndDecrement());
        private static final AtomicInteger COUNTER = new AtomicInteger(0);
        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(group, runnable, "thread-pool" + COUNTER.getAndDecrement());
        }
    }

}

package com.rick.chapter_08;

import java.util.LinkedList;

/**
 * @Author: Rick
 * @Date: 2022/10/4 23:46
 */
public class T07_LinkedRunnableQueue implements T02_RunnableQueue {
    // 任务队列的最大容量，在构造时传入
    private final int limit;

    // 若任务队列中的任务已经满了，则需要执行决绝策略
    private final T04_DenyPolicy denyPolicy;

    // 存放任务的队列
    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    private final T01_ThreadPool threadPool;

    public T07_LinkedRunnableQueue(int limit, T04_DenyPolicy denyPolicy, T01_ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList) {
            if (runnableList.size() >= limit) {
                // 无法容纳新的任务时执行拒绝策略
                denyPolicy.reject(runnable, threadPool);
            } else {
                // 将任务加入队尾，并且唤醒阻塞中的线程
                runnableList.addLast(runnable);
                runnableList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnableList) {
            while (runnableList.isEmpty()) {
                try {
                    // 如果任务队列中没有可执行任务，则当前线程将会挂起，进入runnableList关联的monitor waitset中等待唤醒（新的任务加入）
                    runnableList.wait();
                } catch (InterruptedException e) {
                    // 被中断时需要将该异常抛出
                    throw e;
                }
            }
            // 从任务队列头部一处一个任务
            return runnableList.removeFirst();
        }
    }

    @Override
    public int size() {
        synchronized (runnableList){
            // 返回当前任务队列中的任务数
            return runnableList.size();
        }
    }
}

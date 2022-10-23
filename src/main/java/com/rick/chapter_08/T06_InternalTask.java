package com.rick.chapter_08;

/**
 * @Author: Rick
 * @Date: 2022/10/4 23:36
 */
public class T06_InternalTask implements Runnable {

    private final T02_RunnableQueue runnableQueue;
    private volatile boolean running = true;

    public T06_InternalTask(T02_RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        // 如果当前任务为running并且没有被中断，则其将不断地从queue中获取runnable，然后执行run方法
        while (running && !Thread.currentThread().isInterrupted()){
            try {
                Runnable task = runnableQueue.take();
                task.run();
            } catch (InterruptedException e) {
                running = false;
                break;
            }
        }
    }

    // 停止当前任务，主要会在线程池的shutdown方法中使用
    public void stop(){
        this.running = false;
    }
}

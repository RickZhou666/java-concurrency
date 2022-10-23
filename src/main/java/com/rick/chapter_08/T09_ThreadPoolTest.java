package com.rick.chapter_08;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/5 00:36
 */
public class T09_ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        // 定义线程池，初始化线程数为2，
        //           核心线程数为4，
        //           最大线程数为6，
        //           任务队列最多允许1000个任务
        final T01_ThreadPool threadPool = new T08_BasicThreadPool(2, 6, 4, 1000);

        // 定义20个任务并且提交给线程池
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                try {
                    // TimeUnit.SECONDS.sleep(10);
                    TimeUnit.MILLISECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + " is running and done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // for (; ; ) {
            // 不断输出线程池的信息
            // System.out.println("getActiveCount : " + threadPool.getActiveCount());
            // System.out.println("getQueueSize : " + threadPool.getQueueSize());
            // System.out.println("getCoreSize: " + threadPool.getCoreSize());
            // System.out.println("getMaxSize: " + threadPool.getMaxSize());
            // System.out.println("=======================================================");
            // TimeUnit.SECONDS.sleep(5);
        // }

        TimeUnit.SECONDS.sleep(12);
        threadPool.shutdown();
        Thread.currentThread().join();
    }
}

package com.rick.chapter_08;

/**
 * @Author: Rick
 * @Date: 2022/10/4 23:25
 */
public interface T01_ThreadPool {

    // 提交任务到线程池
    void execute(Runnable runnable);

    // 关闭线程池
    void shutdown();

    // 获取线程池的初始化大小
    int getInitSize();

    // 获取线程池最大的线程数
    int getMaxSize();

    // 获取线程池的核心线程数量
    int getCoreSize();

    // 获取线程池中用于缓存任务队列的大小
    int getQueueSize();

    // 获取线程池中活跃线程的数量
    int getActiveCount();

    // 查看线程池是否已经被shutdown
    boolean isShutdown();
}

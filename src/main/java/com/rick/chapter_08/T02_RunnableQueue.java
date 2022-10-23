package com.rick.chapter_08;

/**
 * @Author: Rick
 * @Date: 2022/10/4 23:28
 */
// 任务队列，主要用于缓存提交到线程池中的任务
public interface T02_RunnableQueue {
    // 当有新的任务进来时首先回offer到队列中
    void offer(Runnable runnable);

    // 工作线程通过take方法获取runnable
    Runnable take() throws InterruptedException;

    // 获取任务队列中任务的数量
    int size();
}

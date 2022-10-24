package com.rick.chapter_17;

/**
 * @Author: Rick
 * @Date: 2022/10/24 10:38
 */
public interface Lock {

    // 获取显示锁，没有获得锁的线程将被阻塞
    void lock() throws InterruptedException;

    // 释放获得的锁
    void unlock();
}

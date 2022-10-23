package com.rick.chapter_05;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @Author: Rick
 * @Date: 2022/10/4 10:18
 */
public interface T07_Lock {

    void lock() throws InterruptedException;
    void lock(long mills) throws InterruptedException, TimeoutException;
    void unlock();
    List<Thread> getBlockedThreads();
}

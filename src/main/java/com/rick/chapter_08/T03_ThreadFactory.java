package com.rick.chapter_08;

/**
 * @Author: Rick
 * @Date: 2022/10/4 23:30
 */
@FunctionalInterface
public interface T03_ThreadFactory {
    Thread createThread(Runnable runnable);
}

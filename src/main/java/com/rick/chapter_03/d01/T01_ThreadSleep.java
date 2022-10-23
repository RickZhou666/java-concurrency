package com.rick.chapter_03.d01;

import sun.java2d.loops.TransformHelper;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/2 07:14
 */
public class T01_ThreadSleep {
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            sleep(2_000L);
        }).start();

        long startTime = System.currentTimeMillis();
        sleep(3_000L);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("Main thread total spend %d ms", (endTime - startTime)));

        // 都使用 TimeUnit
        Thread.sleep(12257088L);
        TimeUnit.HOURS.sleep(3);
        TimeUnit.MINUTES.sleep(24);
        TimeUnit.SECONDS.sleep(17);
        TimeUnit.MILLISECONDS.sleep(88);
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }
}

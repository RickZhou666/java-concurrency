package com.rick.chapter_07;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/4 21:59
 */
public class T01_CaptureThreadException {
    public static void main(String[] args) {
        // 1. 设置回调接口
        // lambda 传入了一个 UncaughtExceptionHandler 对象
        // void uncaughtException(Thread t, Throwable e);
        // 在匿名内部类里的实现
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + " occur exception");
            e.printStackTrace();
        });
        final Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
            // 2. 这里会出现unchecked异常
            // here will throw unchecked exception
            System.out.println(1 / 0);
        }, "Test-Thread");

        thread.start();
    }
}

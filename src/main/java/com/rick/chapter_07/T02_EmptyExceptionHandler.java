package com.rick.chapter_07;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/4 22:15
 */
public class T02_EmptyExceptionHandler {
    public static void main(String[] args) {
        // get current thread's thread group
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println(mainGroup.getName());
        System.out.println(mainGroup.getParent());
        System.out.println(mainGroup.getParent().getParent());


        // Runnable 接口的实现
        // public abstract void run();
        // 因为override的方法没有入参，所以为 () -> {}
        final Thread thread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
            // here will throw unchecked exception.
            System.out.println(1 / 0);
        }, "Test-Thread");

        thread.start();
    }
}

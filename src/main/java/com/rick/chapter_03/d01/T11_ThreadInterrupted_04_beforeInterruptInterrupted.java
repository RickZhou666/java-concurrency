package com.rick.chapter_03.d01;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/3 11:27
 */
public class T11_ThreadInterrupted_04_beforeInterruptInterrupted {
    public static void main(String[] args) {
        // 1. 判断当前线程是否被中断
        System.out.println("Main thread is interrupted? " + Thread.interrupted()); // .interrupted() 擦除interrupt标识，让其变成非interrupt状态

        // 2. 中断当前线程
        Thread.currentThread().interrupt();
        // 3. 判断当前线程是否已经中断              .isInterrupted() 不会擦出interrupt标识，保持interrupt状态
        System.out.println("Main thread is interrupted? " + Thread.currentThread().isInterrupted());
        // System.out.println("Main thread is interrupted? " + Thread.interrupted());

        try {
            // 4. 当前线程执行可中断方法
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            // 5. 捕获中断信号
            System.out.println("I will be interrupted still.");
        }
    }
}

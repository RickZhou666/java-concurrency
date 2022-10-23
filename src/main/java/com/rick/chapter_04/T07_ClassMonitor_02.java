package com.rick.chapter_04;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * @Author: Rick
 * @Date: 2022/10/4 00:50
 */
public class T07_ClassMonitor_02 {
    public static synchronized void method1() {
        System.out.println(currentThread().getName() + " enter to method1");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void method2() {
        synchronized (T07_ClassMonitor_02.class) {
            System.out.println(currentThread().getName() + " enter to method2");
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(T07_ClassMonitor_02::method1, "T1").start();
        new Thread(T07_ClassMonitor_02::method2, "T2").start();
    }
}

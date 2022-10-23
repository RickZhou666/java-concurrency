package com.rick.chapter_04;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * @Author: Rick
 * @Date: 2022/10/4 00:47
 */
public class T06_ClassMonitor {
    public static synchronized void method1() {
        System.out.println(currentThread().getName() + " enter to method1");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void method2() {
        System.out.println(currentThread().getName() + " enter to method2");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(T06_ClassMonitor::method1, "T1").start();
        new Thread(T06_ClassMonitor::method2, "T2").start();
    }
}

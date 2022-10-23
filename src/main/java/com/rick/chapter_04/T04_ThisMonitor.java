package com.rick.chapter_04;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * @Author: Rick
 * @Date: 2022/10/4 00:34
 */
public class T04_ThisMonitor {
    public synchronized void method1(){
        System.out.println(currentThread().getName() + " enter to method1");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void method2(){
        System.out.println(currentThread().getName() + " enter to method2");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        T04_ThisMonitor thisMonitor = new T04_ThisMonitor();
        new Thread(thisMonitor:: method1, "T1").start();
        new Thread(thisMonitor:: method2, "T2").start();
    }
}

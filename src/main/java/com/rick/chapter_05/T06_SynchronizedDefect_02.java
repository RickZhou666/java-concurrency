package com.rick.chapter_05;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/4 10:08
 */
public class T06_SynchronizedDefect_02 {

    public synchronized void syncMethod(){
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T06_SynchronizedDefect_02 defect = new T06_SynchronizedDefect_02();
        Thread t1 = new Thread(defect::syncMethod, "T1");
        // make sure the t1 started
        t1.start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(defect::syncMethod, "T2");
        t2.start();
        // make sure the t2 started.
        TimeUnit.MILLISECONDS.sleep(2);
        t2.interrupt();
        // t1.interrupt();
        System.out.println(t2.isInterrupted());
        System.out.println(t2.getState());
    }
}

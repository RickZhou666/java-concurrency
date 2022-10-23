package com.rick.chapter_05;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/4 10:03
 */
public class T05_SynchronizedDefect {
    public synchronized void syncMethod(){
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T05_SynchronizedDefect defect = new T05_SynchronizedDefect();
        Thread t1 = new Thread(defect::syncMethod, "T1");
        // make sure the t1 started
        t1.start();
        TimeUnit.MILLISECONDS.sleep(2);

        Thread t2 = new Thread(defect::syncMethod, "T2");
        t2.start();
    }
}

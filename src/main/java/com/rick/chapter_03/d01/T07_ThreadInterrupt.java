package com.rick.chapter_03.d01;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/3 08:00
 */
public class T07_ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->
        {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Oh, i am be interrupted");
            }
        });
        thread.start();

        // short block and make sure thread is started
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
    }
}

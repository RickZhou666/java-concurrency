package com.rick.chapter_03.d01;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/3 21:31
 */
public class T17_InterruptThreadExit_02 {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread("Rick-Thread-01"){
            // 匿名内部类
            @Override
            public void run() {
                System.out.println("I will start work");
                for (;;){
                    // System.out.println("working");
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                System.out.println("I will be exiting");
            }
        };

        t.start();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("System will be shutdown.");
        t.interrupt();
    }
}

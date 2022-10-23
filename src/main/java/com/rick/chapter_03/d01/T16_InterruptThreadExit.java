package com.rick.chapter_03.d01;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/3 21:21
 */
public class T16_InterruptThreadExit {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(){
            // 匿名内部类
            @Override
            public void run() {
                // super.run();
                System.out.println("I will start work");
                while (!isInterrupted()){
                    // System.out.println("working");
                }
                System.out.println("I will be exiting");
            }
        };

        // Thread t2 = new Thread(()->{
        //     System.out.println("Thread constructor anonymous class");
        // }){
        //     @Override
        //     public void run() {
        //         System.out.println("Thread inside class anonymous class");
        //     }
        // };
        // t2.start();

        t.start();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("System will be shutdown.");
        t.interrupt();
    }
}

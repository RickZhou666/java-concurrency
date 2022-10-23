package com.rick.chapter_03.d01;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/3 11:21
 */
public class T10_ThreadInterrupted_03 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true){
                    System.out.println(Thread.interrupted());
                }
            }
        };
        thread.setDaemon(true);
        thread.start();

        // shortly block make sure the thread is started
        TimeUnit.NANOSECONDS.sleep(2);
        thread.interrupt();
    }
}

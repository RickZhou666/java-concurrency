package com.rick.chapter_03.d01;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/3 08:09
 */
public class T08_ThreadisInterrupted {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true){
                    // do nothing, just empty loop
                }
            }
        };

        // 将thread设置为daemon线程，这样当main结束后，因为JVM中没有非守护线程，process也随之结束
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted? %s\n", thread.isInterrupted());
        thread.interrupt();
        System.out.printf("Thread is interrupted? %s\n", thread.isInterrupted());
    }
}

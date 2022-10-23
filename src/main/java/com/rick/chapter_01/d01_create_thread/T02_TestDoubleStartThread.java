package com.rick.chapter_01.d01_create_thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/9/26 23:44
 */
public class T02_TestDoubleStartThread {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        thread.start();
    }
}

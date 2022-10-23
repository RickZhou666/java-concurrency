package com.rick.chapter_01.d01_create_thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/9/26 23:47
 */
public class T03_TestThreadLifeCycleEnd {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();

        TimeUnit.SECONDS.sleep(2); // 休眠主要是为了确保thread生命周期结束

        thread.start(); // 企图重新激活该线程
    }
}

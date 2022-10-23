package com.rick.chapter_04;

import sun.awt.Mutex;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/4 00:06
 */
public class T03_Mutex {
    private final static Object MUTEX = new Object();

    public void accessResource(){
        synchronized (MUTEX){
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final T03_Mutex mutex = new T03_Mutex();
        for (int i = 0; i < 5; i++) {
            new Thread(mutex::accessResource).start();
        }
        
    }
}

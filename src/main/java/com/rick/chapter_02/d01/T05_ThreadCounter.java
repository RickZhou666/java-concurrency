package com.rick.chapter_02.d01;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Rick
 * @Date: 2022/10/2 06:18
 */
public class T05_ThreadCounter extends Thread {
    final static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        try {
            while (true) {
                new T05_ThreadCounter().start();
            }
        } catch (Throwable e) {
            System.out.println("failed At=> " + counter.get());
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("The " + counter.getAndIncrement() + " thread be created.");
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

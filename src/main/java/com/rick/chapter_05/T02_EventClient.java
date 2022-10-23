package com.rick.chapter_05;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/4 09:14
 */
public class T02_EventClient {
    public static void main(String[] args) {
        final T01_EventQueue eventQueue = new T01_EventQueue();
        new Thread(() -> {
            for (; ; ) {
                eventQueue.offer(new T01_EventQueue.Event());
            }
        }, "Producer").start();

        new Thread(() -> {
            for (; ; ) {
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer").start();
    }
}

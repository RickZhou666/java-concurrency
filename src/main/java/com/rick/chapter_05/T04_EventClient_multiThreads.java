package com.rick.chapter_05;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/4 09:49
 */
public class T04_EventClient_multiThreads {

    public static void main(String[] args) {
        final T01_EventQueue eventQueue = new T01_EventQueue();

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                for (; ; ) {
                    eventQueue.offer(new T01_EventQueue.Event());
                }
            }, "Producer-0" + i).start();
        }

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                for (; ; ) {
                    eventQueue.take();
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "Consumer-0" + i).start();
        }
    }
}

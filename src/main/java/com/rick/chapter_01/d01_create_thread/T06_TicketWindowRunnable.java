package com.rick.chapter_01.d01_create_thread;

import java.sql.SQLOutput;

/**
 * @Author: Rick
 * @Date: 2022/9/27 00:20
 */
public class T06_TicketWindowRunnable implements Runnable {
    private static final int MAX = 50;

    private int index = 1; // 不做static修饰

    @Override
    public void run() {
        while (index < MAX) {
            System.out.println(Thread.currentThread() + " 的号码是：" + (index++));

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final T06_TicketWindowRunnable task = new T06_TicketWindowRunnable();
        Thread windowThread1 = new Thread(task, "一号窗口");
        Thread windowThread2 = new Thread(task, "二号窗口");
        Thread windowThread3 = new Thread(task, "三号窗口");
        Thread windowThread4 = new Thread(task, "四号窗口");

        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
        windowThread4.start();
    }
}

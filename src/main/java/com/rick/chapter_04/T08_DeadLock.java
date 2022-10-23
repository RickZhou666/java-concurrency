package com.rick.chapter_04;

import static java.lang.Thread.currentThread;

/**
 * @Author: Rick
 * @Date: 2022/10/4 00:58
 */
public class T08_DeadLock {
    private final Object MUTEX_READ = new Object();
    private final Object MUTEX_WRITE = new Object();

    public void read() {
        synchronized (MUTEX_READ) {
            System.out.println(currentThread().getName() + " get READ LOCK");
            synchronized (MUTEX_WRITE) {
                System.out.println(currentThread().getName() + " get WRITE LOCK");
            }
            System.out.println(currentThread().getName() + " release WRITE LOCK");
        }
        System.out.println(currentThread().getName() + " release READ LOCK");
    }

    public void write() {
        synchronized (MUTEX_WRITE) {
            System.out.println(currentThread().getName() + " get WRITE LOCK");
            synchronized (MUTEX_READ) {
                System.out.println(currentThread().getName() + " get READ LOCK");
            }
            System.out.println(currentThread().getName() + " release READ LOCK");
        }
        System.out.println(currentThread().getName() + " release WRITE LOCK");
    }

    public static void main(String[] args) {
        final T08_DeadLock deadLock = new T08_DeadLock();
        new Thread(()-> {
            while (true){
                deadLock.read();
            }
        }, "READ-THREAD").start();

        new Thread(()-> {
            while (true){
                deadLock.write();
            }
        }, "WRITE-THREAD").start();
    }
}

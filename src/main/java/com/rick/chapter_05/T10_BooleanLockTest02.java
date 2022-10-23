package com.rick.chapter_05;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @Author: Rick
 * @Date: 2022/10/4 10:42
 */
public class T10_BooleanLockTest02 {
    // 定义BooleanLock
    private final T07_Lock lock = new T08_BooleanLock();

    // 使用try...finally 语句块确保lock每次都能被正确释放
    public void syncMethod() {
        // 加锁
        try {
            lock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            int randomInt = current().nextInt(10);
            System.out.println(currentThread() + " get the lock. " +
                    "and sleep " + randomInt + " ms.");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T10_BooleanLockTest02 blt = new T10_BooleanLockTest02();
        new Thread(blt::syncMethod, "T1").start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(blt::syncMethod, "T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(2);
        t2.interrupt();
    }
}

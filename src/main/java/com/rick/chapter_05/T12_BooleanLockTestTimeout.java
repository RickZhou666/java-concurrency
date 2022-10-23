package com.rick.chapter_05;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @Author: Rick
 * @Date: 2022/10/4 12:33
 */
public class T12_BooleanLockTestTimeout {
    private final T07_Lock lock = new T08_BooleanLock();

    public void syncMethodTimeoutable() {
        try {
            lock.lock(1000);
            int randomInt = current().nextInt(10);
            System.out.println(currentThread() + " get the lock.");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T12_BooleanLockTestTimeout blt = new T12_BooleanLockTestTimeout();
        new Thread(blt::syncMethodTimeoutable, "T1").start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(blt::syncMethodTimeoutable, "T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(10);
    }
}

package com.rick.chapter_05;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @Author: Rick
 * @Date: 2022/10/4 10:42
 */
public class T09_BooleanLockTest {
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
            System.out.println(currentThread() + " get the lock.");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T09_BooleanLockTest blt = new T09_BooleanLockTest();
        // 定义一个线程并启动
        IntStream.range(0, 10)
                .mapToObj(i -> new Thread(blt::syncMethod))
                .forEach(Thread::start);
    }
}

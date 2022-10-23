package com.rick.chapter_05;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

/**
 * @Author: Rick
 * @Date: 2022/10/4 10:20
 */
public class T08_BooleanLock implements T07_Lock {

    private Thread currentThread;

    private boolean locked = false;

    private final List<Thread> blockedList = new ArrayList<>();


    @Override
    public void lock() throws InterruptedException {
        // 锁住这个方法，表示同一时间只能有一个线程访问这个方法
        synchronized (this) {
            while (locked) {
                if (!blockedList.contains(currentThread()))
                    blockedList.add(currentThread());
                this.wait();
            }
            blockedList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (mills <= 0) {
                this.lock();
            } else {
                long remainingMills = mills;
                long endMillis = currentTimeMillis() + remainingMills;
                while (locked) {
                    if (remainingMills <= 0)
                        throw new TimeoutException("can not get the lock during " + mills + " ms.");

                    if (!blockedList.contains(currentThread()))
                        blockedList.add(currentThread());

                    this.wait(remainingMills);
                    remainingMills = endMillis - currentTimeMillis();
                }
                blockedList.remove(currentThread());
                this.locked = true;
                this.currentThread = currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if (currentThread == currentThread()) {
                this.locked = false;
                Optional.of(currentThread().getName() + " release the lock.")
                        .ifPresent(System.out::println);
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        // return blockedList;
        return Collections.unmodifiableList(blockedList);
    }
}

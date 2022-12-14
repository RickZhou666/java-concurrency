package com.rick.chapter_17;

/**
 * @Author: Rick
 * @Date: 2022/10/24 10:55
 */
// ReadLock设计为包可见
class ReadLock implements Lock {
    private final ReadWriteLockImpl readWriteLock;

    public ReadLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        // 使用Mutex作为锁
        synchronized (readWriteLock.getMutex()) {
            // 若自持有线程正在进行写操作，或者有写线程在等待并且偏向写锁的标识为true时，
            // 就会无法获得读锁，只能被挂起
            while (readWriteLock.getWritingWriters() > 0
                    || (readWriteLock.getPreferWriter()
                        && readWriteLock.getWaitingWriters() > 0)){
                readWriteLock.getMutex().wait();
            }
            // 成功获得读锁，并且使readingReaders的数量增加
            readWriteLock.incrementReadingReaders();
        }
    }

    @Override
    public void unlock() {
        // 使用Mutex作为锁
        synchronized (readWriteLock.getMutex()) {
            // 释放锁的过程就是使得当前reading的数量减一
            // 将preferWriter设置为true，可以使得writer线程获得更多的机会
            // 通知唤醒与Mutex关联monitor waitset中的线程
            readWriteLock.decrementReadingReaders();
            readWriteLock.changePrefer(true);
            readWriteLock.getMutex().notifyAll();
        }
    }
}

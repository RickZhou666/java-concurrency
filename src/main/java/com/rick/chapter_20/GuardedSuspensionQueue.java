package com.rick.chapter_20;

import java.util.LinkedList;

/**
 * @Author: Rick
 * @Date: 2022/10/24 18:29
 */
public class GuardedSuspensionQueue {
    // 定义存放Integer类型的queue
    private final LinkedList<Integer> queue = new LinkedList<>();

    // 定义queue的最大容量为100
    private final int LIMIT = 100;

    // 往queue中插入数据，如果queue中的元素超过了最大容量，则会陷入阻塞
    public void offer(Integer data) throws InterruptedException {
        synchronized (this) {
            // 判断queue的当前元素是否超过了LIMIT
            while (queue.size() >= LIMIT) {
                System.out.println("====================Queue IS full====================");
                // 挂起当前线程，使其陷入阻塞
                this.wait();
            }
            //插入元素并且唤醒take线程
            queue.addLast(data);
            this.notifyAll();
        }
    }

    public Integer take() throws InterruptedException {
        synchronized (this) {
            // 判断如果队列为空
            while (queue.isEmpty()) {
                System.out.println("====================Queue IS empty====================");
                // 则挂起当前线程
                this.wait();
            }

            // 通知offer线程可以继续插入数据了
            this.notifyAll();
            return queue.removeFirst();
        }

    }
}

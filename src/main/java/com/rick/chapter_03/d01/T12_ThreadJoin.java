package com.rick.chapter_03.d01;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @Author: Rick
 * @Date: 2022/10/3 20:05
 */
public class T12_ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        // 1. 定义两个线程，并保存在threads中
        List<Thread> threads = IntStream.range(1, 3)
                .mapToObj(T12_ThreadJoin::create)
                .collect(toList());

        // 2. 启动这两个线程
        threads.forEach(Thread::start);

        // 3. 执行这两个线程的join方法
        for(Thread thread : threads){
            thread.join();
        }

        // 4. main线程循环输出
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "#" + i);
            shortSleep();
        }

        for(Thread thread : threads){
            thread.join();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "#" + i);
            shortSleep();
        }
    }

    // 构造一个简单的线程，每个线程只是简单的循环输出
    private static Thread create(int seq) {
        // public Thread(Runnable target, String name) {}
        // 这里定义的是一个 runnable target
        // 当thread.start()时，就会运行下面的for循环
        return new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "#" + i);
                shortSleep();
            }
        }, String.valueOf(seq));
    }

    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

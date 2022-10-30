package com.rick.chapter_20;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.currentThread;

/**
 * @Author: Rick
 * @Date: 2022/10/24 18:33
 */
public class GuardedSuspensionQueueTest {
    private static String PRODUCER_THREAD = "RICK_PRODUCER_THREAD-";
    private static String CONSUMER_THREAD = "RICK_CONSUMER_THREAD-";

    private static AtomicInteger nexProducerCounter = new AtomicInteger(0);
    private static AtomicInteger nexConsumerCounter = new AtomicInteger(0);

    private static GuardedSuspensionQueue queue = new GuardedSuspensionQueue();

    private static Random random = new Random();

    public static void main(String[] args) {
        // producer
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        int input = random.nextInt(20);
                        queue.offer(input);
                        System.out.println(currentThread() + " is offering: " + input);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    slowlyForProducer();
                }
            }, PRODUCER_THREAD + nexProducerCounter.getAndDecrement()).start();
        }
        // consumer
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        Integer output = queue.take();
                        System.out.println(currentThread() + " is taking: " + output);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    slowlyForConsumer();
                }
            }, CONSUMER_THREAD + nexConsumerCounter.getAndDecrement()).start();
        }
    }

    private static void slowlyForProducer() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void slowlyForConsumer() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.rick.chapter_18;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Author: Rick
 * @Date: 2022/10/24 16:00
 */
public class T03_IntegerAccumulator_ThreadSafe {

    private int init;

    public T03_IntegerAccumulator_ThreadSafe(int init) {
        this.init = init;
    }

    public int add(int i) {
        this.init += i;
        return this.init;
    }

    public int getValue() {
        return this.init;
    }


    public static void main(String[] args) {
        T03_IntegerAccumulator_ThreadSafe accumulator = new T03_IntegerAccumulator_ThreadSafe(0);
        IntStream.range(0, 3).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
                int oldValue;
                int result;
                // 使用class实例作为同步锁
                synchronized (T03_IntegerAccumulator_ThreadSafe.class) {
                    oldValue = accumulator.getValue();
                    result = accumulator.add(inc);
                }
                System.out.println(oldValue + " + " + inc + " = " + result);
                if (inc + oldValue != result) {
                    System.err.println("ERROR:" + oldValue + " + " + inc + " = " + result);
                }
                inc++;
                slowly();
            }
        }).start());
    }

    private static void slowly() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

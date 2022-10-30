package com.rick.chapter_18;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Author: Rick
 * @Date: 2022/10/24 15:41
 */
public class T02_IntegerAccumulator_ThreadUnsafe {
    private int init;

    public T02_IntegerAccumulator_ThreadUnsafe(int init) {
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
        T02_IntegerAccumulator_ThreadUnsafe accumulator = new T02_IntegerAccumulator_ThreadUnsafe(0);
        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
                // 首先获得old value
                int oldValue = accumulator.getValue();
                // 然后调用add方法计算
                int result = accumulator.add(inc);
                System.out.println(oldValue + " + " + inc + " = " + result);
                // 经过验证
                if (inc + oldValue != result) {
                    System.err.println("ERROR:" + oldValue + " + " + inc + " = " + result);
                }
                inc++;
                // 模拟延迟
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

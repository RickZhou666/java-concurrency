package com.rick.chapter_18;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Author: Rick
 * @Date: 2022/10/24 16:10
 */
// 不可变对象不允许被继承
public final class T04_IntegerAccumulator_ImmutableObject {
    // 不允许线程对init进行修改，一旦再构造器中被赋值后将不再改变
    private final int init;

    // 构造时传入初始值
    public T04_IntegerAccumulator_ImmutableObject(int init) {
        this.init = init;

    }

    // 构造新的累加器，需要用到另外一个accumulator和初始值
    public T04_IntegerAccumulator_ImmutableObject(T04_IntegerAccumulator_ImmutableObject accumulator, int init) {
        this.init = accumulator.getValue() + init;
    }

    // 每次相加都会产生一个新的IntegerAccumulator
    public T04_IntegerAccumulator_ImmutableObject add(int i) {
        return new T04_IntegerAccumulator_ImmutableObject(this, i);
    }

    public int getValue() {
        return this.init;
    }

    public static void main(String[] args) {
        T04_IntegerAccumulator_ImmutableObject accumulator = new T04_IntegerAccumulator_ImmutableObject(0);
        IntStream.range(0, 3).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
                int oldValue = accumulator.getValue();
                int result = accumulator.add(inc).getValue();
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
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

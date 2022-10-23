package com.rick.chapter_02.d01;

import java.util.stream.IntStream;

/**
 * @Author: Rick
 * @Date: 2022/10/2 04:56
 */
public class T01_ThreadConstructor {
    public static void main(String[] args) {
        IntStream.range(0, 5).boxed().map(i -> new Thread(
                () -> System.out.println(Thread.currentThread().getName()))
        ).forEach(Thread::start);
    }
}

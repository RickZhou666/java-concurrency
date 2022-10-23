package com.rick.chapter_02.d01;

import java.util.stream.IntStream;

/**
 * @Author: Rick
 * @Date: 2022/10/2 05:03
 */
public class T02_nameYourThread {
    private final static String PREFIX = "ALEX_";

    public static void main(String[] args) {
        IntStream.range(0, 5).mapToObj(T02_nameYourThread::createThread)
                .forEach(Thread::start);
    }

    private static Thread createThread(final int intName) {
        return new Thread(
                () -> System.out.println(Thread.currentThread().getName())
                , PREFIX + intName);
    }
}

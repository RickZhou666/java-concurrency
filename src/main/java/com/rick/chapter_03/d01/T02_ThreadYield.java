package com.rick.chapter_03.d01;

import java.util.stream.IntStream;

/**
 * @Author: Rick
 * @Date: 2022/10/2 07:23
 */
public class T02_ThreadYield {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            IntStream.range(0,2).mapToObj(T02_ThreadYield::create)
                    .forEach(Thread::start);
        }


    }

    private static Thread create(int index) {
        return new Thread(()->
        {
            // 注释部分
            // if (index == 0)
            //     Thread.yield();
            System.out.println(index);
        });
    }
}

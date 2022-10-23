package com.rick.chapter_03.d01;

/**
 * @Author: Rick
 * @Date: 2022/10/2 07:30
 */
public class T03_ThreadPriority {
    public static void main(String[] args) {
        Thread t1 = new Thread(() ->
        {
            while (true) {
                System.out.println("t1");
            }
        });

        t1.setPriority(3);


        Thread t2 = new Thread(() ->
        {
            while (true) {
                System.out.println("t2");
            }
        });

        t1.setPriority(10);

        t1.start();
        t2.start();
    }
}

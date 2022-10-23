package com.rick.chapter_03.d01;

/**
 * @Author: Rick
 * @Date: 2022/10/2 07:43
 */
public class T05_ThreadPriority_03 {
    public static void main(String[] args) {

        Thread t1 = new Thread();
        System.out.println("t1 priority " + t1.getPriority() + ", thread ID " + t1.getId());

        Thread t2 = new Thread(() -> {
            Thread t3 = new Thread();
            System.out.println("t3 priority " + t3.getPriority()+ ", thread ID " + t3.getId());
        });

        t2.setPriority(6);
        t2.start();
        System.out.println("t2 priority " + t2.getPriority()+ ", thread ID " + t2.getId());
    }
}

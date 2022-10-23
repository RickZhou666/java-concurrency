package com.rick.chapter_03.d01;

/**
 * @Author: Rick
 * @Date: 2022/10/2 07:39
 */
public class T04_ThreadPriority_02 {
    public static void main(String[] args) {
        // 定义一个线程组
        ThreadGroup group = new ThreadGroup("test");
        // 将线程组的优先级指定为7
        group.setMaxPriority(7);
        // 定义一个线程，将该线程加入到group中
        Thread thread = new Thread(group, "test-thread");
        // 企图将线程的优先级设定为10
        thread.setPriority(10);
        // 企图未遂
        System.out.println(thread.getPriority());
    }
}

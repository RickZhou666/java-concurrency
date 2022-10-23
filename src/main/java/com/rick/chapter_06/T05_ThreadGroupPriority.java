package com.rick.chapter_06;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/4 16:55
 */
public class T05_ThreadGroupPriority {
    public static void main(String[] args) {
        /*
         * Create a thread group and thread.
         */

        ThreadGroup group = new ThreadGroup("group1");
        Thread thread = new Thread(group, ()->{
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread");
        thread.setDaemon(true);
        thread.start();

        System.out.println("group.getMaxPriority() = " + group.getMaxPriority());

        System.out.println("thread.getPriority() = " + thread.getPriority());

        // 改变group的最大优先级
        group.setMaxPriority(3);

        System.out.println("group.getMaxPriority() = " + group.getMaxPriority());

        System.out.println("thread.getPriority() = " + thread.getPriority());
    }
}

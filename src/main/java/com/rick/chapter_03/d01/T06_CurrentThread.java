package com.rick.chapter_03.d01;

/**
 * @Author: Rick
 * @Date: 2022/10/2 07:48
 */
public class T06_CurrentThread {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                // always true
                System.out.println(Thread.currentThread() == this);
            }
        };
        thread.start();
        System.out.println(thread.getContextClassLoader());

        String name = Thread.currentThread().getName();
        System.out.println("main".equals(name));
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}

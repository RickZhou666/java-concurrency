package com.rick.chapter_07;

import org.omg.SendingContext.RunTime;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/4 22:26
 */
public class T03_ThreadHook {
    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread(){
            // 匿名内部类
            @Override
            public void run() {
                try {
                    System.out.println("The hook thread 1 is running.");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The hook thread 1 will exit.");
            }
        });

        // 钩子线程可注册多个
        Runtime.getRuntime().addShutdownHook(new Thread(){
            // 匿名内部类
            @Override
            public void run() {
                try {
                    System.out.println("The hook thread 2 is running.");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The hook thread 2 will exit.");
            }
        });
        System.out.println("The program is stopping");
    }
}

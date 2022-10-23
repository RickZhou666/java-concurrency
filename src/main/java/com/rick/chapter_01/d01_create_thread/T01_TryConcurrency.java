package com.rick.chapter_01.d01_create_thread;


import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/9/24 11:06
 */
public class T01_TryConcurrency {
    public static void main(String[] args) {

        // 通过匿名内部类的方式创建线程，并且重写其中的run方法
        // new Thread(){
        //     @Override
        //     public void run() {
        //         enjoyMusic();
        //     }
        // }.start();
        new Thread(T01_TryConcurrency::enjoyMusic).start();
        browseNews();

    }

    /**
     * Browse the latest news
     */
    private static void browseNews() {
        for (; ; ) {
            System.out.println("Uh-huh, the good news.");
            sleep(1);
        }
    }

    /**
     * Listening and enjoy the music
     */

    private static void enjoyMusic() {
        for (; ; ) {
            System.out.println("Uh-huh, the nice music.");
            sleep(1);
        }
    }

    /**
     * simulate the wait and ignore exception
     *
     * @param seconds
     */
    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

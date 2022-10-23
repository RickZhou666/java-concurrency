package com.rick.chapter_15;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/23 22:54
 */
public class ObservableThreadTest {
    public static void main(String[] args) {
        Observable observableThread = new ObservableThread<>(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" finished done.");
            return null;
        });
        observableThread.start();
    }
}

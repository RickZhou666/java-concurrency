package com.rick.chapter_15;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/23 22:54
 */
public class ObservableThreadTest2 {
    public static void main(String[] args) {
        final TaskLifecycle<String> lifecycle = new TaskLifecycle.EmptyLifecycle<String>() {
            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("====In finish phase====");
                System.out.println("The result is " + result);
            }

            @Override
            public void onStart(Thread thread) {
                System.out.println("====In start phase====");
            }

            @Override
            public void onRunning(Thread thread) {
                System.out.println("====In running phase====");
            }

            @Override
            public void onError(Thread thread, Exception e) {
                System.out.println("====In error phase====");
            }
        };

        Observable observableThread = new ObservableThread<>(lifecycle, ()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" finished done.");
            return "Hello Observer";
        });

        observableThread.start();
    }
}

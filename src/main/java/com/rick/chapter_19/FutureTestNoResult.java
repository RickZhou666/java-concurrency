package com.rick.chapter_19;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/24 17:04
 */
public class FutureTestNoResult {
    public static void main(String[] args) throws InterruptedException {
        // 定义不需要返回值的FutureService
        FutureService<Void, Void> service = FutureService.newService();
        // submit方法为立即返回的方法
        Future<?> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am finish done.");
        });
        // get方法会使当前线程进入阻塞
        future.get();
    }
}

package com.rick.chapter_19;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/24 17:17
 */
public class FutureTestCallback {
    public static void main(String[] args) throws InterruptedException {
        // 定义有返回值的FutureService
        FutureService<String, Integer> service = FutureService.newService();
        service.submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input.length();
        }, "Hello", System.out::println);
    }
}

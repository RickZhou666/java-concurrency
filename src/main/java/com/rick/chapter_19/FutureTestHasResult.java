package com.rick.chapter_19;

import com.sun.java.swing.plaf.windows.WindowsDesktopPaneUI;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/24 17:06
 */
public class FutureTestHasResult {
    public static void main(String[] args) throws InterruptedException {
        // 定义有返回值的FutureService
        FutureService<String, Integer> service = FutureService.newService();
        // submit方法会立即返回
        Future<Integer> future = service.submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input.length();
        }, "Hello");
        // get方法会使当前线程进入阻塞
        System.out.println(future.get());
    }
}

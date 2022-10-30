package com.rick.chapter_19;

/**
 * @Author: Rick
 * @Date: 2022/10/24 16:35
 */
public interface Future<T> {
    // 返回计算后的结果，该方法会陷入阻塞状态
    T get() throws InterruptedException;

    // 判断任务是否已经被执行完成
    boolean done();
}

package com.rick.chapter_19;

/**
 * @Author: Rick
 * @Date: 2022/10/24 17:14
 */
public interface Callback <T>{
    // 任务完成后会调用该方法，其中T为任务执行后的结果
    void call(T t);
}

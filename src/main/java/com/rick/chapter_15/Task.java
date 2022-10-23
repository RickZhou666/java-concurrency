package com.rick.chapter_15;

/**
 * @Author: Rick
 * @Date: 2022/10/23 22:41
 */
@FunctionalInterface
public interface Task <T>{

    // 任务执行接口，该接口允许有返回值
    T call();
}

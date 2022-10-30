package com.rick.chapter_19;

/**
 * @Author: Rick
 * @Date: 2022/10/24 16:36
 */
@FunctionalInterface
public interface Task <IN, OUT>{
    OUT get(IN input);
}

package com.rick.chapter_14;

/**
 * @Author: Rick
 * @Date: 2022/10/23 21:04
 */
// final不允许被继承
public final class T02_Singleton_lazy {
    // 实例变量
    private byte[] bytes = new byte[1024];

    // 定义实例，但是不直接初始化
    private static T02_Singleton_lazy instance = null;

    private T02_Singleton_lazy() {
    }


    public static T02_Singleton_lazy getInstance() {
        if (instance == null) {
            instance = new T02_Singleton_lazy();
        }
        return instance;
    }
}

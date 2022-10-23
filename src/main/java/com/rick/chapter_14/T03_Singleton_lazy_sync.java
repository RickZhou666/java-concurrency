package com.rick.chapter_14;

/**
 * @Author: Rick
 * @Date: 2022/10/23 21:06
 */
// final不允许被继承
public final class T03_Singleton_lazy_sync {

    // 实例变量
    private byte[] bytes = new byte[1024];

    // 定义实例，但是不直接初始化
    private static T03_Singleton_lazy_sync instance = null;

    private T03_Singleton_lazy_sync() {
    }

    // 向getInstance方法加入同步控制，每次只能有一个线程能够进入
    public static synchronized T03_Singleton_lazy_sync getInstance() {
        if (instance == null) {
            instance = new T03_Singleton_lazy_sync();
        }
        return instance;
    }
}

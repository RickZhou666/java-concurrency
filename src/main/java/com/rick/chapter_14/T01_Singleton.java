package com.rick.chapter_14;

/**
 * @Author: Rick
 * @Date: 2022/10/18 22:42
 */
// final 不允许被继承
public final class T01_Singleton {

    private byte[] data = new byte[1024];

    private static T01_Singleton instance = new T01_Singleton();


    private T01_Singleton() {
    }

    public static T01_Singleton getInstance() {
        return instance;
    }
}

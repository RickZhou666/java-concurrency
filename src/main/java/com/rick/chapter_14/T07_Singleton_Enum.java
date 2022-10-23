package com.rick.chapter_14;

/**
 * @Author: Rick
 * @Date: 2022/10/23 21:30
 */
// 枚举类型本身是final的，不允许被继承
public enum T07_Singleton_Enum {

    INSTANCE;

    // 实例变量
    private byte[] bytes = new byte[1024];

    T07_Singleton_Enum(){
        System.out.println("INSTANCE will be initialized immediately");
    }

    public static void method(){
        // 调用该方法则会主动使用Singleton, INSTANCE将会被实例化
    }

    public static T07_Singleton_Enum getInstance(){
        return INSTANCE;
    }

}

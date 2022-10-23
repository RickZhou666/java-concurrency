package com.rick.chapter_14;

/**
 * @Author: Rick
 * @Date: 2022/10/23 21:22
 */
// final不允许被继承
public final class T06_Singleton_Holder {
    // 实例变量
    private byte[] bytes = new byte[1024];

    private T06_Singleton_Holder() {
    }

    // 在静态内部类中持有Singleton的实例，并且可被直接初始化
    private static class Holder{
        private static T06_Singleton_Holder instance = new T06_Singleton_Holder();
    }

    // 调用getInstance方法，事实上是获得Holder的instance静态属性
    public static T06_Singleton_Holder getInstance(){
        return Holder.instance;
    }
}

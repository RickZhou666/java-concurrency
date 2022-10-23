package com.rick.chapter_14;

import java.net.Socket;
import java.sql.Connection;

/**
 * @Author: Rick
 * @Date: 2022/10/23 21:20
 */
// final不允许被继承
public final class T05_Singleton_DoubleCheck_Volatile {
    // 实例变量
    private byte[] bytes = new byte[1024];

    private volatile static T05_Singleton_DoubleCheck_Volatile instance = null;

    Connection conn;

    Socket socket;

    public T05_Singleton_DoubleCheck_Volatile() {
        // 初始化conn
        this.conn = new ConnectionImpl();
        // 初始化socket
        this.socket = new Socket();
    }

    public static T05_Singleton_DoubleCheck_Volatile getInstance() {
        // 当instance为null时，进入同步代码块，同事该判断避免了每次都需要进入同步代码块，可以提高效率
        if (null == instance) {
            // 只有一个线程能够获得Singleton.class关联的monitor
            synchronized (T05_Singleton_DoubleCheck_Volatile.class) {
                // 判断如果instance为null则创建
                if (null == instance) {
                    instance = new T05_Singleton_DoubleCheck_Volatile();
                }
            }
        }
        return instance;
    }
}

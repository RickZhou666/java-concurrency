package com.rick.chapter_09;

/**
 * @Author: Rick
 * @Date: 2022/10/16 23:11
 */
public class T01_Singleton {

    private static T01_Singleton instance = new T01_Singleton();
    private static int x = 0;
    private static int y;



    public T01_Singleton() {
        x++;
        y++;
        // this.x = x;
        // this.y = 1;
    }

    public static T01_Singleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        T01_Singleton singleton = T01_Singleton.getInstance();
        System.out.println(singleton.x);
        System.out.println(singleton.y);
    }
}

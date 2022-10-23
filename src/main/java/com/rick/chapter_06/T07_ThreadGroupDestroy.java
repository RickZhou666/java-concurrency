package com.rick.chapter_06;

/**
 * @Author: Rick
 * @Date: 2022/10/4 17:05
 */
public class T07_ThreadGroupDestroy {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("TestGroup");

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println("group.isDestroyed = " + group.isDestroyed()); // check whether destroyed
        mainGroup.list();

        group.destroy(); // remove group itself from parent group

        System.out.println("group.isDestroyed = " + group.isDestroyed());
        mainGroup.list();
    }
}

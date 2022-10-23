package com.rick.chapter_06;

/**
 * @Author: Rick
 * @Date: 2022/10/4 16:20
 */
public class T01_ThreadGroupCreator {
    public static void main(String[] args) {
        // 1. 获取当前线程的group
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        System.out.println(currentGroup);

        // 2. 定义一个新的group
        ThreadGroup group1 = new ThreadGroup("Group1");

        // 3. 程序输出true
        System.out.println(group1.getParent() == currentGroup);

        // 4. 定义group2，指定group1为其父group
        ThreadGroup group2 = new ThreadGroup(group1, "Group2");

        // 5. 程序输出true
        System.out.println(group2.getParent() == group1);
    }
}

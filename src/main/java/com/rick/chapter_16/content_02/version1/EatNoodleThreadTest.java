package com.rick.chapter_16.content_02.version1;

/**
 * @Author: Rick
 * @Date: 2022/10/23 23:40
 */
public class EatNoodleThreadTest {
    public static void main(String[] args) {
        Tableware fork = new Tableware("fork");
        Tableware knife = new Tableware("knife");
        new EatNoodleThread("A", fork, knife).start();
        new EatNoodleThread("B", knife, fork).start();
    }
}

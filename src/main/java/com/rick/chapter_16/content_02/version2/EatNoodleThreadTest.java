package com.rick.chapter_16.content_02.version2;


import com.rick.chapter_16.content_02.version1.Tableware;

/**
 * @Author: Rick
 * @Date: 2022/10/23 23:40
 */
public class EatNoodleThreadTest {
    public static void main(String[] args) {
        TableWarePair tableWarePair = new TableWarePair(new Tableware("fork"), new Tableware("knife"));
        new EatNoodleThread("A", tableWarePair).start();
        new EatNoodleThread("B", tableWarePair).start();
    }
}

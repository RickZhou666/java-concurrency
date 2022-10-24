package com.rick.chapter_16.content_02.version2;

/**
 * @Author: Rick
 * @Date: 2022/10/23 23:40
 */
public class EatNoodleThread extends Thread {
    private final String name;

    private final TableWarePair tableWarePair;


    public EatNoodleThread(String name, TableWarePair tableWarePair) {
        this.name = name;
        this.tableWarePair = tableWarePair;
    }

    @Override
    public void run() {
        while (true) {
            this.eat();
        }
    }

    // 吃面条的过程
    private void eat() {
        synchronized (tableWarePair) {
            System.out.println(name + " take up " + tableWarePair.getLeftTool() + "(left)");
            System.out.println(name + " take up " + tableWarePair.getRightTool() + "(right)");
            System.out.println(name + " is eating now.");
            System.out.println(name + " put down " + tableWarePair.getRightTool() + "(right)");
            System.out.println(name + " put down " + tableWarePair.getLeftTool() + "(left)");
        }

    }
}

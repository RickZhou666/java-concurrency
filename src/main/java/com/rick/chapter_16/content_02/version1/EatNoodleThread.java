package com.rick.chapter_16.content_02.version1;

/**
 * @Author: Rick
 * @Date: 2022/10/23 23:40
 */
public class EatNoodleThread extends Thread {
    private final String name;

    // 左手边的餐具
    private final Tableware leftTool;
    // 右手边的餐具
    private final Tableware rightTool;

    public EatNoodleThread(String name, Tableware leftTool, Tableware rightTool) {
        this.name = name;
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    @Override
    public void run() {
        while (true) {
            this.eat();
        }
    }

    // 吃面条的过程
    private void eat() {
        synchronized (leftTool) {
            System.out.println(name + " take up " + leftTool + "(left)");
            synchronized (rightTool){
                System.out.println(name + " take up " + rightTool + "(right)");
                System.out.println(name + " is eating now.");
                System.out.println(name + " put down " + rightTool + "(right)");
            }
            System.out.println(name + " put down " + leftTool + "(left)");
        }

    }
}

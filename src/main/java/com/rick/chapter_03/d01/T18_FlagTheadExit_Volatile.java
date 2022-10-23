package com.rick.chapter_03.d01;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/3 21:47
 */
public class T18_FlagTheadExit_Volatile {
    static class MyTask extends Thread {
        private volatile boolean closed = false;

        @Override
        public void run() {
            System.out.println("I will start work");
            while (!closed && !isInterrupted()) {
                // 正在运行
            }
            System.out.println("i will be exiting");
        }

        public void close(){
            this.closed = true;
            this.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask t = new MyTask();
        t.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("System will be shutdown.");;
        t.close();
    }
}

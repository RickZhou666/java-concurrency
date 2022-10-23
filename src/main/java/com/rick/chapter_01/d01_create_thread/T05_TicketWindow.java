package com.rick.chapter_01.d01_create_thread;

import java.util.Timer;

/**
 * @Author: Rick
 * @Date: 2022/9/27 00:08
 */
public class T05_TicketWindow extends Thread {

    private final String name;

    private static final int MAX = 50;

    // private int index = 1;
    private static int index = 1;

    public T05_TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index < MAX)
            System.out.println("柜台: " + name + " 当前的号码是： " + (index++));
    }


    public static void main(String[] args) {
        T05_TicketWindow ticketWindow1 = new T05_TicketWindow("一号出号机");
        ticketWindow1.start();

        T05_TicketWindow ticketWindow2 = new T05_TicketWindow("二号出号机");
        ticketWindow2.start();

        T05_TicketWindow ticketWindow3 = new T05_TicketWindow("三号出号机");
        ticketWindow3.start();

        T05_TicketWindow ticketWindow4 = new T05_TicketWindow("四号出号机");
        ticketWindow4.start();
    }
}

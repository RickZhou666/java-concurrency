package com.rick.chapter_16.content_01;

/**
 * @Author: Rick
 * @Date: 2022/10/23 23:19
 */
public class FlightSecurity {
    private int count = 0;

    // 登机牌
    private String boardingPass = "null";
    // 身份证
    private String idCard = "null";

    // public void pass(String boardingPass, String idCard) {
    public synchronized void pass(String boardingPass, String idCard) { // this can make sure single thread execution
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        this.count++;
        check();
    }

    private void check() {
        if (boardingPass.charAt(0) != idCard.charAt(0))
            throw new RuntimeException("====Exception====" + toString());
        System.out.println(Thread.currentThread() + toString() + "is verified");
    }

    @Override
    public String toString() {
        return "The " + count +
                " passengers, boardingPass [" + boardingPass + "]" +
                ", idCard [" + idCard + "]";
    }
}

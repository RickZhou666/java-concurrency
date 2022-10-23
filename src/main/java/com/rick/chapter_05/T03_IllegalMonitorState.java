package com.rick.chapter_05;

/**
 * @Author: Rick
 * @Date: 2022/10/4 09:36
 */
public class T03_IllegalMonitorState {

    private final Object MUTEX = new Object();

    // private synchronized void testWait(){
    private void testWait() {
        synchronized (MUTEX) {
            try {
                MUTEX.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // private synchronized void testNotify() {
    private void testNotify() {
        synchronized (MUTEX) {
            MUTEX.notify();
        }
    }

    public static void main(String[] args) {
        T03_IllegalMonitorState illegalMonitorState = new T03_IllegalMonitorState();
        illegalMonitorState.testWait();
        illegalMonitorState.testNotify();
    }
}

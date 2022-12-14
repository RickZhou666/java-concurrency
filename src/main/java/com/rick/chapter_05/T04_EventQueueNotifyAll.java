package com.rick.chapter_05;

import java.util.LinkedList;

import static java.lang.Thread.currentThread;

/**
 * @Author: Rick
 * @Date: 2022/10/4 09:59
 */
public class T04_EventQueueNotifyAll {
    private final int max;

    static class Event {
    }

    private final LinkedList<Event> eventQueue
            = new LinkedList<>();

    private final static int DEFAULT_MAX_EVENT = 10;

    public T04_EventQueueNotifyAll() {
        this(DEFAULT_MAX_EVENT);
    }

    public T04_EventQueueNotifyAll(int max) {
        this.max = max;
    }

    public void offer(Event event) {
        synchronized (eventQueue) {
            while (eventQueue.size() >= max) {
                try {
                    console(" the queue is full");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console(" the new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notifyAll();
        }
    }

    public Event take() {
        synchronized (eventQueue) {
            while (eventQueue.isEmpty()) {
                try {
                    console(" the queue is empty.");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = eventQueue.removeFirst();
            this.eventQueue.notifyAll();
            console(" the event " + event + " is handled.");
            return event;
        }
    }

    private void console(String message) {
        System.out.printf("%s:%s\n", currentThread().getName(), message);
    }
}

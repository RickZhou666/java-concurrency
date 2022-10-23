package com.rick.chapter_04;

import java.util.HashMap;

/**
 * @Author: Rick
 * @Date: 2022/10/4 01:07
 */
public class T09_HashMapDeadLock {
    private final HashMap<String, String> map = new HashMap<>();

    public void add(String key, String value) {
        this.map.put(key, value);
    }

    public static void main(String[] args) {
        final T09_HashMapDeadLock hmdl = new T09_HashMapDeadLock();
        for (int x = 0; x < 2; x++) {
            new Thread(() -> {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    hmdl.add(String.valueOf(i), String.valueOf(i));
                }
            }).start();
        }
    }
}

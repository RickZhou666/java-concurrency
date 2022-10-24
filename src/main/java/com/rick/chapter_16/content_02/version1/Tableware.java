package com.rick.chapter_16.content_02.version1;

/**
 * @Author: Rick
 * @Date: 2022/10/23 23:40
 */
public class Tableware {
    private final String toolName;

    public Tableware(String toolName) {
        this.toolName = toolName;
    }

    @Override
    public String toString() {
        return "Tool: " + toolName;
    }
}

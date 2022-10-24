package com.rick.chapter_16.content_02.version2;

import com.rick.chapter_16.content_02.version1.Tableware;

/**
 * @Author: Rick
 * @Date: 2022/10/24 00:10
 */
public class TableWarePair {
    private final Tableware leftTool;

    private final Tableware rightTool;

    public TableWarePair(Tableware leftTool, Tableware rightTool) {
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    public Tableware getLeftTool() {
        return leftTool;
    }

    public Tableware getRightTool() {
        return rightTool;
    }
}

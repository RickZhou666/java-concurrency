package com.rick.chapter_18;

import java.util.Collections;
import java.util.List;

/**
 * @Author: Rick
 * @Date: 2022/10/24 16:29
 */
// class 被final修改 不允许更改
public final class T05_Immutable {
    // list只能在构造时被指定
    private final List<String> list;

    public T05_Immutable(List<String> list) {
        this.list = list;
    }

    // 但 list是可变的，因为返回的list是可被其他线程修改的，如果不想让其被修改
    // 加上Collections.unmodifiableList(this.list);

    // public List<String> getList() {
    //     return this.list;
    // }

    public List<String> getList() {
        return Collections.unmodifiableList(this.list);
    }
}

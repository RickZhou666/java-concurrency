package com.rick.chapter_18;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Rick
 * @Date: 2022/10/24 15:33
 */
public class T01_ArrayListStream {
    public static void main(String[] args) {
        // 定义一个list并且使用Arrays的方式进行初始化
        List<String> list = Arrays.asList("Java", "Thread", "Concurrency", "Scala", "Clojure");

        // 获取并行的stream，然后通过map函数对list中的数据进行加工，最后输出
        list.parallelStream().map(String::toUpperCase)
                .forEach(System.out::println);
        System.out.println("\n");
        list.forEach(System.out::println);
    }
}

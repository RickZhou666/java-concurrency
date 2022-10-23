package com.rick.chapter_03.d01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @Author: Rick
 * @Date: 2022/10/3 20:41
 */
public class T15_FlightQueryExample {
    private static List<String> flightCompany = Arrays.asList("CSA", "CEA", "HNA");

    public static void main(String[] args) {
        // 1. 合作的各大航空公司
        List<String> results = search("SH", "BJ");
        System.out.println("==============result==============");
        results.forEach(System.out::println);
    }

    private static List<String> search(String original, String dest) {
        final List<String> result = new ArrayList<>();

        // 2. 创建查询航班信息的线程列表
        List<T14_FlightQueryTask> tasks = flightCompany.stream()
                .map(f -> createSearchTask(f, original, dest))
                .collect(toList());


        // 3. 分别启动这个几个线程
        tasks.forEach(Thread::start);

        // 第一个进来，阻塞住
        // 交给别的线程运行
        tasks.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println(t.getName() + "i am blocked");
            }
        });

        // 5. 在此之前，当前线程会阻塞住，获取每个查询线程的结果，并且加入到result中
        tasks.stream()
                .map(T13_FlightQuery::get)
                .forEach(result::addAll);

        return result;
    }

    private static T14_FlightQueryTask createSearchTask(String flight, String original, String dest) {
        return new T14_FlightQueryTask(flight, original, dest);
    }
}

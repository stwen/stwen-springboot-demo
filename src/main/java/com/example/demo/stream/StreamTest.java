package com.example.demo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: xianhao_gan
 * @date: 2020/09/04
 **/
public class StreamTest {

    public static void main(String[] args) {

        // map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        List<Integer> collect = list.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        collect.stream().forEach(System.out::println);

        // filter 方法用于通过设置的条件过滤出元素。以下代码片段使用 filter 方法过滤出空字符串：
        List<String> list1 = Arrays.asList("a","","b","c","d");
        long count = list1.stream().filter(string -> string.isEmpty()).count();


    }
}

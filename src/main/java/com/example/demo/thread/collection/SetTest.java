package com.example.demo.thread.collection;

import java.util.*;

/**
 * @description: Set 不包含重复元素的集合,区别list
 *      https://blog.csdn.net/Pandaminn/article/details/92682607
 * @author: stwen_gan
 * @date: 2020/04/25
 **/
public class SetTest {

    public static void main(String[] args) {
        // 查看类或接口的继承关系：ctrl + h
        Set<String> set = new HashSet<>();// 底层由HashMap实现
        Set<String> set2 = new LinkedHashSet<>();
        Set<String> set3 = new TreeSet<>();
    }
}

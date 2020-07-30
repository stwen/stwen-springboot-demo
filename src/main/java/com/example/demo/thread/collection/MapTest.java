package com.example.demo.thread.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: stwen_gan
 * @date: 2020/04/25
 **/
public class MapTest {

    public static void main(String[] args) {
        //非线程安全
        Map<String,Object> map = new HashMap<>();

        // 内部存储的键值对是无序的是按照哈希算法进行排序, 与 HashMap 最大的区别就是线程安全。
        // 键或者值不能为 null，为 null 就会抛出空指针异常
        Map<String,Object> map2 = new Hashtable<>();

        // 基于红黑树 (red-black tree) 数据结构实现，按 key 排序，默认的排序方式是升序
        Map<String,Object> map3 = new TreeMap<>();

        // 有序的 Map 集合实现类，相当于一个栈，先 put 进去的最后出来，先进后出
        Map<String,Object> map4 = new LinkedHashMap<>();

        // 线程安全
        Map<String,Object> map5 = new ConcurrentHashMap<>();

    }
}

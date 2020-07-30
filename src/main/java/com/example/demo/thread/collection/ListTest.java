package com.example.demo.thread.collection;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @description: ArrayList、LinkedList和Vector的区别
 *      https://blog.csdn.net/u012814441/article/details/80671604
 *      https://blog.csdn.net/kuangsonghan/article/details/79861170
 * @author: stwen_gan
 * @date: 2020/04/24
 **/
public class ListTest {

    public static void main(String[] args) {
        // List 中存储的数据是有顺序，并且允许重复
        List<String> list = new ArrayList<>();// 数组，非线程安全

        List<String> list2 = new LinkedList<>();// 数组，线程安全

        List<String> list3 = new Vector<>();// 双向链表，非线程安全

        // 并发环境，ArrayList和LinkedList可调用synchronizedList
        List<String> list4 = Collections.synchronizedList(list);

        vectorTest();
    }

    /**
     * Vector日常使用，线程安全
     */
    public static void vectorTest(){
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        List<Integer> list = new Vector<>();//线程安全 Synchronize
//        List<Integer> list = new ArrayList<>();// 非线程安全
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    list.add(i);
                }
                countDownLatch.countDown();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    list.add(i);
                }
                countDownLatch.countDown();
            }
        });
        thread1.start();
        thread2.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Integer k : list) {
            System.out.println(k);
        }
        System.out.println("总数" + list.size());
    }
}

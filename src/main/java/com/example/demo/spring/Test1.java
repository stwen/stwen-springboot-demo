package com.example.demo.spring;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: xianhao_gan
 * @date: 2020/11/09
 **/
public class Test1 {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
        long lambdaStart = System.currentTimeMillis();
        list.forEach(i -> {
            // 不用做事情，循环就够了
        });
        long lambdaEnd = System.currentTimeMillis();
        System.out.println("lambda循环运行毫秒数===" + (lambdaEnd - lambdaStart));

        long normalStart = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            // 不用做事情，循环就够了
        }
        long normalEnd = System.currentTimeMillis();
        System.out.println("普通循环运行毫秒数===" + (normalEnd - normalStart));
    }

}

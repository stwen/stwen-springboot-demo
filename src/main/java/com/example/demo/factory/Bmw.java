package com.example.demo.factory;

/**
 * @description: 宝马车
 * @author: xianhao_gan
 * @date: 2020/12/30
 **/
public class Bmw implements Car{

    @Override
    public void run() {
        System.out.println("----宝马车启动----");
    }
}

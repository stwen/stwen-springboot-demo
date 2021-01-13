package com.example.demo.factory;

/**
 * @description: 奔驰车
 * @author: xianhao_gan
 * @date: 2020/12/30
 **/
public class Benz implements Car{
    @Override
    public void run() {
        System.out.println("----奔驰车启动----");
    }
}

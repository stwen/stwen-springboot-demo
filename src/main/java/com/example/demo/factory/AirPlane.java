package com.example.demo.factory;

/**
 * @description: 客机
 * @author: xianhao_gan
 * @date: 2020/12/31
 **/
public class AirPlane implements Plane{
    @Override
    public void fly() {
        System.out.println("----客机起飞----");
    }
}

package com.example.demo.factory;

/**
 * @description: 战斗机
 * @author: xianhao_gan
 * @date: 2020/12/31
 **/
public class BattlePlane implements Plane{
    @Override
    public void fly() {
        System.out.println("----战斗机起飞----");
    }
}

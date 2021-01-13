package com.example.demo.factory;

/**
 * @description: 工厂二
 * @author: xianhao_gan
 * @date: 2020/12/31
 **/
public class FactoryTwo implements Factory{

    @Override
    public Car getCarByFactory() {
        return new Benz();
    }

    @Override
    public Plane getPlaneByFactory() {
        return new BattlePlane();
    }
}

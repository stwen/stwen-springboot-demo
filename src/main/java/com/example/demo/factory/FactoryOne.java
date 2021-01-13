package com.example.demo.factory;

/**
 * @description: 工厂一
 * @author: xianhao_gan
 * @date: 2020/12/31
 **/
public class FactoryOne implements Factory{

    @Override
    public Car getCarByFactory() {
        return new Bmw();
    }

    @Override
    public Plane getPlaneByFactory() {
        return new AirPlane();
    }
}

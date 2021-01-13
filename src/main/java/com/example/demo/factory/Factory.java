package com.example.demo.factory;

/**
 * @description: 抽象工厂--可生产不同产品组产品
 * @author: xianhao_gan
 * @date: 2020/12/31
 **/
public interface Factory {

    Car getCarByFactory();

    Plane getPlaneByFactory();
}

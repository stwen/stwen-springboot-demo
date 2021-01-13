package com.example.demo.factory;

/**
 * @description:
 * @author: xianhao_gan
 * @date: 2020/12/30
 **/
public class BmwFactory implements ICarFactory{
    @Override
    public Car getCarByFactory() {
        return new Bmw();
    }
}

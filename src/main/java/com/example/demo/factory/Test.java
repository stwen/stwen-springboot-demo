package com.example.demo.factory;

/**
 * @description:
 * @author: xianhao_gan
 * @date: 2020/12/30
 **/
public class Test {
    public static void main(String[] args) {

        // 简单工厂/静态工厂
//        Car car = CarStaticFactory.getCar("BMW");
//        Car car = CarStaticFactory.getCarByReflect("com.example.demo.factory.Bmw");
//        car.run();
//
//        // 简单工厂--模拟Spring bean工厂 (配置+反射)
//        Car bmw =BeanFactory.getCarByName("bmw");
//        Car benz =BeanFactory.getCarByName("benz");
//        bmw.run();
//        benz.run();
//
//        // 工厂方法
//        Car car1 = new BmwFactory().getCarByFactory();
//        car1.run();

        // 抽象工厂
        Car car1 = new FactoryOne().getCarByFactory();
        car1.run();
        Plane plane1 = new FactoryOne().getPlaneByFactory();
        plane1.fly();

        Car car2 = new FactoryOne().getCarByFactory();
        car2.run();
        Plane plane2 = new FactoryOne().getPlaneByFactory();
        plane2.fly();

    }
}

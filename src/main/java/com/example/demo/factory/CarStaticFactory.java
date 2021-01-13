package com.example.demo.factory;

/**
 * @description: 静态工厂
 * @author: xianhao_gan
 * @date: 2020/12/30
 **/
public class CarStaticFactory {

    public static Car getCar(String carName){
        if (carName.equals("BMW")){
            return new Bmw();
        }else if (carName.equals("Benz")){
            return new Benz();
        }
        return null;
    }

    // 通过反射获取类实例
    public static Car getCarByReflect(String classPath){
        Car car = null;
        try {
            Class clazz = Class.forName(classPath);
            //创建对象
            car = (Car) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }
}

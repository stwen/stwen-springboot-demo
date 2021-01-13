package com.example.demo.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description: cglib动态代理
 * @author: xianhao_gan
 * @date: 2020/12/29
 **/
public class CglibProxy implements MethodInterceptor {

    //维护目标对象
    private Object target;
    public CglibProxy(Object target) {
        this.target = target;
    }

//    //获取目标对象的代理对象
//    public Object getProxy() {
//        //1. 实例化工具类
//        Enhancer en = new Enhancer();
//        //2. 设置父类对象
//        en.setSuperclass(this.target.getClass());
//        //3. 设置回调函数
//        en.setCallback(this);
//        //4. 创建子类，也就是代理对象
//        return en.create();
//    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("-------动态代理，前置增强-----");

        //执行目标对象的方法
        Object object = method.invoke(target, objects);

        System.out.println("-------动态代理，后置增强-----");

        return object;
    }
}

package com.example.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: 动态代理
 * @author: xianhao_gan
 * @date: 2020/12/29
 **/
public class DynamicProxy implements InvocationHandler {


//    private Object target;
//
//    public DynamicProxy(Object target){
//        this.target = target;
//    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("------拼接sql-----");

        System.out.println("------执行sql-----");

        System.out.println("------关闭资源-----");
        return null;
    }

    public static <T> T getProxy(Class<T> clazz){
        DynamicProxy proxy = new DynamicProxy();
        return (T)Proxy.newProxyInstance(proxy.getClass().getClassLoader(), new Class[]{clazz},proxy);
    }

}

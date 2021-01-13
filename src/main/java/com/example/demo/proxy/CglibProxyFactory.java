package com.example.demo.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * @description: cglib代理工厂
 * @author: xianhao_gan
 * @date: 2020/12/30
 **/
public class CglibProxyFactory {

    public static <T> T createProxy(final Class<?> targetClass, final MethodInterceptor methodInterceptor) {
        return (T) Enhancer.create(targetClass,methodInterceptor);
    }
}

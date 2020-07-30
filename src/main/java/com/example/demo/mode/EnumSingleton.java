package com.example.demo.mode;

/**
 * @description: 枚举单例
 * @author: stwen_gan
 * @date: 2020/05/14
 **/
public enum EnumSingleton {
    INSTANCE;
    public EnumSingleton getInstance(){
        return INSTANCE;
    }
}

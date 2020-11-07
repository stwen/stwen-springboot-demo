package com.example.demo.spring.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * bean类型范围（单例、原型..）
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)// 只能放在类上
public @interface Scope {

    //默认单例
    String value() default "singleton";// 原型：prototype
}

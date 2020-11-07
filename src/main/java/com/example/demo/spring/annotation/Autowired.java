package com.example.demo.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: spring 依赖注入注解
 * @author: xianhao_gan
 * @date: 2020/10/28
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.CONSTRUCTOR,ElementType.FIELD})
public @interface Autowired {

    String value() default "";
}

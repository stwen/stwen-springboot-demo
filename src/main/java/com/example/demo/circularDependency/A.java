package com.example.demo.circularDependency;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xianhao_gan
 * @date: 2021/01/14
 **/
@Component
public class A {

    @Autowired
    private B b;
}

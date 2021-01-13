package com.example.demo.aop2;

/**
 * @description:
 * @author: xianhao_gan
 * @date: 2021/01/12
 **/
// 普通对象B 依赖了 A

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {

	@Autowired
	A a;

	public void test(){
		System.out.println("---b-----");

	}
}

package com.example.demo.aop2;

import com.example.demo.spring.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xianhao_gan
 * @date: 2021/01/12
 **/
@Component
public class A {

	@Autowired
	private B b;

	public void test(){
		System.out.println("----a---");
	}
}

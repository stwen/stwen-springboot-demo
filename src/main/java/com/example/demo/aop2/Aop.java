package com.example.demo.aop2;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: xianhao_gan
 * @date: 2021/01/12
 **/
@Aspect
@Component
public class Aop {


	@Pointcut("execution(* com.example.demo.aop2..*.*(..))")
	private void pointcut(){}

	@After("pointcut()")
	public void advice(){
		System.out.println("-----------后置增强---------");
	}
}

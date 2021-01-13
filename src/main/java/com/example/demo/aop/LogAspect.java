package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @description: aop
 * https://blog.csdn.net/a1036645146/article/details/112172174
 * @author: xianhao_gan
 * @date: 2021/01/04
 **/

@Component
@Aspect
public class LogAspect {


    // 切点
    @Before("execution(* com.example.demo.aop.IOrderService.submitOrder(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("IOrderServiceAspect.....()");
    }
}

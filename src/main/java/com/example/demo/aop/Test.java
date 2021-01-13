package com.example.demo.aop;

import com.example.demo.spring.factorybean.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: xianhao_gan
 * @date: 2021/01/07
 **/
public class Test {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(MyConfig.class);

        //测试自定义FactoryBean实现getObject产生bean （又实现类情况，可直接new）
        IOrderService bean = context.getBean(IOrderService.class);
        System.out.println(bean);
        bean.submitOrder();
        System.out.println("---------------------");
    }
}

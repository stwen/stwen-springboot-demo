package com.example.demo.spring.factorybean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description: 测试自定义FactoryBean的实现，如何产生bean
 * https://www.bilibili.com/video/BV1C54y1i7m6?from=search&seid=16368973764196409173
 *
 * @author: xianhao_gan
 * @date: 2020/12/24
 **/
public class FactoryBeanTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(MyConfig.class);

        //测试自定义FactoryBean实现getObject产生bean （又实现类情况，可直接new）
        IOrderService bean = context.getBean(IOrderService.class);
        System.out.println(bean);
        System.out.println("---------------------");

        // 自定义FactoryBean，必须是以类型的方式去获取 ,以下报错
        IOrderService bean2 = (IOrderService) context.getBean("orderServiceImpl");

        // 通过自定义FactoryBean 获取bean
        System.out.println(context.getBean("orderFactoryBean"));// bean是OrderServiceImpl,并不是OrderFactoryBean
        System.out.println(context.getBean("orderFactoryBean"));// 测试是否单例
        System.out.println(context.getBean("orderFactoryBean"));// 测试是否单例
        System.out.println(context.getBean("&orderFactoryBean"));// 获取到的是OrderFactoryBean

        // 测试OrderMapper接口（无实现类情况）能否生成代理对象--动态代理
        // OrderServiceImpl 需要加注解@Service
//        IOrderService orderService = context.getBean(IOrderService.class);
//        orderService.saveOrder();


    }
}

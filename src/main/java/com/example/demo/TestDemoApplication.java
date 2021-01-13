package com.example.demo;

import com.example.demo.aop.IOrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class TestDemoApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(TestDemoApplication.class, args);
        //
        IOrderService orderService = context.getBean(IOrderService.class);
        orderService.submitOrder();
    }

}

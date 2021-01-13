package com.example.demo.proxy;

/**
 * @description: 静态代理类
 * @author: xianhao_gan
 * @date: 2020/12/29
 **/
public class OrderStaticProxy implements IOrderService {

    private IOrderService orderService;

    // 构造注入
    public OrderStaticProxy(IOrderService orderService){
        this.orderService=orderService;
    }

    @Override
    public void submitOrder() {
        System.out.println("--------提交订单前，自定义逻辑");
        orderService.submitOrder();
        System.out.println("--------提交订单前，自定义逻辑");
    }
}

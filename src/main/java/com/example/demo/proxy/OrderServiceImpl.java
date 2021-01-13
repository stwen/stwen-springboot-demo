package com.example.demo.proxy;

/**
 * @description: 订单实现类
 * @author: xianhao_gan
 * @date: 2020/12/29
 **/
public class OrderServiceImpl implements IOrderService{

    // 提交订单测试
    @Override
    public void submitOrder() {
        System.out.println("-------保存订单-------");
    }
}

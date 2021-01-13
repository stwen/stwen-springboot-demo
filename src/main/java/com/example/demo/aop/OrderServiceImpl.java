package com.example.demo.aop;

import org.springframework.stereotype.Component;

/**
 * @description: 订单实现类
 * @author: xianhao_gan
 * @date: 2020/12/29
 **/
@Component
public class OrderServiceImpl implements IOrderService {

    // 提交订单测试
    @Override
    public void submitOrder() {
        System.out.println("-------保存订单-------");
    }
}

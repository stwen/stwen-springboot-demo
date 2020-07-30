package com.example.demo.service.consumer;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 消费者--订单服务
 * @author: stwen_gan
 * @date: 2020/05/08
 **/
@Service
public class OrderServiceConsumer {

    public Map<String,Object> getOrderDetail(String orderCode){

        Map<String,Object> map = new HashMap<>();

        return map;
    }
}

package com.example.demo.util;

import lombok.Data;

import java.util.concurrent.CompletableFuture;

/**
 * @description: 请求参数工具类
 * @author: stwen_gan
 * @date: 2020/05/08
 **/
@Data
public class RequestParams {

    private String OrderCode;//订单编号
    private String serialNo;//请求标记，相当于每个请求对应的唯一身份号
    private CompletableFuture future;//
}

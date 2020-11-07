package com.example.demo.spring.service;

import com.example.demo.spring.BeanNameAware;
import com.example.demo.spring.InitializingBean;
import com.example.demo.spring.annotation.Autowired;
import com.example.demo.spring.annotation.Component;
import com.example.demo.spring.annotation.Scope;

/**
 * @description: 用户服务
 * @author: xianhao_gan
 * @date: 2020/10/28
 **/
@Component("userService")
//@Lazy // 懒加载
@Scope("prototype") // 原型bean(多实例)
public class UserService implements BeanNameAware, InitializingBean {

    @Autowired
    private User user;

    // todo 需要spring初始化时，帮该bean自动注入 beanName属性，
    //  假如Component没有填value,则需要默认给他一个beanName=userService
    private String beanName;

    private String userName;

    /**
     * 测试是否注入User bean
     */
    public void test(){
        System.out.println(user);
        System.out.println(beanName);
        System.out.println(userName);
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName=beanName;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingBean初始化bean属性");
        this.userName="xxxx";
    }
}

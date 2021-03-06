package com.example.demo.spring;

import com.example.demo.spring.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: xianhao_gan
 * @date: 2020/10/28
 **/
@Configuration
@ComponentScan("com.example.demo.spring.service")// 自定义扫描注解
public class AppConfig {

}

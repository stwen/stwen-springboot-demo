package com.example.demo.spring;

import com.example.demo.spring.service.UserService;

/**
 * @description:
 * @author: xianhao_gan
 * @date: 2020/10/28
 **/
public class Test {

    public static void main(String[] args) {

        // 加载xml配置
        //ClassPathXmlApplicationContext
        // 启动spring,初始化，加载java注解配置类
//         AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // 启动spring,初始化（扫描-->创建非懒加载的单例bean--bean声明周期）
        MySpringApplicationContext applicationContext = new MySpringApplicationContext(AppConfig.class);
        System.out.println("==================分割线=================");

        UserService userService = (UserService) applicationContext.getBean("userService");

        // 测试是否单例，单例：3次打印的对象应该都是一样的；原型：3次都不一样
        System.out.println(userService);
//        System.out.println(applicationContext.getBean("userService"));
//        System.out.println(applicationContext.getBean("userService"));

        // 测试Autowired是否注入成功
        userService.test();

    }
}

package com.example.demo.spring.test;

import com.example.demo.spring.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description: spring 调试测试
 * https://blog.csdn.net/java_lyvee/article/details/102633067
 * @author: xianhao_gan
 * @date: 2020/12/18
 **/
public class SpringDebugTest {

//        当执行完 AbstractApplicationContext 的 invokeBeanFactoryPostProcessors (beanFactory) 方法后
//        beanDefinitionMap 多了一个<x, beanDefinition >，但是后台发现并没有打印 "X Constructor"，这说明这个时候X并没有被实例化，
//        说明spring是先把类扫描出来解析称为一个beanDefintion对象，然后put到beanDefintionMap，
//        后面执行 finishBeanFactoryInitialization(beanFactory) 才会去实例化X
    public static void main(String[] args) {


        // 方式一：启动spring
//         AnnotationConfigApplicationContext ac1 = new AnnotationConfigApplicationContext(AppConfig.class);

        // 方式二：启动spring  注解方式

        // 会初始化一个BeanFactory,为默认的DefaultListableBeanFactory
        // 会初始化一个beanDefinition的读取器，同时向容器中注册了7个spring的后置处理器(包括BeanPostProcessor和BeanFactoryPostProcessor)
        // 会初始化一个扫描器，后面似乎并没有用到这个扫描器，在refresh()中使用的是重新new的一个扫描器。
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

        //动态注册一个配置类 进beanDefinitionMap中
        ac.register(AppConfig.class);
        ac.register(X.class);// 可以显示、动态注册一个程序员提供的bean

        // 初始化spring容器
        ac.refresh();


        //正常打印
//        System.out.println(ac.getBean(Y.class));
//        //正常打印
//        System.out.println(ac.getBean(Z.class));
        //异常打印
        //虽然X加了注解，但是被偷梁换柱了，故而异常
        System.out.println(ac.getBean(X.class));

    }
}

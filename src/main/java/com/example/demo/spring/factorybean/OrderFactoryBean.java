package com.example.demo.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @description: 自定义FactoryBean实现
 * @author: xianhao_gan
 * @date: 2020/12/24
 **/
@Component // 记得使用spring的，别导入自定义的了
public class OrderFactoryBean implements FactoryBean {

    /**
     * 这里我们就可以控制Bean的创建过程
     */
    @Override
    public Object getObject() throws Exception {
        System.out.println("-----调用OrderFactoryBean.getObject----");

        // 1、直接new，有实现类的情况
        IOrderService orderService = new OrderServiceImpl();
        return orderService;

        // 2、动态代理，无需实现类
        /**
         * 应用：如mybatis 给xxxMapper接口生成代理实现类
         * https://blog.csdn.net/lovejj1994/article/details/88193562
         * MapperFactoryBean 实现 FactoryBean 接口
         * MapperScannerRegistrar类的registerBeanDefinitions方法利用ClassPathMapperScanner对象读取解析了指定包名下所有的接口，
         * 并且通过构建BeanDefinitions对象，让每个MapperFactoryBean拥有不同的mapperInterface class属性
         */
//        OrderMapper bean = (OrderMapper) Proxy.newProxyInstance(OrderFactoryBean.class.getClassLoader(), new Class[]{OrderMapper.class}, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                // todo
//                System.out.println(method.getName());
//                return null;
//            }
//        });
//
//        return bean;
    }

    /**
     * 返回需要获取的bean的class类型
     * 因为我们可能有多个自定义的FactoryBean实现，所以需要getObjectType通过class类型来区分
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return IOrderService.class;
//        return OrderMapper.class;
    }

    /**
     * 是否单例，默认单例
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}

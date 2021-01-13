package com.example.demo.proxy;

/**
 * @description: 测试
 * @author: stwen_gan
 * @date:
 **/
public class Test {

    public static void main(String[] args) {

        //该设置用于输出jdk动态代理产生的类
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        // 静态代理
//        IOrderService orderService = new OrderServiceImpl();
//        OrderStaticProxy proxy = new OrderStaticProxy(orderService);
//        proxy.submitOrder();

        // jdk动态代理
//        IOrderService orderService = new OrderServiceImpl();
////        IOrderService dynamicProxy = (IOrderService) DynamicProxy.getProxy(orderService);
//        IOrderService dynamicProxy = DynamicProxy.getProxy(IOrderService.class);
//        dynamicProxy.submitOrder();

        // cglib动态代理
        OrderDao orderDao = new OrderDao();
//        OrderDao orderDaoProxy = (OrderDao) new CglibProxy(orderDao).getProxy();
        OrderDao orderDaoProxy = CglibProxyFactory.createProxy(OrderDao.class, new CglibProxy(orderDao));
        orderDaoProxy.submitOrder();
    }
}

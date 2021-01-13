package com.example.demo.spring.factorybean;

/**
 * @description:
 * @author: xianhao_gan
 * @date: 2020/12/24
 **/
//@Service
public class OrderServiceImpl implements IOrderService {


//    @Autowired
//    private OrderMapper orderMapper;// OrderMapper没有实现类
//
//    @Override
//    public void saveOrder() {
//
//        System.out.println("--------开始保存订单----");
//        // 测试通过是否空指针
////        System.out.println(orderMapper);
//        orderMapper.saveOrder();
//        System.out.println("--------保存订单成功----");
//    }

    @Override
    public void saveOrder() {
        System.out.println("-----创建订单------");
    }
}

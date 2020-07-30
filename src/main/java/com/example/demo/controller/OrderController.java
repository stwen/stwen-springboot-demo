package com.example.demo.controller;

import com.example.demo.service.consumer.OrderServiceConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @description:
 * @author: stwen_gan
 * @date: 2020/05/08
 **/
@RestController
@Controller("/order")
public class OrderController {

    private static Integer THREAD_COUNT = 1000;

    //CountDownLatch类似MapReduce，一个或多个线程等待其他线程的完成，才能往下执行
    //CyclicBarrier 线程间互相等待
    private CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
    @Autowired
    private OrderServiceConsumer orderService;

    @RequestMapping("/detail")
    public Map<String,Object> getOrderDetail(String orderCode){

        for(int i=0;i<THREAD_COUNT;i++){
            Thread thread = new Thread(()->{
                // Runnable --> run()方法
                try {
                    countDownLatch.countDown();
                    countDownLatch.await();//阻塞：等待countDownLatch减为0，才往下执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Map<String, Object> response = orderService.getOrderDetail(orderCode);
            });
            thread.start();
        }
        return null;
    }

}

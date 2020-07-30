package com.example.demo.thread.state;

import java.util.concurrent.TimeUnit;

/**
 * @description: 线程不断休眠--Thread.sleep()线程进入 TIME_WAITING：超时等待状态。可以在一定的时间自行返回
 * @author: stwen_gan
 * @date: 2020/04/17
 **/

public class WaitingTime implements Runnable{
    @Override
    public void run() {
        while (true){
            waitSecond(200);
        }
    }

    //线程等待多少秒
    public static final void waitSecond(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

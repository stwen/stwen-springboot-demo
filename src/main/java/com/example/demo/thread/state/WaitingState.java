package com.example.demo.thread.state;

/**
 * @description: 线程在Warting上等待--Object.wait(),验证线程的WAITING状态
 * @author: stwen_gan
 * @date: 2020/04/17
 **/

public class WaitingState implements Runnable {
    @Override
    public void run() {
        while (true){
            synchronized (WaitingState.class){
                try {
                    WaitingState.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

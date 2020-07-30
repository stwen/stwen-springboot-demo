package com.example.demo.thread.state;

/**
 * @description: 线程的各种状态，测试线程的生命周期
 * @author: stwen_gan
 * @date: 2020/04/17
 **/
public class ThreadStateTest {

    /**
     * 可使用命令“jsp 、jstack 线程id” 查看线程运行状态
     * @param args
     */
    public static void main(String[] args){
        new Thread(new WaitingTime(), "WaitingTimeThread").start();
        new Thread(new WaitingState(), "WaitingStateThread").start();

        //BlockedThread-01线程会抢到锁，BlockedThread-02线程会阻塞
        new Thread(new BlockedThread(), "BlockedThread-01").start();
        new Thread(new BlockedThread(), "BlockedThread-02").start();
    }
}

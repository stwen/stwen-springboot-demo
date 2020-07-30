package com.example.demo.thread.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @description: https://www.jianshu.com/p/f1f2cd289205
 * @author: stwen_gan
 * @date: 2020/04/20
 **/
public class LockSupportDemo {

    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super(name);
        }
        @Override public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                LockSupport.park();
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("被中断了");
                }
                System.out.println("继续执行");
            }
        }
    }

    /**
     * 执行结果：
     * ------------------
     * in t1
     * 被中断了
     * 继续执行
     * in t2
     * 继续执行
     * ------------------
     * -->注意：因为run方法里加了synchronized--同步代码块，要t1执行完才轮到t2
     */
    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(1000L);
        t2.start();
        Thread.sleep(3000L);
        t1.interrupt();//如果调用线程被中断，则park方法会返回
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}

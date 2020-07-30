package com.example.demo.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: stwen_gan
 * @date: 2020/03/04
 **/
public class ReentrantLockTest {

    // 默认是非公平锁，true-公平锁
    //（reentrantLock--jvm级别，用户态，不会去到操作系统os的内核态，相对jdk1.6之前的sync性能高）
    private static ReentrantLock reentrantLock = new ReentrantLock(true);

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test();
            }
        });
        t1.setName("t1");
        t2.setName("t2");

        t1.start();
//        t2.start();
        System.out.println("111111111111");
    }

    private static void test() {

        reentrantLock.lock();
//        reentrantLock.lockInterruptibly();

        String name = Thread.currentThread().getName();
        System.out.println(name);
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }

    }
}

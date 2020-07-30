package com.example.demo.thread.callable;

import java.util.concurrent.*;

/**
 * @description: 自定义实现一个FutureTask功能
 * @author: stwen_gan
 * @date: 2020/05/09
 **/
public class MyFutureTask<V> implements RunnableFuture<V> {

    //方便调用callable的call(),call需要用户去实现，调用实际业务逻辑
    Callable<V> callable;
    volatile Object result;//保存结果

    //FutureTask是通过一个state状态属性,判断当前任务task的运行状态
    //private volatile int state;

    //通过MyFutureTask构造方法实例化
    public MyFutureTask(Callable<V> callable){
        this.callable = callable;
    }

    /**
     *  启动Thread线程start()，则会执行Runnable的run()方法，
     *  所以,想要启动线程时，调用Callable的call()（用户覆写实现）,则需要在run调用call,
     *  则需要增加一个Callable对象属性
     */
    @Override
    public void run() {
        try {
            callable.call();
            //todo 把结果设到共享变量result中

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 阻塞，直到有返回值
     * 实现思路？
     * 1、首先，返回值对应哪个线程，如何实现？-->共享变量
     * 2、如何实现阻塞？-->wait()、notify/notifyAll
     * 注：FutureTask使用park()、unpark()
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Override
    public V get() throws InterruptedException, ExecutionException {
        //有值才返回
        if(null!=result){
            return (V)result;
        }
        //如何实现阻塞？--> while循坏? sleep?yield?--参考ReentrantLock如何实现，park/unpark
        //https://blog.csdn.net/java_lyvee/article/details/98966684
        synchronized (this){
            this.wait();
        }
        return (V)result;
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

}

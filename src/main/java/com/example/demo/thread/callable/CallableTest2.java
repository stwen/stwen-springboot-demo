package com.example.demo.thread.callable;

import java.util.concurrent.*;

/**
 * @description: Callable、FutureTask、ExecutorService 示例
 *      http://www.cnblogs.com/dolphin0520/p/3949310.html
 * @author: stwen_gan
 * @date: 2020/05/09
 **/
public class CallableTest2 {

    public static void main(String[] args) {

        //方法一：Callable + FutureTask + ExecutorService
        ExecutorService executorService = Executors.newCachedThreadPool();
        MyTask task = new MyTask();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        executorService.submit(futureTask);
        executorService.shutdown();

        //方法二，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
//        MyTask task = new MyTask();
//        FutureTask<Integer> futureTask = new FutureTask<>(task);
//        Thread thread = new Thread(futureTask);
//        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            System.out.println("task运行结果"+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");

    }

    static class MyTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程在执行任务...");
            Thread.sleep(3000);
            int sum = 0;
            for(int i=0;i<100;i++)
                sum += i;
            return sum;
        }
    }
}

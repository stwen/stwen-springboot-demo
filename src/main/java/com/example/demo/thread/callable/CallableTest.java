package com.example.demo.thread.callable;

import java.util.concurrent.*;

/**
 * @description: Callable、Future、ExecutorService 示例
 *      http://www.cnblogs.com/dolphin0520/p/3949310.html
 * @author: stwen_gan
 * @date: 2020/05/09
 **/
public class CallableTest {
    public static void main(String[] args) {

        // Callable + Future + ExecutorService 启动线程
        ExecutorService executorService = Executors.newCachedThreadPool();

//        Callable<Integer> callable = new Callable() {
//            @Override
//            public Object call() throws Exception {
//                //todo
//                return null;
//            }
//        };
        MyTask callable = new MyTask();

        Future<Integer> result = executorService.submit(callable);
        executorService.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务...");

        try {
            System.out.println("子线程结果："+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("所有线程执行完毕！");
    }

    /**
     * 执行结果：
     * 子线程在执行任务...
     * 主线程在执行任务...
     * 子线程结果：4950
     * 所有线程执行完毕！
     */

    static class MyTask implements Callable<Integer>{
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

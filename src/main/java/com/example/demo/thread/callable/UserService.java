package com.example.demo.thread.callable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description: 用户服务，串行/并行区别
 * @author: stwen_gan
 * @date: 2020/05/09
 **/
public class UserService {

    public Map<String,Object> getUserDetail(String userId) throws ExecutionException, InterruptedException {

        Map<String,Object> map = new HashMap<>();

        //方法一、串行，总耗时12秒
        //获取用户信息--调用用户模块，5秒
        //Map<String,Object> userInfo = userService.getUserInfo();

        //获取积分信息--调用积分模块，4秒
        //Map<String,Object> scoreInfo = scoreService.getScoreInfo();

        //获取账户信息--调用账户模块，3秒
        //Map<String,Object> accountInfo = accountService.getAccountInfo();

        //方法二、改进：多线程并行，总耗时5秒
        Callable userCallable = new Callable() {
            @Override
            public Object call() throws Exception {
                //Map<String,Object> userInfo = userService.getUserInfo();
                return null;
            }
        };
        Callable scoreCallable = new Callable() {
            @Override
            public Object call() throws Exception {
                //Map<String,Object> scoreInfo = scoreService.getScoreInfo();
                return null;
            }
        };
        Callable accountCallable = new Callable() {
            @Override
            public Object call() throws Exception {
                //Map<String,Object> accountInfo = accountService.getAccountInfo();
                return null;
            }
        };

        //启动线程
        FutureTask futureTask = new FutureTask(userCallable);
        Object user = futureTask.get();

        FutureTask scoreTask = new FutureTask(scoreCallable);
        Object score = scoreTask.get();

        FutureTask accountTask = new FutureTask(accountCallable);
        Object account = accountTask.get();

        map.put("userInfo",user);
        map.put("scoreInfo",score);
        map.put("accountInfo",account);

        return null;
    }
}

package com.example.demo.cache;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 监听失效数据并移除
 * @author: xianhao_gan
 * @date: 2020/09/02
 **/
@Slf4j
public class CacheListener{

    private CacheManagerImpl cacheManagerImpl;

    /**
     * 构造注入 缓存实现类
     * @param cacheManagerImpl
     */
    public CacheListener(CacheManagerImpl cacheManagerImpl) {
        this.cacheManagerImpl = cacheManagerImpl;
    }

    /**
     * todo 优化：使用定时器线程池，用于清除过期缓存 ：ScheduledExecutorService
     * https://blog.csdn.net/u013314786/article/details/80658738
     *
     */
    public void startListen() {
        new Thread(){
            public void run() {
                while (true) {
                    for(String key : cacheManagerImpl.getAllKeys()) {
                        if (cacheManagerImpl.isTimeOut(key)) {
                            cacheManagerImpl.clearByKey(key);
                            log.info(key + "缓存被清除");
                        }
                    }
                }
            }
        }.start();

    }
}

package com.example.demo;

import com.example.demo.cache.CacheManagerImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description: 测试缓存
 * @author: xianhao_gan
 * @date: 2020/09/02
 **/
@Slf4j
public class TestCache {

    /**
     * 测试缓存和缓存失效
     */
    @Test
    public void testCacheManager() {
        CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
        cacheManagerImpl.putCache("test", "test", 10 * 1000L);
        cacheManagerImpl.putCache("myTest", "myTest", 15 * 1000L);

        //todo 已优化成：在 putCache中使用定时器线程池，定期清理过期缓存
//        CacheListener cacheListener = new CacheListener(cacheManagerImpl);
//        cacheListener.startListen();
        log.info("test:" + cacheManagerImpl.getCacheByKey("test").getDatas());
        log.info("myTest:" + cacheManagerImpl.getCacheByKey("myTest").getDatas());
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("test:" + cacheManagerImpl.getCacheByKey("test"));
        log.info("myTest:" + cacheManagerImpl.getCacheByKey("myTest"));
    }

    /**
     * 测试线程安全
     */
    @Test
    public void testThredSafe() {
        final String key = "thread";
        final CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            exec.execute(new Runnable() {
                public void run() {
                    if (!cacheManagerImpl.isContains(key)) {
                        cacheManagerImpl.putCache(key, 1, 0);
                    } else {
                        //因为+1和赋值操作不是原子性的，所以把它用synchronize块包起来
                        synchronized (cacheManagerImpl) {
                            int value = (Integer) cacheManagerImpl.getCacheDataByKey(key) + 1;
                            cacheManagerImpl.putCache(key,value , 0);
                        }
                    }
                }
            });
        }
        exec.shutdown();
        try {
            exec.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        log.info(cacheManagerImpl.getCacheDataByKey(key).toString());
    }
}
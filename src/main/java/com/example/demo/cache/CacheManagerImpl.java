package com.example.demo.cache;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @description: 缓存接口实现类
 *  每个方法可加 synchronized 同步
 *
 * @author: xianhao_gan
 * @date: 2020/09/02
 **/
public class CacheManagerImpl implements ICacheManager {

    /**
     * 缓存--键值对集合
     */
    private static Map<String, EntityCache> caches = new ConcurrentHashMap<String, EntityCache>();

    /**
     * 定时器线程池，用于清除过期缓存
     */
    // 注： 不要使用Executors.newXXXThreadPool()快捷方法创建线程池，因为这种方式会使用无界的任务队列，
    //为避免OOM，我们应该使用ThreadPoolExecutor的构造方法手动指定队列的最大长度
    private final static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();


    /**
     * 存入缓存
     *
     * @param key
     * @param cache
     */
    @Override
    public void putCache(String key, EntityCache cache) {
        //先清除原key
        //this.clearByKey(key);

        //todo 优化：设置过期时间，定期自动清理
        // https://blog.csdn.net/u013314786/article/details/80658738
        if(cache.getTimeOut()>0){
            Future future = executor.schedule(new Runnable() {
                @Override
                public void run() {
                    clearByKey(key);
                }
            }, cache.getTimeOut(), TimeUnit.MILLISECONDS);
        }

        caches.put(key, cache);
    }

    /**
     * 存入缓存，设置过期时间
     *
     * @param key
     * @param datas
     * @param timeOut
     */
    @Override
    public void putCache(String key, Object datas, long timeOut) {
        timeOut = timeOut > 0 ? timeOut : 0L;
        putCache(key, new EntityCache(datas, timeOut, System.currentTimeMillis()));
    }

    /**
     * 获取对应缓存
     *
     * @param key
     * @return
     */
    @Override
    public EntityCache getCacheByKey(String key) {
        if (this.isContains(key)) {
            return caches.get(key);
        }
        return null;
    }

    /**
     * 获取对应缓存
     *
     * @param key
     * @return
     */
    @Override
    public Object getCacheDataByKey(String key) {
        if (this.isContains(key)) {
            return caches.get(key).getDatas();
        }
        return null;
    }

    /**
     * 获取所有缓存
     *
     * @return
     */
    @Override
    public Map<String, EntityCache> getCacheAll() {
        return caches;
    }

    /**
     * 判断是否在缓存中
     *
     * @param key
     * @return
     */
    @Override
    public boolean isContains(String key) {
        return caches.containsKey(key);
    }

    /**
     * 清除所有缓存
     */
    @Override
    public void clearAll() {
        caches.clear();
    }

    /**
     * 清除对应缓存
     *
     * @param key
     */
    @Override
    public void clearByKey(String key) {
        if (this.isContains(key)) {
            caches.remove(key);
        }
    }

    /**
     * 缓存是否超时失效
     *
     * @param key
     * @return
     */
    @Override
    public boolean isTimeOut(String key) {
        if (!caches.containsKey(key)) {
            return true;
        }
        EntityCache cache = caches.get(key);
        long timeOut = cache.getTimeOut();
        long lastRefreshTime = cache.getLastRefeshTime();
        if(timeOut==0){//永不过期
            return false;
        }else if (System.currentTimeMillis() - lastRefreshTime >= timeOut) {
            return true;
        }
        return false;
    }

    /**
     * 获取所有key
     *
     * @return
     */
    @Override
    public Set<String> getAllKeys() {
        return caches.keySet();
    }
}

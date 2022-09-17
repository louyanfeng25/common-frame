package com.baiyan.common.base.utils.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 本地缓存工厂
 *
 * @author baiyan
 * @time 2020/11/18 14:46
 */
public class CacheFactory {

    /**
     * 获取实例
     */
    public static final CacheFactory instance = new CacheFactory();

    private CacheFactory() {
    }

    private static final Logger log = LoggerFactory.getLogger(CacheFactory.class);

    /**
     * 构建缓存
     * @param expiration
     * @return
     */
    public Cache<String, Object> getCache(Long expiration){
        return CacheBuilder.newBuilder()
                .concurrencyLevel(8)
                .expireAfterWrite(expiration, TimeUnit.SECONDS)
                .initialCapacity(20)
                .maximumSize(1000L)
                .recordStats()
                .removalListener(notification -> log.info("{}被移除了,原因:{}",notification.getKey(),notification.getCause()))
                .build();
    }
}

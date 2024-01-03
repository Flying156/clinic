package com.learn.clinic.uitls;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis 工具类
 *
 * @author Milk
 * @version 2023/12/31 16:58
 */
@Component
public class RedisUtil {

    private volatile static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate){
        RedisUtil.redisTemplate =  redisTemplate;
    }

    /**
     * 为 Redis 键设置过期时间
     *
     * @param key      键
     * @param timeout  过期时间
     * @param timeUnit 单位
     * @return         设置是否成功
     */
    public Boolean setEx(@NotNull String key, Long timeout, TimeUnit timeUnit){
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     * 删除键
     *
     * @param key 键
     * @return    是否成功
     */
    public Boolean del(@NotNull String key){
        return redisTemplate.delete(key);
    }

    /**
     * 将哈希键值放入 Redis 哈希表中
     *
     * @param key    键
     * @param hKey   哈希键
     * @param value  值
     */
    public void hSet(@NotNull String key, @NotNull Object hKey,@NotNull Object value){
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取值
     *
     * @param key    键
     * @param hKey   哈希键
     * @return       值
     */
    public Object get(@NotNull String key, @NotNull Object hKey){
        return redisTemplate.opsForHash().get(key, hKey);
    }

}

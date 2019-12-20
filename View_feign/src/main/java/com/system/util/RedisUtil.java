package com.system.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: dapeng_lgq
 * @since: JDK-1.8
 * @author: dapeng-liguoqing
 * @create: 2019/12/16 9:45
 * @version: 1.0
 * @description: Redis工具类
 **/
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    //放入缓存但不设置时间
    public boolean setRedis(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //放入缓存并设置失效时间，以分钟为单位
    public boolean set(String key, Object value, long minutes) {
        try {
            if (minutes > 0) {
                redisTemplate.opsForValue().set(key, value, minutes, TimeUnit.MINUTES);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //移除值为value的redis缓存
    public boolean removeRedis(String[] key){
        try {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //判断key键是否存在
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //读取缓存数据
    public Object getObject(String key){
        Object o = null;
        try {
            o = redisTemplate.opsForValue().get(key);
        }catch (Exception e){
            o = null;
        }
        return o;
    }



}

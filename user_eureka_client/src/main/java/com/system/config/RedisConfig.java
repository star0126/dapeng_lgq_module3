package com.system.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ProjectName: dapeng_lgq
 * @since: JDK-1.8
 * @author: dapeng-liguoqing
 * @create: 2019/12/16 9:31
 * @version: 1.0
 * @description: redis配置类
 **/

@Configuration
public class RedisConfig {
    

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        //使用fastjson序列化
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        //value的序列化采用fastjson序列化
        template.setValueSerializer(fastJsonRedisSerializer);
        // key的序列化采用StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        //hash的value值序列化采用fastjson序列化
        template.setHashValueSerializer(fastJsonRedisSerializer);
        //hash的key值序列化采用fastjson序列化
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

}

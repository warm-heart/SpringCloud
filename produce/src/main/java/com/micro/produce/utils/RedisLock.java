package com.micro.produce.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wangqianlong
 * @create 2020-08-13 22:03
 */
@Service
public class RedisLock {
    @Autowired
    private RedisTemplate redisTemplate;

    public boolean getLock(String key,String value){
        return redisTemplate.opsForValue().setIfAbsent(key,value,5L, TimeUnit.SECONDS);
    }

    public boolean releaseLock(String key,String value){

        RedisScript<Long> script = RedisScript
                .of(new ClassPathResource("releaseLock.lua"),Long.class);
        List<String> keys = Arrays.asList(key);
        Long res = (Long) redisTemplate.execute(script, keys, value);
        if (res==1){
            return true;
        }
        return false;
    }

}

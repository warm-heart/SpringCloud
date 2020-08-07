package com.micro.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangqianlong
 * @create 2020-07-06 21:19
 */
@SpringBootTest
public class RedisLuaTests  {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void redisLua() {

        RedisScript<Long> script = RedisScript
                .of(new ClassPathResource("redis.lua"),Long.class);
        List<String> keys = Arrays.asList("hello");
//        RedisScript<String> script = RedisScript
//                .of("return redis.call('set',KEYS[1],ARGV[1])",String.class);
//        RedisScript<Long> script = RedisScript
//                .of("return 1",Long.class);

//        DefaultRedisScript<String> script =
//                new DefaultRedisScript<>("return redis.call('set',KEYS[1],ARGV[1])",String.class);
        Object o = stringRedisTemplate.execute(script, keys, "world");
        System.err.println(o instanceof Long);
        System.err.println(o);
    }
}

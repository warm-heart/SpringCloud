package com.micro.produce.config;

import io.lettuce.core.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @author wql
 * @Description
 * @create 2020-11-06 14:55
 */
//@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        String expiredKey = message.toString();
        String value = stringRedisTemplate.opsForValue().get(expiredKey);
        log.info(expiredKey);
        log.info(value);
        if (expiredKey.startsWith("zeus:order")) {
            //TODO
        }
    }
}

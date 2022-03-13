package com.micro.produce.config;

import io.lettuce.core.RedisURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;

import java.time.Duration;

/**
 * @author wangqianlong
 * @create 2020-08-16 19:45
 */
@Configuration
public class RedisSentinelConfig {

    // @Bean
    // public RedisConnectionFactory redisConnectionFactory() {
    //     LettucePoolingClientConfiguration lettuceClientConfiguration =
    //             LettucePoolingClientConfiguration.builder()
    //                     .shutdownTimeout(Duration.ofMillis(100))
    //                     .commandTimeout(Duration.ofSeconds(RedisURI.DEFAULT_TIMEOUT))
    //                     .build();
    //
    //     //哨兵
    //     RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration()
    //             .master("master")
    //             .sentinel("127.0.0.1", 26379)
    //             .sentinel("127.0.0.1", 26380)
    //             .sentinel("localhost", 26381);
    //     return new LettuceConnectionFactory(sentinelConfiguration,lettuceClientConfiguration);
    //
    // }

}

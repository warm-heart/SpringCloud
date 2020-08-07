package com.micro.produce;

import com.warm.heart.redis.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProduceApplicationTests {
    @Autowired
    private RedisService redisService;

    @Test
    void contextLoads() {
        // redisService.putForString("name","111",1L);
        // String res = (String) redisService.getForString("name");
        // System.err.println(res);
        redisService.expire("name",8L);
    }

}

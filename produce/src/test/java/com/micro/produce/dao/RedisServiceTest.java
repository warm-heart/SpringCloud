package com.micro.produce.dao;

import com.micro.commons.util.IDUtils;
import com.micro.produce.ProduceApplicationTests;
import com.warm.heart.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author wangqianlong
 * @create 2020-08-13 21:19
 */
@Slf4j
public class RedisServiceTest extends ProduceApplicationTests {

    @Autowired
    private RedisService redisService;
    @Autowired
    RedisTemplate redisTemplate;

    private static Integer count = 100;
    private static Integer succ = 0;
    private static AtomicInteger exec = new AtomicInteger(0);
    private static AtomicInteger fail = new AtomicInteger(0);
    CountDownLatch countDownLatch = new CountDownLatch(100000);

    private static String productId = "productId";

    @Test
    public void redis() {
        redisService.putForString("name", "111", 1L);
        String value = (String) redisService.getForString("name");
        System.err.println(value);
    }

    @Test
    public void redisLock() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 8, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(50000), new ThreadPoolExecutor.CallerRunsPolicy());

        Long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            executor.execute(() -> {
                exec.incrementAndGet();
                String value = String.valueOf(IDUtils.genId());
                if (getLock(productId, value)) {
                    try {
                        if (count > 0) {
                            count--;
                            succ++;
                        } else {
                            //获取锁成功但是库存小于0
                             fail.incrementAndGet();
                        }
                    } finally {
                        releaseLock(productId, value);
                    }
                } else {
                    //获取锁失败
                    fail.incrementAndGet();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executor.shutdown();
        log.info("剩余库存:{}", count);
        log.info("总共人数:{}", exec);
        log.info("抢购失败的人数：{}", fail);
        log.info("抢购成功的人数：{}", succ);
        //本地机器 100000个耗时15s 50000个8.9s
        log.info("总共耗时：{}", System.currentTimeMillis() - start);
    }

    public boolean getLock(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, 5L, TimeUnit.SECONDS);
    }

    public boolean releaseLock(String key, String value) {

        RedisScript<Long> script = RedisScript
                .of(new ClassPathResource("releaseLock.lua"), Long.class);
        List<String> keys = Arrays.asList(key);
        Long res = (Long) redisTemplate.execute(script, keys, value);
        if (res == 1) {
            return true;
        }
        return false;
    }

    @Test
    public void releaseLockTest() {
        // redisTemplate.opsForValue().set("name","111");
        boolean res = releaseLock("name", "222");
        System.err.println(res);
    }


}

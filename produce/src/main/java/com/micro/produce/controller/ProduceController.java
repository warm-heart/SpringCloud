package com.micro.produce.controller;

import com.micro.commons.dto.ApiResponse;
import com.micro.commons.entity.User;
import com.micro.produce.service.ProduceService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;


/**
 * @author wangqianlong
 * @create 2020-04-22 10:43
 */
@RestController
@Slf4j
public class ProduceController {
    @Resource
    ProduceService produceService;

    @Resource
    RedissonClient redissonClient;

    @RequestMapping("/get")
    public ApiResponse<User> get111(@RequestParam String userId, HttpServletRequest request) {

        //获取分布式锁
        RLock lock = redissonClient.getLock("PURCHASE");
        lock.lock();

        log.info("进入生产端controller，参数：{}", userId);
//        return produceService.get(userId);
//        System.err.println(request.getHeader("token"));
        return ApiResponse.success(new User());
    }

    @RequestMapping("/user/get1")
    public ApiResponse<User> get1(HttpServletRequest request) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return produceService.get("1");
    }

    @RequestMapping("/user/{id}")
    public ApiResponse<User> getBypath(@PathVariable String id) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return produceService.get(id);
    }

    @PostMapping("/post")
    public ApiResponse<User> get(@RequestBody User user) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("进入生产端controller，参数：{}", user.getUserId());
        return produceService.get(user.getUserId());
    }

}

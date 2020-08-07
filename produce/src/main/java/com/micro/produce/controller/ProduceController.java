package com.micro.produce.controller;

import com.micro.commons.dto.ApiResponse;
import com.micro.commons.entity.User;
import com.micro.produce.service.ProduceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author wangqianlong
 * @create 2020-04-22 10:43
 */
@RestController
@Slf4j
public class ProduceController {
    @Autowired
    ProduceService produceService;

    @RequestMapping("/get")
    public ApiResponse<User> get(@RequestParam String userId) {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        log.info("进入生产端controller，参数：{}", userId);
        return produceService.get(userId);
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

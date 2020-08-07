package com.micro.consume.controller;

import com.micro.commons.dto.ApiResponse;
import com.micro.commons.entity.User;
import com.micro.consume.feign.produce1.Produce1FeignService;
import com.micro.consume.feign.produce.ProduceFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangqianlong
 * @create 2020-04-22 10:50
 */
@RestController
@Slf4j
public class ConsumeController {

    @Autowired
    private ProduceFeignService produceFeignService;
    @Autowired
    private Produce1FeignService produce1FeignService;


    /**
     * @param userID
     * @return
     */
    @RequestMapping("feignGet")
    public ApiResponse<User> feignGet(String userID) {
        log.info("进入消费端controller ，参数：{}", userID);
        return produceFeignService.get(userID);
    }


    /**
     * @param userID
     * @return
     */
    @RequestMapping("getBypath")
    public ApiResponse<User> getBypath(String userID) {
        log.info("进入消费端controller ，参数：{}", userID);
        return produceFeignService.getBypath(userID);
    }


    /**
     * @param userID
     * @return
     */
    @RequestMapping("/produce1/hello")
    public String hello(String userID) {
        log.info("进入消费端controller ，参数：{}", userID);
        return produce1FeignService.hello();
    }

}

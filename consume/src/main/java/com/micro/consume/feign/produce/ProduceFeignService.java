package com.micro.consume.feign.produce;

import com.micro.commons.dto.ApiResponse;
import com.micro.commons.entity.User;
import com.micro.consume.feign.produce.fallback.ProduceFallback;
import org.springframework.cloud.openfeign.FeignClient;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author wangqianlong
 * @create 2020-04-22 10:47
 */
@Service
@FeignClient(name = "produce", fallback = ProduceFallback.class)
public interface ProduceFeignService {


    @RequestMapping("/get")
    ApiResponse<User> get(@RequestParam(name = "userId") String userId);

    @RequestMapping("/user/{id}")
    ApiResponse<User> getBypath(@PathVariable String id);
}

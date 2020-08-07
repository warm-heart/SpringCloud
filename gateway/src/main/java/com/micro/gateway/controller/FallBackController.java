package com.micro.gateway.controller;

import com.micro.commons.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangqianlong
 * @create 2020-06-02 21:43
 */
@RestController
@Slf4j
public class FallBackController {

    @RequestMapping("/fallback")
    public ApiResponse<String> fallback(){
        return ApiResponse.error("网关服务降级");
    }
}

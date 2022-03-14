package com.micro.consume;

import com.micro.commons.dto.ApiResponse;
import com.micro.commons.entity.User;
import com.micro.consume.feign.produce.ProduceFeignService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ConsumeApplicationTests {


    @Resource
    private ProduceFeignService produceFeignService;

    @Test
    void contextLoads() {
        ApiResponse<User> res = produceFeignService.get("1");
        System.err.println(res);
    }


}

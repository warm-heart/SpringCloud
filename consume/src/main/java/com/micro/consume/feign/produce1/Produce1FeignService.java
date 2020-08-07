package com.micro.consume.feign.produce1;


import com.micro.consume.feign.produce1.fallback.Produce1Fallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author wangqianlong
 * @create 2020-04-22 10:47
 */
@Service
@FeignClient(name = "produce1", fallback = Produce1Fallback.class)
public interface Produce1FeignService {
    @GetMapping("/hello")
    String hello();
}

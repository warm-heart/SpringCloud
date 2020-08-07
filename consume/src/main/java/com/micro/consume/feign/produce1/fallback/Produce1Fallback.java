package com.micro.consume.feign.produce1.fallback;

import com.micro.consume.feign.produce1.Produce1FeignService;
import org.springframework.stereotype.Component;

/**
 * @author wangqianlong
 * @create 2020-06-09 20:10
 */

@Component
public class Produce1Fallback implements Produce1FeignService {

    @Override
    public String hello() {
        return "produce1 服务降级";
    }
}

package com.micro.consume.feign.produce.fallback;

import com.micro.commons.dto.ApiResponse;
import com.micro.commons.entity.User;
import com.micro.consume.feign.produce.ProduceFeignService;
import org.springframework.stereotype.Component;

/**
 * @author wangqianlong
 * @create 2020-04-22 11:22
 */
@Component
public class ProduceFallback implements ProduceFeignService {


    @Override
    public ApiResponse<User> get(String userId) {
        return ApiResponse.error("produce服务降级");
    }

    @Override
    public ApiResponse<User> getBypath(String id) {
        return ApiResponse.error("produce getBypath服务降级");
    }
}

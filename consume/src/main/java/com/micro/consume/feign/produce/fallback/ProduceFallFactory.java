package com.micro.consume.feign.produce.fallback;

import com.micro.commons.dto.ApiResponse;
import com.micro.commons.entity.User;
import com.micro.consume.feign.produce.ProduceFeignService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author wangqianlong
 * @create 2020-04-22 11:22
 */
@Component
public class ProduceFallFactory implements FallbackFactory {


    @Override
    public ProduceFeignService create(Throwable throwable) {
        return new ProduceFeignService() {
            @Override
            public ApiResponse<User> get(String userId) {
                return ApiResponse.error("FallbackFactory服务降级");
            }

            @Override
            public ApiResponse<User> getBypath(String id) {
                return ApiResponse.error("FallbackFactorygetBypath服务降级");
            }
        };
    }
}

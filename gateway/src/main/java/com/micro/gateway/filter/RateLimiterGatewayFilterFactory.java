package com.micro.gateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.HasRouteId;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import java.util.List;
import static java.util.Collections.singletonList;

/**
 * @author wangqianlong
 * @create 2020-06-06 11:42
 */
@Component
@Slf4j
public class RateLimiterGatewayFilterFactory extends
        AbstractGatewayFilterFactory<RateLimiterGatewayFilterFactory.Config> implements InitializingBean {

    @Value("${permitsPerSecond}")
    private Double permitsPerSecond;
    RateLimiter rateLimiter;


    @Override
    public void afterPropertiesSet() {
        rateLimiter = RateLimiter.create(permitsPerSecond);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        //return Arrays.asList("maxSize", "minSize");
        return singletonList(NAME_KEY);
    }

    public RateLimiterGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            log.info("进入限流器");
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
           log.info("{}",request.getPath());
            boolean suc = rateLimiter.tryAcquire(config.permits);
            if (!suc) {
                response.setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                return response.setComplete();
            }
            ServerHttpRequest.Builder builder = request.mutate();
            return chain.filter(exchange.mutate().request(builder.build()).build());
        });
    }

    @Data
    public static class Config implements HasRouteId {
        private Integer permits;

        private String routeId;

        @Override
        public void setRouteId(String routeId) {
            this.routeId = routeId;
        }

        @Override
        public String getRouteId() {
            return routeId;
        }
    }
}

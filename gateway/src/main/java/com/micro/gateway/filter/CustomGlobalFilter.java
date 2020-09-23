package com.micro.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author wangqianlong
 * @create 2020-06-01 21:16
 */
@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("custom global filter");
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders httpHeaders = request.getHeaders();
        //获取http header数据，可以权限鉴定用
        String token=httpHeaders.getFirst("token");
        log.info("{}", request.getHeaders().getFirst("token"));
        //获取request里的数据
        String jwt=request.getQueryParams().getFirst("jwt");
       // System.err.println(jwt);
      /*  if (StringUtils.isEmpty(jwt)){
            response.setStatusCode(HttpStatus.BAD_GATEWAY);
            return response.setComplete();
        }*/
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 10000;
    }
}

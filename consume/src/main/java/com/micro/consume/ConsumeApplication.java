package com.micro.consume;

import com.netflix.loadbalancer.*;
import org.apache.ibatis.javassist.CtMethod;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

import java.lang.instrument.ClassFileTransformer;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableRetry
public class ConsumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumeApplication.class, args);
    }

}

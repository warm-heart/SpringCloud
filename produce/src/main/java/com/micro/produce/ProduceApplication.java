package com.micro.produce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class ProduceApplication {

    private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ProduceApplication.class, args);

        String location = "classpath*:releaseLock.lua";
        Resource[] resources = resourceResolver.getResources(location);

        System.err.println(resources);
        //www.baidu.com/cs/hostRunning   //第一个参数使用https
//        PingUrl p = new PingUrl(false, "");
//        // p.setExpectedContent("true");
//        Server s = new Server("www.baidu.com", 80);
//
//        boolean isAlive = p.isAlive(s);
//        System.out.println("isAlive:" + isAlive);

    }

}

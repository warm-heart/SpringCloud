package com.micro.produce;

import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProduceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProduceApplication.class, args);


        //www.baidu.com/cs/hostRunning   //第一个参数使用https
//        PingUrl p = new PingUrl(false, "");
//        // p.setExpectedContent("true");
//        Server s = new Server("www.baidu.com", 80);
//
//        boolean isAlive = p.isAlive(s);
//        System.out.println("isAlive:" + isAlive);

    }

}

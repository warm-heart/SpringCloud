package com.mircoservice.produce1;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class Produce1Application {
    @Value("${server.port}")
    String port;

    public static void main(String[] args) {
        SpringApplication.run(Produce1Application.class, args);
    }


    @GetMapping("/hello")
    public String hello() throws InterruptedException {
       // Thread.sleep(2000);
        return "hello produce1"+port;
    }
}

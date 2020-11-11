package com.micro.produce.aspect;


import com.micro.produce.annotation.AfterRateLimit;
import com.micro.produce.annotation.RateLimit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author wangqianlong
 * @create 2019-04-28 19:51
 */

@Aspect
@Component
@Slf4j
public class RateLimitAspect {

    //声明一个切入点  相当于xml配置中的pointcut标签
    @Pointcut("execution(* com.micro.produce.service.Impl.ProduceServiceImpl.get(..))")
    public void anyMethod() {
    }


    @Before("@annotation(rateLimit)")
    public void rateLimit(JoinPoint joinPoint, RateLimit rateLimit) {
        Integer permitsPer = rateLimit.permitsPer();
        log.info("current permitPer is {}", permitsPer);
        //todo 了解机制原理
        LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue<>();
    }


    @After("anyMethod()")
    public void doAfter() {
        System.out.println("后置通知");
    }

}

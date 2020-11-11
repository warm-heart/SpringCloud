package com.micro.produce.annotation;

import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Transactional
public @interface RateLimit {
    int permitsPer() default 10;
}

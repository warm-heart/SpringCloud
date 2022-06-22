package com.micro.produce.Interceptor;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author wangqianlong
 * @create 2020-08-02 21:52
 */

@Component
public class RateLimitInterceptor implements HandlerInterceptor {
    RateLimiter rateLimiter = RateLimiter.create(2);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//        Annotation[] annotations = method.getDeclaredAnnotations();
//        Object o = handlerMethod.getBean();
//        if (rateLimiter.tryAcquire(1)) {
//            return true;
//        }
//        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
//        return false;
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

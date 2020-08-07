package com.micro.produce.exception;


import com.micro.commons.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;

/**
 * @author wangqianlong
 * @create 2018-09-14 20:00
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse Exception(Exception e, HttpServletRequest request) {
        log.error("服务器异常 , uri: {} , caused by :{} ", request.getRequestURI(), e);

        return ApiResponse.error("服务器错误，请稍后重试");
    }


}

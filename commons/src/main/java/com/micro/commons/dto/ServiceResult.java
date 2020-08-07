package com.micro.commons.dto;

import lombok.Data;

/**
 * 服务接口通用结构
 *
 * @author wangqianlong
 * @create 2019-05-01 19:05
 */

@Data
public class ServiceResult<T> {
    private boolean success;
    private String message;
    private T result;

    @Deprecated
    public ServiceResult(boolean success) {
        this.success = success;
    }

    public ServiceResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ServiceResult(boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }


    public static <T> ServiceResult<T> success(T result) {
        ServiceResult<T> serviceResult = new ServiceResult<>(true);
        serviceResult.setResult(result);
        return serviceResult;
    }

    public static <T> ServiceResult<T> error(String message) {
        ServiceResult<T> serviceResult = new ServiceResult<>(false);
        serviceResult.setMessage(message);
        return serviceResult;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

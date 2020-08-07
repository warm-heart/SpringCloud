package com.micro.produce.service;

import com.micro.commons.dto.ApiResponse;
import com.micro.commons.entity.User;
import com.micro.produce.dao.UserDao;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangqianlong
 * @create 2020-04-22 10:43
 */
@Service
@Slf4j
public class ProduceService {
    @Autowired
    UserDao userDao;


    public ApiResponse<User> get(String userId) {
       // log.info("进入生产端service，端口:8100，参数：{}", userId);
        User user = userDao.getUserByUserId(userId);
        if (null != user)
            return ApiResponse.success(user);
        return ApiResponse.error("没找到用户");
    }

}

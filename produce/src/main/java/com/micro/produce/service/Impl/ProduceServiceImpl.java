package com.micro.produce.service.Impl;

import com.micro.commons.dto.ApiResponse;
import com.micro.commons.entity.User;
import com.micro.commons.util.IDUtils;
import com.micro.produce.annotation.RateLimit;
import com.micro.produce.controller.ProduceController;
import com.micro.produce.dao.UserDao;
import com.micro.produce.service.ProduceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author wangqianlong
 * @create 2020-04-22 10:43
 */
@Service
@Slf4j
public class ProduceServiceImpl implements ProduceService {
    @Autowired
    private UserDao userDao;

    @Override
    public ApiResponse<User> get(String userId) {
        log.info("进入生产端service，端口:8100，参数：{}", userId);
        User user = userDao.getUserByUserId(userId);
        if (null != user)
            return ApiResponse.success(user);
        return ApiResponse.error("没找到用户");

    }

    @Transactional(propagation = Propagation.NEVER, isolation = Isolation.REPEATABLE_READ, rollbackFor = RuntimeException.class)
    @RateLimit(permitsPer = 1000)
    @Override
    public Integer addUser() {
        User user = new User();
        user.setUserName("ooo");
        user.setPassword("ooo😘");
        user.setUserId(String.valueOf(IDUtils.genId()));
        user.setCreateTime(LocalDateTime.now());
        Integer res = userDao.insert(user);
        return res;
    }

}

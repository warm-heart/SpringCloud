package com.micro.produce.service;

import com.micro.commons.dto.ApiResponse;
import com.micro.commons.entity.User;
import com.micro.commons.util.IDUtils;
import com.micro.produce.annotation.RateLimit;
import com.micro.produce.dao.UserDao;
import lombok.extern.slf4j.Slf4j;

import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author wangqianlong
 * @create 2020-04-22 10:43
 */
public interface ProduceService {


    public ApiResponse<User> get(String userId);

    public Integer addUser();

}

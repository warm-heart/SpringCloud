package com.micro.commons.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author wangqianlong
 * @create 2020-04-22 12:37
 */
@Data
public class User {
    private String userId;
    private String userName;
    private String password;
    private LocalDateTime CreateTime;
}

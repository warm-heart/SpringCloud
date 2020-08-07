package com.micro.produce.dao;

import com.micro.produce.ProduceApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;



class UserDaoTest extends ProduceApplicationTests {
    @Autowired
    UserDao userDao;

    @Test
    void test() {
        System.out.println(userDao.getUserByUserId("1"));
    }


}
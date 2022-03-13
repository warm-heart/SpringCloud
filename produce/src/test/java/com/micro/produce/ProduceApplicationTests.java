package com.micro.produce;


import com.micro.produce.dao.UserDao;
import com.micro.produce.service.Impl.ProduceServiceImpl;
import com.micro.produce.service.ProduceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ProduceApplicationTests {
    @Autowired
    private ProduceService produceService;
    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {
         Integer res = produceService.addUser();
        // System.err.println(res);
        // System.err.println(userDao.getUserByUserId("83337312073125888"));
    }
}

package com.micro.commons;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class CommonsApplicationTests {

    @Test
    void contextLoads() {
        List list = solution(100, 10);
        System.err.println(list);
    }


    public List<BigDecimal> solution(int maneySum, int redNum) {
        ArrayList array = new ArrayList(redNum);
        BigDecimal totalManey = new BigDecimal(maneySum);
        BigDecimal halfMoney = totalManey.divide(new BigDecimal(2), 0, BigDecimal.ROUND_DOWN);
        BigDecimal maxRedPage = new BigDecimal(90);
        for (int i = 0; i < redNum; i++) {
            Random random = new Random();
            Double v = random.nextDouble();
            BigDecimal bigDecimal = totalManey.multiply(new BigDecimal(v));
            BigDecimal multiply = totalManey.multiply(bigDecimal);
            if (maxRedPage.compareTo(multiply) >= 0) {
                if (multiply.compareTo(halfMoney) > 0) {
                    multiply = multiply.subtract(halfMoney);
                }
                if (multiply.compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }
                multiply = multiply.setScale(2, BigDecimal.ROUND_DOWN);
                array.add(multiply);
                totalManey = totalManey.subtract(multiply);
            }
        }
        return array;
    }
}

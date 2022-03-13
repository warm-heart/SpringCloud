package com.micro.commons;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootTest
class CommonsApplicationTests {

    @Test
    void contextLoads() {
        List<BigDecimal> res = solution(99, 10);
        BigDecimal total = res.stream().reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        System.err.println(res);
        System.err.println(total);
    }

    public List<BigDecimal> solution(int money, int count) {
        BigDecimal totalMoney = new BigDecimal(money);
        BigDecimal loadFactory = new BigDecimal(10000);
        BigDecimal once = totalMoney.divide(loadFactory, 2, BigDecimal.ROUND_HALF_DOWN);
        int forCount = totalMoney.divide(once, 2, BigDecimal.ROUND_HALF_DOWN).intValue();
        BigDecimal[] result = new BigDecimal[count];
        for (int i = 1; i <= forCount; i++) {
            int n = new Random().nextInt(count);
            result[n] = result[n] == null ? once : result[n].add(once);
        }
        return Arrays.stream(result).collect(Collectors.toList());
    }
}
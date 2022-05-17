package com.micro.consume;

import com.micro.commons.dto.ApiResponse;
import com.micro.commons.entity.User;
import com.micro.consume.feign.produce.ProduceFeignService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class ConsumeApplicationTests {


    @Resource
    private ProduceFeignService produceFeignService;

    @Test
    void contextLoads() {
        ApiResponse<User> res = produceFeignService.get("1");
        System.err.println(res);
    }

    @Test
    void partition() {

        List<String> strings = new ArrayList<>(100000000);
        for (int i = 0; i < 100000000; i++) {
            strings.add(String.valueOf(i));
        }
        Integer size = 50;
        Long start = System.currentTimeMillis();
        List<List<String>> res = doPartition(strings, size);

        Long current = System.currentTimeMillis();
        System.err.println("size=" + res.size() + "------" + "耗时=" + (current - start));
    }


    private List<List<String>> doPartition(List<String> elements,
                                           Integer partitionSize) {
        if (CollectionUtils.isEmpty(elements)) {
            return Collections.emptyList();
        }
        if (elements.size() <= partitionSize) {
            return Collections.singletonList(elements);
        }

        //游标，不断增长
        int start = 0;
        int count = elements.size() % partitionSize == 0 ? elements.size() / partitionSize : elements.size() / partitionSize + 1;
        List<List<String>> res = new ArrayList<>(count);
        for (int i = 0; i <= count; i++) {
            List<String> partitionElements = new ArrayList<>();
            int end = partitionSize;
            //剩余元素数
            int surplusElement = elements.size() - 1 - start;
            if (surplusElement < partitionSize) {
                end = surplusElement;
            }
            for (int j = start; j < end; j++) {
                partitionElements.add(elements.get(j));
            }
            start += partitionSize;
            res.add(partitionElements);
        }
        return res;
    }

}

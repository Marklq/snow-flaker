package com.dd.snowflaker.service.Impl;

import com.dd.snowflaker.dao.IdGeneratorSnowFlake;
import com.dd.snowflaker.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Package: com.dd.snowflaker.service.Impl
 * @ClassName: OrderServiceImpl
 * @Author: 东方不败
 * @CreateTime: 2020-10-04 16:08
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private IdGeneratorSnowFlake idGenerator;

    @Override
    public String getIdBySnowFlake() {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 20; i++) {
            threadPool.submit(() -> {
                System.out.println(idGenerator.snowflakeId());
            });
        }
        threadPool.shutdown();
        return "hello snowflaker";
    }
}

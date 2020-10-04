package com.dd.snowflaker.controller;

import com.dd.snowflaker.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: com.dd.snowflaker.controller
 * @ClassName: SnowFlaker
 * @Author: 东方不败
 * @CreateTime: 2020-10-04 16:05
 * @Description:
 */
@RestController
public class SnowFlakerController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/snowflaker")
    public String snowFlaker() {
        return orderService.getIdBySnowFlake();
    }


}

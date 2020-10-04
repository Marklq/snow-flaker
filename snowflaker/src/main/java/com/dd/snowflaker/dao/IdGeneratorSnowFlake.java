package com.dd.snowflaker.dao;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Package: com.dd.snowflaker.dao
 * @ClassName: IdGeneratorSnowFlake
 * @Author: 东方不败
 * @CreateTime: 2020-10-04 16:10
 * @Description: 业务逻辑类
 */
@Component
@Slf4j
public class IdGeneratorSnowFlake {

    private long workerId = 0;
    private long dataCenterId = 1;

    private Snowflake snowflake = IdUtil.createSnowflake(workerId, dataCenterId);

    @PostConstruct
    public void init() {

        try {
            //得到本机的workId
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前的机器Id：{}" + workerId);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("当前的机器ID获取失败", e);
            workerId = NetUtil.getLocalhostStr().hashCode();
        }
    }


    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workerId, long datacenterId) {

        Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        System.out.println(new IdGeneratorSnowFlake().snowflakeId());
    }

}

package com.lawu.eshop.activity.srv;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@MapperScan({"com.lawu.eshop.activity.srv.mapper"})
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@ComponentScan
@ImportResource(locations={"classpath:spring.xml"})
public class ActivitySrvApplication {

    private static Logger logger = LoggerFactory.getLogger(ActivitySrvApplication.class);

    public static void main(String[] args) {
        logger.info("activity-srv is starting");
        SpringApplication.run(ActivitySrvApplication.class, args);
        logger.info("activity-srv is started");
    }
}

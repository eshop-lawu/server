package com.lawu.eshop.member.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 会员api启动类
 * @author Leach
 * @date 2017/3/10
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.lawu.eshop"})
@Configuration
@ImportResource(locations = {"classpath:spring.xml"})
@ComponentScan(basePackages = {"com.lawu.eshop", "com.lawu.autotest.client"})
public class MemberApiApplication {

    private static Logger logger = LoggerFactory.getLogger(MemberApiApplication.class);

    public static void main(String[] args) {
        logger.info("member-api is starting");
        SpringApplication.run(MemberApiApplication.class, args);
        logger.info("member-api is started");
    }
}


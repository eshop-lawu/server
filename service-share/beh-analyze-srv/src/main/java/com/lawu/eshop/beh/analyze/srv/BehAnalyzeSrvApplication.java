package com.lawu.eshop.beh.analyze.srv;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@MapperScan({"com.lawu.eshop.beh.analyze.srv.mapper"})
@EnableDiscoveryClient
@EnableFeignClients({"com.lawu.eshop"})
@SpringBootApplication
@ImportResource(locations = {"classpath:spring.xml"})
@ComponentScan({"com.lawu.eshop"})
public class BehAnalyzeSrvApplication {

    private static Logger logger = LoggerFactory.getLogger(BehAnalyzeSrvApplication.class);

    public static void main(String[] args) {
        logger.info("beh-analyze-srv is starting");
        SpringApplication.run(BehAnalyzeSrvApplication.class, args);
        logger.info("beh-analyze-srv is started");
    }
}

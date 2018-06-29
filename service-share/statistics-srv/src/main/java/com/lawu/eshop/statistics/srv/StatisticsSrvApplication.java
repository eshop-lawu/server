package com.lawu.eshop.statistics.srv;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * 统计服务启动类
 * @author Leach
 * @date 2017/6/28
 */
@MapperScan("com.lawu.eshop.statistics.srv.mapper")
@EnableDiscoveryClient
@EnableFeignClients({"com.lawu.eshop"})
@SpringBootApplication
@ImportResource(locations={"classpath:spring.xml"})
@ComponentScan({"com.lawu.eshop"})
public class StatisticsSrvApplication {

    private static Logger logger = LoggerFactory.getLogger(StatisticsSrvApplication.class);

    public static void main(String[] args) {
        logger.info("statistics-srv is starting");
        SpringApplication.run(StatisticsSrvApplication.class, args);
        logger.info("statistics-srv is started");
    }
}

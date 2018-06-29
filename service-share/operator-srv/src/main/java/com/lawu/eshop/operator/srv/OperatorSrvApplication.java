package com.lawu.eshop.operator.srv;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 运营平台服务启动类
 * @author Sunny
 * @date 2017/3/10
 */
@MapperScan("com.lawu.eshop.operator.srv.mapper")
@EnableDiscoveryClient
@EnableFeignClients({"com.lawu.eshop"})
@SpringBootApplication
@ComponentScan({"com.lawu.eshop"})
public class OperatorSrvApplication {

    private static Logger logger = LoggerFactory.getLogger(OperatorSrvApplication.class);

    public static void main(String[] args) {
        logger.info("operator-srv is starting");
        SpringApplication.run(OperatorSrvApplication.class, args);
        logger.info("operator-srv is started");
    }
}

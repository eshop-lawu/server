package com.lawu.eshop.order.srv;

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
 * 订单服务启动类
 * @author Sunny
 * @date 2017/3/27
 */
@MapperScan({"com.lawu.eshop.order.srv.mapper"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages="com.lawu.eshop")
@SpringBootApplication
@ComponentScan({"com.lawu.eshop"})
@ImportResource(locations={"classpath:spring.xml"})
public class OrderSrvApplication {

    private static Logger logger = LoggerFactory.getLogger(OrderSrvApplication.class);

    public static void main(String[] args) {
        logger.info("order-srv is starting");
        SpringApplication.run(OrderSrvApplication.class, args);
        logger.info("order-srv is started");
    }
}

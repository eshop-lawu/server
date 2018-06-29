package com.lawu.eshop.eureka.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * eureka服务启动类
 *
 * @author Leach
 * @date 2017/3/10
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    private static Logger logger = LoggerFactory.getLogger(EurekaServerApplication.class);

    public static void main(String[] args) {

        logger.info("eureka-server is starting");
        SpringApplication.run(EurekaServerApplication.class, args);
        logger.info("eureka-server is started");


    }
}

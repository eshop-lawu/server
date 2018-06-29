package com.lawu.eshop.config.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 配置服务器启动类
 * @author Leach
 * @date 2017/3/7
 */
@Configuration
@EnableAutoConfiguration
//@EnableDiscoveryClient
@EnableConfigServer
public class ConfigServerApplication {

    private static Logger logger = LoggerFactory.getLogger(ConfigServerApplication.class);


    public static void main(String[] args) {
        logger.info("config-server is starting");
        SpringApplication.run(ConfigServerApplication.class, args);
        logger.info("config-server is started");
    }

}
package com.lawu.eshop.game.srv;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@MapperScan({"com.lawu.eshop.game.srv.mapper"})
@EnableDiscoveryClient
@EnableFeignClients({"com.lawu.eshop"})
@SpringBootApplication
@ComponentScan({"com.lawu.eshop"})
@ImportResource(locations={"classpath:spring.xml"})
public class GameSrvApplication {

    private static Logger logger = LoggerFactory.getLogger(GameSrvApplication.class);

    public static void main(String[] args) {
        logger.info("game-srv is starting");
        SpringApplication.run(GameSrvApplication.class, args);
        logger.info("game-srv is started");
    }
}

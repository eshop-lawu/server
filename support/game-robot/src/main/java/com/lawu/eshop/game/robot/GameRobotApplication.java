package com.lawu.eshop.game.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年5月10日
 * @updateDate 2018年5月10日
 */
@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"com.lawu.eshop"})
public class GameRobotApplication {

    private static Logger logger = LoggerFactory.getLogger(GameRobotApplication.class);
    
    public static void main(String[] args) {
        logger.info("game-robot is starting");
        new SpringApplication(GameRobotApplication.class).run(args);
        logger.info("game-robot is started");
    }
    
}

package com.lawu.eshop.agent.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Leach
 * @date 2017/3/13
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.lawu.eshop"})
@ImportResource(locations = {"classpath:spring.xml"})
@ComponentScan(basePackages={"com.lawu.eshop"})
public class AgentApiApplication {

    private static Logger logger = LoggerFactory.getLogger(AgentApiApplication.class);

    public static void main(String[] args) {
        logger.info("agent-api is starting");
        SpringApplication.run(AgentApiApplication.class, args);
        logger.info("agent-api is started");
    }
}

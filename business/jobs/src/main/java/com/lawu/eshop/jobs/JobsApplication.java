package com.lawu.eshop.jobs;

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
 * @author Leach
 * @date 2017/4/24
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages={"com.lawu.eshop"})
@Configuration
@ImportResource(locations={"classpath:spring.xml"})
@ComponentScan(basePackages={"com.lawu.eshop"})
public class JobsApplication {

    private static Logger logger = LoggerFactory.getLogger(JobsApplication.class);

    public static void main(String[] args) {
        logger.info("jobs is starting");
        SpringApplication.run(JobsApplication.class, args);
        logger.info("jobs is started");
    }
}

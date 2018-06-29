package com.lawu.eshop.external.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年4月12日 下午2:44:55
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Configuration
@ComponentScan(basePackages={"com.lawu.eshop"})
public class ExternalApiApplication {
	
	private static Logger logger = LoggerFactory.getLogger(ExternalApiApplication.class);
			
	public static void main(String[] args) {
        logger.info("external-api is starting");
        SpringApplication.run(ExternalApiApplication.class, args);
        logger.info("external-api is started");
    }
}

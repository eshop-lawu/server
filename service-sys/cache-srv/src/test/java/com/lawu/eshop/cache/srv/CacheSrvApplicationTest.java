package com.lawu.eshop.cache.srv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * 用户服务启动类
 * @author Leach
 * @date 2017/3/10
 */
@ComponentScan(value = {"com.lawu.eshop"}, excludeFilters = {@ComponentScan.Filter(value = {CacheSrvApplication.class}, type = FilterType.ASSIGNABLE_TYPE)})
@SpringBootApplication
public class CacheSrvApplicationTest {

    private static Logger logger = LoggerFactory.getLogger(CacheSrvApplicationTest.class);

    public static void main(String[] args) {
        logger.info("cache-srv is starting");
        SpringApplication.run(CacheSrvApplicationTest.class, args);
        logger.info("cache-srv is started");
    }
}

package com.lawu.eshop.beh.analyze.srv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

/**
 * @author zhangyong
 * @date 2018/1/29.
 */
@ComponentScan(value = {"com.lawu"}, excludeFilters = {@ComponentScan.Filter(value = {BehAnalyzeSrvApplication.class}, type = FilterType.ASSIGNABLE_TYPE)})
@SpringBootApplication
@ImportResource(locations={"classpath:spring-test.xml"})
public class BehAnalyzeSrvApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(BehAnalyzeSrvApplicationTest.class, args);
    }
}

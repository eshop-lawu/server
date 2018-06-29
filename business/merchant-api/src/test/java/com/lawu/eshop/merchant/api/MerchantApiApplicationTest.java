package com.lawu.eshop.merchant.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

@ComponentScan(value = {"com.lawu.eshop"}, excludeFilters = {@ComponentScan.Filter(value = {MerchantApiApplication.class}, type = FilterType.ASSIGNABLE_TYPE)})
@SpringBootApplication
@ImportResource(locations={"classpath:spring-test.xml"})
public class MerchantApiApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(MerchantApiApplicationTest.class, args);
    }
}

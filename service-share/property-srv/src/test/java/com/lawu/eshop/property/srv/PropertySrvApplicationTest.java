package com.lawu.eshop.property.srv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan({"com.lawu.eshop.property.srv.mapper"})
@EnableTransactionManagement
@ComponentScan(value = {"com.lawu"}, excludeFilters = {@ComponentScan.Filter(value = {PropertySrvApplication.class}, type = FilterType.ASSIGNABLE_TYPE)})
@SpringBootApplication
@ImportResource(locations={"classpath:spring-test.xml"})
public class PropertySrvApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(PropertySrvApplicationTest.class, args);
    }
}

package com.lawu.eshop.statistics.srv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.lawu.eshop.statistics.srv.mapper")
@EnableTransactionManagement
@ComponentScan(value = {"com.lawu"}, excludeFilters = {@ComponentScan.Filter(value = {StatisticsSrvApplication.class}, type = FilterType.ASSIGNABLE_TYPE)})
@SpringBootApplication
@ImportResource(locations={"classpath:spring-test.xml"})
public class StatisticsSrvApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(StatisticsSrvApplicationTest.class, args);
    }
}

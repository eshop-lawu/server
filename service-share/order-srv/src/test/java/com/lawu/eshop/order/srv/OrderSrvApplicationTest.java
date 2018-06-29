package com.lawu.eshop.order.srv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.lawu.eshop.order.srv.strategy.ExpressStrategy;

@MapperScan({"com.lawu.eshop.order.srv.mapper"})
@EnableTransactionManagement
@ComponentScan(value = {"com.lawu.eshop"}, excludeFilters = {@ComponentScan.Filter(value = {OrderSrvApplication.class}, type = FilterType.ASSIGNABLE_TYPE), @ComponentScan.Filter(value = {ExpressStrategy.class}, type = FilterType.ASSIGNABLE_TYPE)})
@SpringBootApplication
@ImportResource(locations={"classpath:spring-test.xml"})
public class OrderSrvApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(OrderSrvApplicationTest.class, args);
    }
}

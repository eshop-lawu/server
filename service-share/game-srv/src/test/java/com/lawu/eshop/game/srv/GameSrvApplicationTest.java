package com.lawu.eshop.game.srv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan({"com.lawu.eshop.game.srv.mapper"})
@EnableTransactionManagement
@ComponentScan(value = {"com.lawu"}, excludeFilters = {@ComponentScan.Filter(value = {GameSrvApplication.class}, type = FilterType.ASSIGNABLE_TYPE)})
@SpringBootApplication
@ImportResource(locations={"classpath:spring-test.xml"})
public class GameSrvApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(GameSrvApplicationTest.class, args);
    }
}

package com.lawu.eshop.jobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

@ComponentScan(value = {"com.lawu.eshop"}, excludeFilters = {@ComponentScan.Filter(value = {JobsApplication.class}, type = FilterType.ASSIGNABLE_TYPE)})
@SpringBootApplication
@ImportResource(locations={"classpath:spring-test.xml"})
public class JobsApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(JobsApplicationTest.class, args);
    }
}

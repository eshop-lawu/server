package com.lawu.eshop.activity.srv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * 活动服务测试启动类
 * @author jiangxinjun
 * @createDate 2017年12月28日
 * @updateDate 2017年12月28日
 */
@MapperScan({"com.lawu.eshop.activity.srv.mapper"})
@ComponentScan(value = {"com.lawu"}, excludeFilters = {@ComponentScan.Filter(value = {ActivitySrvApplication.class}, type = FilterType.ASSIGNABLE_TYPE)})
@SpringBootApplication
public class ActivitySrvApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(ActivitySrvApplicationTest.class, args);
    }
}

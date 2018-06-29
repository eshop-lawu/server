package com.lawu.eshop.member.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

import com.lawu.authorization.interceptor.AuthorizationInterceptor;

/**
 * <p>单元测试 </p>
 *
 * @author yangqh
 * @date 2017/7/24 15:35
 */
@ComponentScan(value = {"com.lawu.eshop"}, excludeFilters = {@ComponentScan.Filter(value = {MemberApiApplication.class}, type = FilterType.ASSIGNABLE_TYPE)})
@SpringBootApplication
@ImportResource(locations={"classpath:spring-test.xml"})
public class MemberApiApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(MemberApiApplicationTest.class, args);
    }

    @Bean
    public AuthorizationInterceptor authorizationInterceptor(){
        AuthorizationInterceptor authorizationInterceptor = new AuthorizationInterceptor();
        authorizationInterceptor.setDebug(true);
        authorizationInterceptor.setTestId(1L);
        authorizationInterceptor.setTestAccount("1234567");
        authorizationInterceptor.setTestNum("M00002");
        authorizationInterceptor.setHttpHeaderName("Authorization");
        return authorizationInterceptor;
    }
}

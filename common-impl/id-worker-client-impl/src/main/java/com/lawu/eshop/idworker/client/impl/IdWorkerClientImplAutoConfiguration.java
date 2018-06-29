package com.lawu.eshop.idworker.client.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lawu.idworker.client.service.IdWorkerService;


/**
 * IdWorkerClientImpl自动配置类
 * 
 * @author jiangxinjun
 * @createDate 2018年4月16日
 * @updateDate 2018年4月16日
 */
@ConditionalOnClass(IdWorkerService.class)
@ConditionalOnMissingBean(IdWorkerService.class)
@Configuration
public class IdWorkerClientImplAutoConfiguration {

    @Bean
    public IdWorkerService idWorkerService() {
        return new MockIdWorkerServiceImpl();
    }

}

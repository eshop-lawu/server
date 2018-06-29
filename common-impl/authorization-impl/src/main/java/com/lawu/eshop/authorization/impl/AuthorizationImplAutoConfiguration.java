package com.lawu.eshop.authorization.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AuthorizationImpl自动配置类
 * @author jiangxinjun
 * @createDate 2018年2月6日
 * @updateDate 2018年2月6日
 */
@Configuration
@ConditionalOnProperty(value = "com.lawu.authorization-impl.token-cache.enabled", havingValue = "true", matchIfMissing = false)
public class AuthorizationImplAutoConfiguration {
    
    @ConfigurationProperties(prefix = "com.lawu.authorization-impl.token-cache")
    @Bean
    public RedisTokenCacheServiceImpl redisTokenCacheServiceImpl() {
        RedisTokenCacheServiceImpl redisTokenCacheServiceImpl = new RedisTokenCacheServiceImpl();
        return redisTokenCacheServiceImpl;
    }
    
}

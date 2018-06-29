package com.lawu.eshop.framework.web.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * FrameworkWebImpl自动配置类
 * @author jiangxinjun
 * @createDate 2018年4月8日
 * @updateDate 2018年4月8日
 */
@Configuration
public class FrameworkWebImplAutoConfiguration implements InitializingBean {
    
    @Override
    public void afterPropertiesSet() {
        // 初始化resultCode
        new ResultCode();
    }
}

package com.lawu.eshop.weixin.srv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.lawu.eshop.weixin.srv.mp.WxMpInRedisConfigStorage;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

/**
 * @author Leach
 * @date 2018/2/5
 */

@ComponentScan(value = {"com.lawu.eshop.weixin.srv"}, excludeFilters = {@ComponentScan.Filter(value = {WeixinSrvApplication.class}, type = FilterType.ASSIGNABLE_TYPE)})
@SpringBootApplication
public class WeixinSrvApplicationTest {
    @Autowired
    private WeixinSrvConfig weixinSrvConfig;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Bean
    @ConditionalOnMissingBean
    public WxMpConfigStorage configStorage() {
        WxMpInRedisConfigStorage configStorage = new WxMpInRedisConfigStorage(stringRedisTemplate);
        configStorage.setAppId(weixinSrvConfig.getMemberAppId());
        configStorage.setSecret(weixinSrvConfig.getMemberAppSecret());
        return configStorage;
    }

    @Bean
    @ConditionalOnMissingBean
    public WxMpService wxMpService(WxMpConfigStorage configStorage) {

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(configStorage);
        return wxMpService;
    }

    @Bean
    @ConditionalOnMissingBean
    public WxPayConfig payConfig() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(weixinSrvConfig.getMemberPayAppId());
        payConfig.setMchId(weixinSrvConfig.getMemberPayMchId());
        payConfig.setMchKey(weixinSrvConfig.getMemberPayMchKey());
        payConfig.setKeyPath(weixinSrvConfig.getMemberPayKeyPath());

        return payConfig;
    }

    @Bean
    public WxPayService wxPayService(WxPayConfig payConfig) {
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }
}

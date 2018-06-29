package com.lawu.eshop.weixin.srv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.lawu.eshop.weixin.srv.mp.WxMpInRedisConfigStorage;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

@EnableDiscoveryClient
@ComponentScan({"com.lawu.eshop"})
@SpringBootApplication
public class WeixinSrvApplication {

    private static Logger logger = LoggerFactory.getLogger(WeixinSrvApplication.class);

    @Autowired
    private WeixinSrvConfig weixinSrvConfig;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static void main(String[] args) {
        logger.info("weixin-srv is starting");
        SpringApplication.run(WeixinSrvApplication.class, args);
        logger.info("weixin-srv is started");
    }

    private WxMpService getWxMpService(StringRedisTemplate stringRedisTemplate, String memberAppId, String memberAppSecret) {
        WxMpInRedisConfigStorage configStorage = new WxMpInRedisConfigStorage(stringRedisTemplate);
        configStorage.setAppId(memberAppId);
        configStorage.setSecret(memberAppSecret);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(configStorage);
        return wxMpService;
    }

    @Bean("memberWxMpService")
    public WxMpService memberWxMpService() {
        WxMpService wxMpService = getWxMpService(stringRedisTemplate, weixinSrvConfig.getMemberAppId(), weixinSrvConfig.getMemberAppSecret());
        return wxMpService;
    }

    @Bean("merchantWxMpService")
    public WxMpService merchantWxMpService() {
        WxMpService wxMpService = getWxMpService(stringRedisTemplate, weixinSrvConfig.getMerchantAppId(), weixinSrvConfig.getMerchantAppSecret());
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

package com.lawu.eshop.ad.srv;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

import com.lawu.eshop.solr.impl.repository.AdSolrRepository;

/**
 * 广告服务启动类
 * @author zhangrc
 * @date 2017/4/5
 */
@EnableSolrRepositories(basePackageClasses = AdSolrRepository.class, multicoreSupport = true)
@MapperScan({"com.lawu.eshop.ad.srv.mapper"})
@EnableDiscoveryClient
@EnableFeignClients({"com.lawu.eshop"})
@SpringBootApplication
@ComponentScan({"com.lawu.eshop"})
@ImportResource(locations={"classpath:spring.xml"})
public class AdSrvApplication {

    private static Logger logger = LoggerFactory.getLogger(AdSrvApplication.class);

    public static void main(String[] args) {
        logger.info("ad-srv is starting");
        SpringApplication.run(AdSrvApplication.class, args);
        logger.info("ad-srv is started");
    }
}

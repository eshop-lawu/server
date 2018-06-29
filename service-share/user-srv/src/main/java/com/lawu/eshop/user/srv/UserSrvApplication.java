package com.lawu.eshop.user.srv;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

import com.lawu.eshop.solr.impl.repository.MerchantStoreSolrRepository;

/**
 * 用户服务启动类
 * @author Leach
 * @date 2017/3/10
 */
@EnableSolrRepositories(basePackageClasses = MerchantStoreSolrRepository.class, multicoreSupport = true)
@MapperScan({"com.lawu.eshop.user.srv.mapper"})
@EnableDiscoveryClient
@EnableFeignClients({"com.lawu.eshop"})
@SpringBootApplication
@ImportResource(locations={"classpath:spring.xml"})
@ComponentScan({"com.lawu.eshop"})
public class UserSrvApplication {

    private static Logger logger = LoggerFactory.getLogger(UserSrvApplication.class);

    public static void main(String[] args) {
        logger.info("user-srv is starting");
        SpringApplication.run(UserSrvApplication.class, args);
        logger.info("user-srv is started");
    }
}

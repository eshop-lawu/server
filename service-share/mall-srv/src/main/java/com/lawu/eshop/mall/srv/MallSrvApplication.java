package com.lawu.eshop.mall.srv;

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

import com.lawu.eshop.solr.impl.repository.MerchantStoreSolrRepository;

/**
 * 商城服务启动类
 * @author Sunny
 * @date 2017/3/10
 */
@EnableSolrRepositories(basePackageClasses = MerchantStoreSolrRepository.class, multicoreSupport = true)
@MapperScan("com.lawu.eshop.mall.srv.mapper")
@EnableDiscoveryClient
@EnableFeignClients({"com.lawu.eshop"})
@SpringBootApplication
@ComponentScan({"com.lawu.eshop"})
@ImportResource(locations={"classpath:spring.xml"})
public class MallSrvApplication {

    private static Logger logger = LoggerFactory.getLogger(MallSrvApplication.class);

    public static void main(String[] args) {
        logger.info("mall-srv is starting");
        SpringApplication.run(MallSrvApplication.class, args);
        logger.info("mall-srv is started");
    }
}

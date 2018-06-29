package com.lawu.eshop.mall.srv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.lawu.eshop.solr.impl.repository.MerchantStoreSolrRepository;

/**
 * 商城服务启动类
 * @author Sunny
 * @date 2017/3/10
 */
@EnableSolrRepositories(basePackageClasses = MerchantStoreSolrRepository.class, multicoreSupport = true)
@MapperScan("com.lawu.eshop.mall.srv.mapper")
@EnableTransactionManagement
@ComponentScan(value = {"com.lawu"}, excludeFilters = {@ComponentScan.Filter(value = {MallSrvApplication.class}, type = FilterType.ASSIGNABLE_TYPE)})
@SpringBootApplication
@ImportResource(locations={"classpath:spring-test.xml"})
public class MallSrvApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(MallSrvApplicationTest.class, args);
    }
}

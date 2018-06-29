package com.lawu.eshop.ad.srv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.lawu.eshop.solr.impl.repository.AdSolrRepository;

@EnableSolrRepositories(basePackageClasses = AdSolrRepository.class, multicoreSupport = true)
@MapperScan("com.lawu.eshop.ad.srv.mapper")
@EnableTransactionManagement
@ComponentScan(value = {"com.lawu"}, excludeFilters = {@ComponentScan.Filter(value = {AdSrvApplication.class}, type = FilterType.ASSIGNABLE_TYPE)})
@SpringBootApplication
@ImportResource(locations={"classpath:spring-test.xml"})
public class AdSrvApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(AdSrvApplicationTest.class, args);
    }
}

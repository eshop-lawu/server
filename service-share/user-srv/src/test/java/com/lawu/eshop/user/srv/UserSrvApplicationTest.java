package com.lawu.eshop.user.srv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.lawu.eshop.solr.impl.repository.MerchantStoreSolrRepository;

@EnableSolrRepositories(basePackageClasses = MerchantStoreSolrRepository.class, multicoreSupport = true)
@MapperScan({"com.lawu.eshop.user.srv.mapper"})
@EnableTransactionManagement
@ComponentScan(value = {"com.lawu"}, excludeFilters = {@ComponentScan.Filter(value = {UserSrvApplication.class}, type = FilterType.ASSIGNABLE_TYPE)})
@SpringBootApplication
@ImportResource(locations={"classpath:spring-test.xml"})
public class UserSrvApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(UserSrvApplicationTest.class, args);
    }
}

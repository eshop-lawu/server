/**
 * 
 */
package com.lawu.eshop.product.srv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.lawu.eshop.solr.impl.repository.ProductSolrRepository;

/**
 * @author lihj
 * @date 2017年7月12日
 */
@EnableSolrRepositories(basePackageClasses = ProductSolrRepository.class, multicoreSupport = true)
@MapperScan({"com.lawu.eshop.product.srv.mapper"})
@EnableTransactionManagement
@ComponentScan(value = { "com.lawu"}, excludeFilters = {@ComponentScan.Filter(value = { ProductSrvApplication.class }, type = FilterType.ASSIGNABLE_TYPE) })
@SpringBootApplication
@ImportResource(locations = {"classpath:spring-test.xml"})
public class ProductSrvApplicationTest {

	public static void main(String[] args) {
		SpringApplication.run(ProductSrvApplicationTest.class, args);
	}
}

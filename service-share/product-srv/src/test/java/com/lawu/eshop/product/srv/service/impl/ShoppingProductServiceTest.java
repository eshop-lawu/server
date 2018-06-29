/**
 * 
 */
package com.lawu.eshop.product.srv.service.impl;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.lawu.eshop.product.param.ListShoppingProductParam;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.bo.ProductSearchBO;
import com.lawu.eshop.product.srv.service.ShoppingProductService;
import com.lawu.framework.core.page.Page;

/**
 * @author lihj
 * @date 2017年7月18日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
public class ShoppingProductServiceTest {

	private Logger log = Logger.getLogger(ProductServiceTest.class);

	@Autowired
	private ShoppingProductService shoppingProductService;

	/**
	 * 
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listHotProduct() {
		ListShoppingProductParam param = new ListShoppingProductParam();
		param.setMerchantId(1L);
		param.setCurrentPage(1);
		param.setPageSize(10);
		Page<ProductSearchBO> list = shoppingProductService.listHotProduct(param);
		log.info(JSON.toJSON(list));

	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listAllProduct() {
		ListShoppingProductParam param = new ListShoppingProductParam();
		param.setMerchantId(1L);
		param.setCurrentPage(1);
		param.setPageSize(10);
		Page<ProductSearchBO> list = shoppingProductService.listAllProduct(param);
		log.info(JSON.toJSON(list));
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listNewProduct() {
		ListShoppingProductParam param = new ListShoppingProductParam();
		param.setMerchantId(1L);
		param.setCurrentPage(1);
		param.setPageSize(10);
		Page<ProductSearchBO> list = shoppingProductService.listNewProduct(param);
		log.info(JSON.toJSON(list));
	}

}

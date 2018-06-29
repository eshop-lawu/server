/**
 * 
 */
package com.lawu.eshop.product.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.lawu.eshop.product.param.ListShoppingProductParam;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

/**
 * @author lihj
 * @date 2017年7月13日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
@WebAppConfiguration
public class ShoppingProductControllerTest {

	private MockMvc mvc;

	private Logger log = Logger.getLogger(ProductModelControllerTest.class);
	@Autowired
	private ShoppingProductController shoppingProductController;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(shoppingProductController).build();
	}
	
	/**
	 * 要购物门店详情店铺首页
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listHotProduct(){
		ListShoppingProductParam param = new ListShoppingProductParam();
		param.setCurrentPage(1);
		param.setPageSize(10);
		param.setMerchantId(1L);
		RequestBuilder request =post("/shoppingProduct/listHotProduct").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(param));
		try {
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * 要购物门店详情全部商品
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listAllProduct(){
		ListShoppingProductParam param = new ListShoppingProductParam();
		param.setCurrentPage(1);
		param.setPageSize(10);
		param.setMerchantId(1L);
		RequestBuilder request =post("/shoppingProduct/listAllProduct").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(param));
		try {
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * 要购物门店详情最新上架
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listNewProduct(){
		ListShoppingProductParam param = new ListShoppingProductParam();
		param.setCurrentPage(1);
		param.setPageSize(10);
		param.setMerchantId(1L);
		RequestBuilder request =post("/shoppingProduct/listNewProduct").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(param));
		try {
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	
	
}

/**
 * 
 */
package com.lawu.eshop.product.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.lawu.eshop.product.param.ProductSearchRealParam;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

/**
 * @author lihj
 * @date 2017年7月13日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
@WebAppConfiguration
public class ProductSolrControllerTest {

	private MockMvc mvc;

	private Logger log = Logger.getLogger(ProductModelControllerTest.class);
	@Autowired
	private ProductSolrController productSolrController;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(productSolrController).build();
	}

	/**
	 * 根据商品型号ID查询购物车商品信息
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listProductByCategoryId() {
		ProductSearchRealParam param =new ProductSearchRealParam();
		param.setCategoryId(1);
		param.setCurrentPage(1);
		param.setName("小炒肉");
		param.setPageSize(10);
		param.setProductId(1L);
		RequestBuilder request = post("/productSolr/listProductByCategoryId").contentType(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(param));
		try{
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * 商品详情为你推荐(同类别按销量排行)
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listRecommendProduct() {
		ProductSearchRealParam param =new ProductSearchRealParam();
		param.setCategoryId(1);
		param.setCurrentPage(1);
		param.setName("小炒肉");
		param.setPageSize(10);
		param.setProductId(1L);
		RequestBuilder request = post("/productSolr/listRecommendProduct").contentType(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(param));
		try{
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * 要购物首页猜你喜欢
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listYouLikeProduct() {
		ProductSearchRealParam param =new ProductSearchRealParam();
		param.setCategoryId(1);
		param.setCurrentPage(1);
		param.setName("小炒肉");
		param.setPageSize(10);
		param.setProductId(1L);
		RequestBuilder request = post("/productSolr/listYouLikeProduct").contentType(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(param));
		try{
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * 会员APP商品搜索
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listProductByName() {
		ProductSearchRealParam param =new ProductSearchRealParam();
		param.setCategoryId(1);
		param.setCurrentPage(1);
		param.setName("小炒肉");
		param.setPageSize(10);
		param.setProductId(1L);
		RequestBuilder request = post("/productSolr/listProductByName").contentType(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(param));
		try{
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * 搜索词关联词频查询
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listStoreSearchWord() {
		RequestBuilder request = get("/productSolr/listProductSearchWord").param("name", "测试");
		try{
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * 查询日销量商品列表
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void findProductSearchList() {
		ProductSearchRealParam param =new ProductSearchRealParam();
		param.setCategoryId(1);
		param.setCurrentPage(1);
		param.setName("小炒肉");
		param.setPageSize(10);
		param.setProductId(1L);
		RequestBuilder request = post("/productSolr/findProductSearchList").contentType(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(param));
		try{
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

}

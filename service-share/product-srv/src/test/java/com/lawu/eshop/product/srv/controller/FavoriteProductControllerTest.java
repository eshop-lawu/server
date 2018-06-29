package com.lawu.eshop.product.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.log4j.Logger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
import com.lawu.eshop.product.query.FavoriteProductQuery;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

/**
 * 
 * @author lihj
 * @date 2017年7月12日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
@WebAppConfiguration
public class FavoriteProductControllerTest {

	private MockMvc mvc;
	
	private Logger log=Logger.getLogger(FavoriteProductControllerTest.class);
	
	@Autowired
	private FavoriteProductController favoriteProductController;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(favoriteProductController).build();
	}

	/**
	 * 收藏
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void save() {
		RequestBuilder request = post("/favoriteProduct/save").param("memberId", "1").param("productId", "1");
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * 取消收藏
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void remove() {
		RequestBuilder request = post("/favoriteProduct/save").param("memberId", "1").param("productId", "1");
		RequestBuilder request1 = post("/favoriteProduct/save").param("memberId", "2").param("productId", "2");
		RequestBuilder requestRemove = delete("/favoriteProduct/remove/1").param("memberId", "1");

		try {
			ResultActions perform = mvc.perform(request);
			ResultActions perform1 = mvc.perform(request1);
			ResultActions remove = mvc.perform(requestRemove);
			MvcResult mvcResult = remove.andExpect(status().is(HttpCode.SC_NO_CONTENT))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_NO_CONTENT, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * 我收藏的商品列表
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectMyFavoriteProduct() {
		RequestBuilder request1 = post("/favoriteProduct/save").param("memberId", "1").param("productId", "1");
		FavoriteProductQuery query = new FavoriteProductQuery();
		RequestBuilder request = post("/favoriteProduct/selectMyFavoriteProduct").param("memberId", "1")
				.contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(query));
		try {
			ResultActions perform1 = mvc.perform(request1);
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print())
					.andReturn();
			Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * 查询用户是否收藏了该商品
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getUserFavorite() {
		RequestBuilder request1 = post("/favoriteProduct/save").param("memberId", "1").param("productId", "1");
		RequestBuilder request = get("/favoriteProduct/getUserFavorite/1").param("memberId", "1");
		try {
			ResultActions perform1 = mvc.perform(request1);
			ResultActions perform =mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print())
					.andReturn();
//			Assert.assertEquals("false", perform.andReturn().getResponse().getContentAsString());
			Assert.assertEquals("true", perform.andReturn().getResponse().getContentAsString());
			Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}
}

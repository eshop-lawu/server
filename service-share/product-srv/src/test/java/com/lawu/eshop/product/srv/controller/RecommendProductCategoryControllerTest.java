/**
 * 
 */
package com.lawu.eshop.product.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.lawu.eshop.product.param.EditRecommendProductCategoryParam;
import com.lawu.eshop.product.param.ListRecommendProductCategoryParam;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

/**
 * @author lihj
 * @date 2017年7月13日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
@WebAppConfiguration
public class RecommendProductCategoryControllerTest {

	private MockMvc mvc;

	private Logger log = Logger.getLogger(ProductModelControllerTest.class);
	@Autowired
	private RecommendProductCategoryController recommendProductCategoryController;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(recommendProductCategoryController).build();
	}

	/**
	 * 保存商品类别
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveRecommendProductCategory() {
		ListRecommendProductCategoryParam qparam =new ListRecommendProductCategoryParam();
		qparam.setCurrentPage(1);
		qparam.setPageSize(10);
		
		EditRecommendProductCategoryParam param =new EditRecommendProductCategoryParam();
		param.setCategoryId(1);
		param.setCategoryName("测试");
		param.setIsvisible(true);
		param.setIsHot(true);
		param.setImagePath("IMAGE");
		param.setOrdinal(Byte.valueOf("1"));
		RequestBuilder request = post("/recommendProductCategory/saveRecommendProductCategory").contentType(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(param));
		RequestBuilder requestq= post("/recommendProductCategory/listRecommendProductCategory").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(qparam));
		
		try{
			ResultActions perform = mvc.perform(request);
			ResultActions performq = mvc.perform(requestq);
			log.info(performq.andReturn().getResponse().getContentAsString());
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
	 * 根据ID删除商品类别
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void deleteRecommendProductCategory() {
		ListRecommendProductCategoryParam qparam =new ListRecommendProductCategoryParam();
		qparam.setCurrentPage(1);
		qparam.setPageSize(10);
		RequestBuilder requestq= post("/recommendProductCategory/listRecommendProductCategory").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(qparam));
		
		RequestBuilder req= delete("/recommendProductCategory/deleteRecommendProductCategory/1");
		
		try{
			addCategory();
			ResultActions performq = mvc.perform(requestq);
			log.info(performq.andReturn().getResponse().getContentAsString());
			ResultActions del = mvc.perform(req);
			log.info(del.andReturn().getResponse().getContentAsString());
			ResultActions performq1 = mvc.perform(requestq);
			log.info(performq1.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = del.andExpect(status().is(HttpCode.SC_NO_CONTENT))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_NO_CONTENT, mvcResult.getResponse().getStatus());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * 根据ID修改商品类别
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void updateRecommendProductCategory() {
		ListRecommendProductCategoryParam qparam =new ListRecommendProductCategoryParam();
		qparam.setCurrentPage(1);
		qparam.setPageSize(10);
		
		
		EditRecommendProductCategoryParam param1 =new EditRecommendProductCategoryParam();
		param1.setCategoryId(1);
		param1.setCategoryName("测试111");
		param1.setIsvisible(true);
		param1.setOrdinal(Byte.valueOf("1"));
		
		RequestBuilder requestq= post("/recommendProductCategory/listRecommendProductCategory").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(qparam));
		
		RequestBuilder req= put("/recommendProductCategory/updateRecommendProductCategory/1").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(param1));
		
		try{
			addCategory();
			ResultActions performq = mvc.perform(requestq);
			log.info(performq.andReturn().getResponse().getContentAsString());
			ResultActions del = mvc.perform(req);
			log.info(del.andReturn().getResponse().getContentAsString());
			ResultActions performq1 = mvc.perform(requestq);
			log.info(performq1.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = del.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * 根据ID查询商品类别
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getRecommendProductCategory() {
		RequestBuilder req= get("/recommendProductCategory/getRecommendProductCategory/1");
		try{
			addCategory();
			ResultActions perreq = mvc.perform(req);
			log.info(perreq.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perreq.andExpect(status().is(HttpCode.SC_OK))
					.andDo(MockMvcResultHandlers.print()).andReturn();
			Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
	}

	/**
	 * 商品类别列表
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listRecommendProductCategory() {
		ListRecommendProductCategoryParam qparam =new ListRecommendProductCategoryParam();
		qparam.setCurrentPage(1);
		qparam.setPageSize(10);
		RequestBuilder request = post("/recommendProductCategory/listRecommendProductCategory").contentType(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(qparam));
		try{
			addCategory();
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
	 * 商品类别列表
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listAllRecommendProductCategory() {
		RequestBuilder request = get("/recommendProductCategory/listAllRecommendProductCategory");
		try{
			addCategory();
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
	 * @throws Exception 
	 * 
	 */
	private void addCategory() throws Exception {
		EditRecommendProductCategoryParam param =new EditRecommendProductCategoryParam();
		param.setCategoryId(1);
		param.setCategoryName("测试");
		param.setIsvisible(true);
		param.setOrdinal(Byte.valueOf("1"));
		RequestBuilder requestadd = post("/recommendProductCategory/saveRecommendProductCategory").contentType(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(param));
		ResultActions performadd = mvc.perform(requestadd);
		log.info(performadd.andReturn().getResponse().getContentAsString());
	}

	@Test
	@Transactional
	@Rollback
	public void getHotRecommendProductCategory(){
		RequestBuilder request = get("/recommendProductCategory/getHotRecommendProductCategory");
		try{
			addCategory();
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

	@Transactional
	@Test
	@Rollback
	public void editHot(){
		RequestBuilder request = put("/recommendProductCategory/editHot/1").param("isHot","false");
		try{
			addCategory();
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

}

/**
 * 
 */
package com.lawu.eshop.product.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.log4j.Logger;
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
import com.lawu.eshop.product.param.OperatorProductCategoryParam;
import com.lawu.eshop.product.param.ProductCategoryParam;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.domain.ProductCategoryeDO;
import com.lawu.eshop.product.srv.mapper.ProductCategoryeDOMapper;
import com.lawu.framework.web.HttpCode;

/**
 * @author lihj
 * @date 2017年7月12日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
@WebAppConfiguration
public class ProductCategoryControllerTest {

	private MockMvc mvc;

	private Logger log = Logger.getLogger(ProductCategoryControllerTest.class);

	@Autowired
	private ProductCategoryController productCategoryController;

	@Autowired
	private ProductCategoryeDOMapper productCategoryeDOMapper;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(productCategoryController).build();
	}

	/**
	 * 查询所有商品分类
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void findAll() {
		RequestBuilder request = get("/productCategory/findAll");
		try {
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
	 * 根据id查询商品分类
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getById() {
		RequestBuilder request = get("/productCategory/getById").param("id", "1");
		try {
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
	 * 查询推荐商品类别(一级)
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listRecommendProductCategory() {
		RequestBuilder request = get("/productCategory/listRecommendProductCategory");
		try{
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print())
					.andReturn();
			Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * 根据parent_id查询
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void find() {
		RequestBuilder request = get("/productCategory/find/1");
		try{
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print())
					.andReturn();
			Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Transactional
	@Test
	@Rollback
	public void findOperatorAll() {
		ProductCategoryeDO categoryDO = new ProductCategoryeDO();
		categoryDO.setImageUrl("image");
		categoryDO.setName("食品");
		categoryDO.setType((byte) 1);
		categoryDO.setIsVirtual(false);
		categoryDO.setStatue(Boolean.TRUE);
		categoryDO.setParentId(0);
		categoryDO.setLevel((byte) 1);
		productCategoryeDOMapper.insertSelective(categoryDO);
		OperatorProductCategoryParam param = new OperatorProductCategoryParam();
		param.setCurrentPage(1);
		param.setPageSize(10);
		RequestBuilder request = post("/productCategory/findOperatorAll").contentType(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(param));
		try {
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

	@Transactional
	@Test
	@Rollback
	public void addProductCategory() {
		ProductCategoryParam param = new ProductCategoryParam();
		param.setLevel((byte) 1);
		param.setImageUrl("image");
		param.setIsVirtual(false);
		param.setName("食品");
		param.setOrdinal((byte) 2);
		param.setParentId(0);
		RequestBuilder request = post("/productCategory/addProductCategory").contentType(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(param));
		try {
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print())
					.andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Transactional
	@Test
	@Rollback
	public void delProductCategory() {
		ProductCategoryeDO categoryDO = new ProductCategoryeDO();
		categoryDO.setImageUrl("image");
		categoryDO.setName("食品");
		categoryDO.setType((byte) 1);
		categoryDO.setIsVirtual(false);
		categoryDO.setStatue(Boolean.TRUE);
		categoryDO.setParentId(0);
		categoryDO.setLevel((byte) 1);
		productCategoryeDOMapper.insertSelective(categoryDO);
		RequestBuilder request = put("/productCategory/delProductCategory").param("ids", categoryDO.getId().toString());
		try {
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print())
					.andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Transactional
	@Test
	@Rollback
	public void editProductCategory() {
		ProductCategoryeDO categoryDO = new ProductCategoryeDO();
		categoryDO.setImageUrl("image");
		categoryDO.setName("食品");
		categoryDO.setType((byte) 1);
		categoryDO.setIsVirtual(false);
		categoryDO.setStatue(Boolean.TRUE);
		categoryDO.setParentId(0);
		categoryDO.setLevel((byte) 1);
		productCategoryeDOMapper.insertSelective(categoryDO);

		ProductCategoryParam param = new ProductCategoryParam();
		param.setLevel((byte) 1);
		param.setImageUrl("image2");
		param.setIsVirtual(false);
		param.setName("食品2");
		param.setOrdinal((byte) 2);
		param.setParentId(0);
		RequestBuilder request = post("/productCategory/editProductCategory/" + categoryDO.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(param));
		try {
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print())
					.andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Transactional
	@Test
	@Rollback
	public void editHot() {
		ProductCategoryeDO categoryDO = new ProductCategoryeDO();
		categoryDO.setImageUrl("image");
		categoryDO.setName("食品");
		categoryDO.setType((byte) 1);
		categoryDO.setIsVirtual(false);
		categoryDO.setStatue(Boolean.TRUE);
		categoryDO.setParentId(0);
		categoryDO.setLevel((byte) 1);
		productCategoryeDOMapper.insertSelective(categoryDO);
		RequestBuilder request = put("/productCategory/editHot/" + categoryDO.getId()).param("type", "PRODUCT_CATEGORY_HOT");
		try {
			ResultActions perform = mvc.perform(request);
			log.info(perform.andReturn().getResponse().getContentAsString());
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print())
					.andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Transactional
	@Test
	@Rollback
	public void getHotProductCategory(){
		RequestBuilder request = get("/productCategory/getHotProductCategory");
		try {
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
}

package com.lawu.eshop.product.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Date;

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
import com.lawu.eshop.product.constant.CustomSpecStatusEnum;
import com.lawu.eshop.product.constant.CustomTypeEnum;
import com.lawu.eshop.product.query.ProductCustomSpecPageQuery;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.domain.ProductCustomSpecDO;
import com.lawu.eshop.product.srv.mapper.ProductCustomSpecDOMapper;
import com.lawu.framework.web.HttpCode;
/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月19日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
@WebAppConfiguration
public class ProductCustomSpecControllerTest {
	
	
	private MockMvc mvc;
	
	@Autowired
	private ProductCustomSpecController productCustomSpecController;
	
	@Autowired
	private ProductCustomSpecDOMapper productCustomSpecDOMapper;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(productCustomSpecController).build();
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void specPage() {
		ProductCustomSpecDO productCustomSpecDO = new ProductCustomSpecDO();
		productCustomSpecDO.setRelateId(1l);
		productCustomSpecDO.setRelateName("衣服");
		productCustomSpecDO.setSpecName("test");
		productCustomSpecDO.setCustomType(CustomTypeEnum.CATEGORY.getVal());
		productCustomSpecDO.setStatus(CustomSpecStatusEnum.ON_DEAL_WITH.getVal());
		productCustomSpecDO.setGmtCreate(new Date());
		productCustomSpecDO.setGmtModified(new Date());
		productCustomSpecDOMapper.insertSelective(productCustomSpecDO);
		
		ProductCustomSpecPageQuery param = new ProductCustomSpecPageQuery();
		param.setCurrentPage(1);
		param.setPageSize(20);
		
		RequestBuilder request = post("/productCustomSpec/specPage").contentType(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(param));
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
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void dealCustomSpec() {
		ProductCustomSpecDO productCustomSpecDO = new ProductCustomSpecDO();
		productCustomSpecDO.setRelateId(1l);
		productCustomSpecDO.setRelateName("衣服");
		productCustomSpecDO.setSpecName("test");
		productCustomSpecDO.setCustomType(CustomTypeEnum.CATEGORY.getVal());
		productCustomSpecDO.setStatus(CustomSpecStatusEnum.ON_DEAL_WITH.getVal());
		productCustomSpecDO.setGmtCreate(new Date());
		productCustomSpecDO.setGmtModified(new Date());
		Integer id = productCustomSpecDOMapper.insertSelective(productCustomSpecDO);
		
		RequestBuilder request = put("/productCustomSpec/dealCustomSpec/"+id.toString()).param("statusEnum", "DEAL_WITH");
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

}

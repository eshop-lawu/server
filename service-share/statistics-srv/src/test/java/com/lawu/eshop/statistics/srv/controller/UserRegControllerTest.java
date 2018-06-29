package com.lawu.eshop.statistics.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.gexin.fastjson.JSONObject;
import com.lawu.eshop.statistics.param.UserRegAreaParam;
import com.lawu.eshop.statistics.param.UserRegParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
@WebAppConfiguration
public class UserRegControllerTest {

	private MockMvc mvc;

	@Autowired
	private UserRegController userRegController;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(userRegController).build();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveUserRegDaily() {
		RequestBuilder request = post("/userReg/saveUserRegDaily").param("memberCount", "10").param("merchantCount", "20");
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveUserRegMonth() {
		RequestBuilder request = post("/userReg/saveUserRegMonth").param("memberCount", "10").param("merchantCount", "20");
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void updateUserRegArea() {
		UserRegAreaParam param = new UserRegAreaParam();
		param.setCityId(4403);
		param.setMemberCount(10);
		param.setMerchantCommonCount(10);
		param.setMerchantCount(5);
		param.setMerchantEntityCount(1);
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/userReg/updateUserRegArea").contentType(MediaType.APPLICATION_JSON).content(requestJson);
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getReportUserRegDaily() {
		UserRegParam param = new UserRegParam();
		param.setBeginDate("2017-7-1");
		param.setEndDate("2017-7-31");
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/userReg/getReportUserRegDaily").contentType(MediaType.APPLICATION_JSON).content(requestJson);
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK))
					.andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getReportUserRegMonth() {
		UserRegParam param = new UserRegParam();
		param.setBeginDate("2017-7-1");
		param.setEndDate("2017-7-31");
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/userReg/getReportUserRegMonth").contentType(MediaType.APPLICATION_JSON).content(requestJson);
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK))
					.andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getReportUserRegArea() {
		RequestBuilder request = get("/userReg/getReportUserRegArea");
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK))
					.andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
}

package com.lawu.eshop.statistics.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.lawu.eshop.statistics.dto.UserActiveDTO;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
@WebAppConfiguration
public class UserActiveControllerTest {

	private MockMvc mvc;

	@Autowired
	private UserActiveController userActiveController;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(userActiveController).build();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void collectionMemberActiveDaily() {
		RequestBuilder request = get("/userActive/collectionMemberActiveDaily");
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
	public void collectionMerchantActiveDaily() {
		RequestBuilder request = get("/userActive/collectionMerchantActiveDaily");
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
	public void saveUserActiveDaily() {
		RequestBuilder request = post("/userActive/saveUserActiveDaily").param("memberCount", "10").param("merchantCount", "20");
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
//	@Transactional(rollbackFor = Exception.class)
//	@Rollback
//	@Test
//	public void collectionMemberActiveMonth() {
//		RequestBuilder request = get("/userActive/collectionMemberActiveMonth");
//		try {
//			ResultActions perform = mvc.perform(request);
//			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
//					.andDo(MockMvcResultHandlers.print()).andReturn();
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
//	}
	
//	@Transactional(rollbackFor = Exception.class)
//	@Rollback
//	@Test
//	public void collectionMerchantActiveMonth() {
//		RequestBuilder request = get("/userActive/collectionMerchantActiveMonth");
//		try {
//			ResultActions perform = mvc.perform(request);
//			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK))
//					.andDo(MockMvcResultHandlers.print()).andReturn();
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
//	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveUserActiveMonth() {
		RequestBuilder request = post("/userActive/saveUserActiveMonth").param("memberCount", "10").param("merchantCount", "20");
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
	public void collectionMemberActiveAreaDaily() {
		RequestBuilder request = get("/userActive/collectionMemberActiveAreaDaily");
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
	public void collectionMerchantActiveAreaDaily() {
		RequestBuilder request = get("/userActive/collectionMerchantActiveAreaDaily");
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
	public void saveUserActiveAreaDaily() {
		List<UserActiveDTO> userActiveDTOS = new ArrayList<>();
		RequestBuilder request = post("/userActive/saveUserActiveAreaDaily").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(userActiveDTOS));
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
	public void saveMerchantActiveAreaDaily() {
		List<UserActiveDTO> userActiveDTOS = new ArrayList<UserActiveDTO>();
		UserActiveDTO u = new UserActiveDTO();
		u.setCityId(4403);
		u.setCityName("深圳市");
		u.setUserCount(13025458);
		u.setVisitDate(new Date());
		userActiveDTOS.add(u);
		String requestJson = JSONObject.toJSONString(userActiveDTOS);
		try {
			RequestBuilder request = post("/userActive/saveMerchantActiveAreaDaily").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
	public void getUserActiveListDaily() {
		RequestBuilder request = get("/userActive/getUserActiveListDaily");
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
	public void getUserActiveListMonth() {
		RequestBuilder request = get("/userActive/getUserActiveListMonth");
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
	public void getReportUserActiveAreaDailyList() {
		RequestBuilder request = get("/userActive/getReportUserActiveAreaDailyList").param("reportDate", "2017-7-1");
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
	public void getReportUserActiveAreaMonthList() {
		RequestBuilder request = get("/userActive/getReportUserActiveAreaMonthList").param("reportDate", "2017-7-1");
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK))
					.andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	
//	@Transactional(rollbackFor = Exception.class)
//	@Rollback
//	@Test
//	public void collectionMemberActiveAreaMonth() {
//		RequestBuilder request = get("/userActive/collectionMemberActiveAreaMonth");
//		try {
//			ResultActions perform = mvc.perform(request);
//			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
//					.andDo(MockMvcResultHandlers.print()).andReturn();
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
//	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveUserActiveAreaMonth() {
		List<UserActiveDTO> userActiveDTOS = new ArrayList<>();
		RequestBuilder request = post("/userActive/saveUserActiveAreaMonth").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(userActiveDTOS));
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
//	@Transactional(rollbackFor = Exception.class)
//	@Rollback
//	@Test
//	public void collectionMerchantActiveAreaMonth() {
//		RequestBuilder request = get("/userActive/collectionMerchantActiveAreaMonth");
//		try {
//			ResultActions perform = mvc.perform(request);
//			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
//					.andDo(MockMvcResultHandlers.print()).andReturn();
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
//	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveMerchantActiveAreaMonth() {
		List<UserActiveDTO> list = new ArrayList<UserActiveDTO>();
		
		RequestBuilder request = post("/userActive/saveMerchantActiveAreaMonth").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(list));
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
	public void getAgentUserActiveListDaily() {
		AgentReportParam param = new AgentReportParam();
		param.setBeginTime("2017-1-1 00:00:00");
		param.setEndTime("2017-12-31 23:59:59");
		param.setRegionPath("1/4403/1");
		RequestBuilder request = post("/userActive/getAgentUserActiveListDaily").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(param));
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
	public void getAgentUserActiveListMonth() {
		AgentReportParam param = new AgentReportParam();
		param.setBeginTime("2017-1-1 00:00:00");
		param.setEndTime("2017-12-31 23:59:59");
		param.setRegionPath("1/4403/1");
		RequestBuilder request = post("/userActive/getAgentUserActiveListMonth").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(param));
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

package com.lawu.eshop.statistics.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
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

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.param.AgentWithdrawCashParam;
import com.lawu.eshop.statistics.param.ReportKCommonParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
@WebAppConfiguration
public class WithdrawCashControllerTest {

	private MockMvc mvc;

	@Autowired
	private WithdrawCashController withdrawCashController;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(withdrawCashController).build();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveDaily() {
		ReportKCommonParam param = new ReportKCommonParam();
		param.setGmtCreate(new Date());
		param.setGmtReport(new Date());
		param.setMemberMoney(new BigDecimal(10));
		param.setMerchantMoney(new BigDecimal(10));
		param.setTotalMoney(new BigDecimal(10));
		String questJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/withdrawCash/saveDaily").contentType(MediaType.APPLICATION_JSON).content(questJson);
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
	public void saveMonth() {
		ReportKCommonParam param = new ReportKCommonParam();
		param.setGmtCreate(new Date());
		param.setGmtReport(new Date());
		param.setMemberMoney(new BigDecimal(10));
		param.setMerchantMoney(new BigDecimal(10));
		param.setTotalMoney(new BigDecimal(10));
		String questJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/withdrawCash/saveMonth").contentType(MediaType.APPLICATION_JSON).content(questJson);
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
	public void getDailyList() {
		saveDaily();
		RequestBuilder request = get("/withdrawCash/getDailyList").param("reportDate", "2017-7");
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
	public void deleteDailyByReportDate() {
		RequestBuilder request = delete("/withdrawCash/deleteDailyByReportDate").param("reportDate", "2017-7-1");
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
	public void deleteMonthByReportDate() {
		RequestBuilder request = delete("/withdrawCash/deleteMonthByReportDate").param("reportDate", "2017-7");
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
	public void selectReport() {
		RequestBuilder request = get("/withdrawCash/selectReport").param("bdate", "2017-7-1").param("edate", "2017-7-31");
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
	public void selectReportAreaWithdrawCashList() {
		RequestBuilder request = get("/withdrawCash/selectReportAreaWithdrawCashList").param("month", "2017-7").param("cityId", "1");
//		try {
//			ResultActions perform = mvc.perform(request);
//			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK))
//					.andDo(MockMvcResultHandlers.print()).andReturn();
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
	}
	
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveAgentDaily() {
		AgentWithdrawCashParam param = new AgentWithdrawCashParam();
		String questJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/withdrawCash/saveAgentDaily").contentType(MediaType.APPLICATION_JSON).content(questJson);
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
	public void saveAgentMonth() {
		AgentWithdrawCashParam param = new AgentWithdrawCashParam();
		String questJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/withdrawCash/saveAgentMonth").contentType(MediaType.APPLICATION_JSON).content(questJson);
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
	public void selectAreaWithdrawDailyReport() {
		AgentReportParam param = new AgentReportParam();
		param.setBeginTime("2017-1-1 00:00:00");
		param.setEndTime("2017-12-31 23:59:59");
		param.setRegionPath("1/4403/1");
		String questJson = JSONObject.toJSONString(param);
		RequestBuilder request = get("/withdrawCash/selectAreaWithdrawDailyReport").contentType(MediaType.APPLICATION_JSON).content(questJson);
//		try {
//			ResultActions perform = mvc.perform(request);
//			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK))
//					.andDo(MockMvcResultHandlers.print()).andReturn();
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectAreaWithdrawMonthReport() {
		AgentReportParam param = new AgentReportParam();
		param.setBeginTime("2017-1-1 00:00:00");
		param.setEndTime("2017-12-31 23:59:59");
		param.setRegionPath("1/4403/1");
		String questJson = JSONObject.toJSONString(param);
		RequestBuilder request = get("/withdrawCash/selectAreaWithdrawMonthReport").contentType(MediaType.APPLICATION_JSON).content(questJson);
//		try {
//			ResultActions perform = mvc.perform(request);
//			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK))
//					.andDo(MockMvcResultHandlers.print()).andReturn();
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		}
	}
}

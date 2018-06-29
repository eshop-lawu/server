package com.lawu.eshop.statistics.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
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
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.param.AgentReportRechargeSaveParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.service.ReportAreaRechargeService;
import com.lawu.framework.web.HttpCode;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
@WebAppConfiguration
public class ReportAreaRechargeControllerTest {

	private MockMvc mvc;
	
	@Autowired
	private ReportAreaRechargeController reportAreaRechargeController;
	
	@Autowired
	private ReportAreaRechargeService reportAreaRechargeService;
	
	
	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(reportAreaRechargeController).build();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveDaily() {
		List<AgentReportRechargeSaveParam> saveParams = new ArrayList<>();
		AgentReportRechargeSaveParam param = new AgentReportRechargeSaveParam();
		param.setAreaId(1);
		param.setCityId(1);
		param.setGmtCreate(new Date());
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		param.setMemberRechargeBalance(new BigDecimal(1));
		param.setMemberRechargePoint(new BigDecimal(1));
		param.setMerchantRechargeBalance(new BigDecimal(1));
		param.setMerchantRechargePoint(new BigDecimal(1));
		param.setProvinceId(1);
		param.setTotalRechargeBalance(new BigDecimal(2));
		param.setTotalRechargePoint(new BigDecimal(2));
		saveParams.add(param);
		String requestJson = JSONObject.toJSONString(saveParams);
		RequestBuilder request = post("/reportAreaRecharge/saveDaily").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
		List<AgentReportRechargeSaveParam> saveParams = new ArrayList<>();
		AgentReportRechargeSaveParam param = new AgentReportRechargeSaveParam();
		param.setAreaId(1);
		param.setCityId(1);
		param.setGmtCreate(new Date());
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		param.setMemberRechargeBalance(new BigDecimal(1));
		param.setMemberRechargePoint(new BigDecimal(1));
		param.setMerchantRechargeBalance(new BigDecimal(1));
		param.setMerchantRechargePoint(new BigDecimal(1));
		param.setProvinceId(1);
		param.setTotalRechargeBalance(new BigDecimal(2));
		param.setTotalRechargePoint(new BigDecimal(2));
		saveParams.add(param);
		String requestJson = JSONObject.toJSONString(saveParams);
		RequestBuilder request = post("/reportAreaRecharge/saveMonth").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
		List<AgentReportRechargeSaveParam> saveParams = new ArrayList<>();
		AgentReportRechargeSaveParam param = new AgentReportRechargeSaveParam();
		param.setAreaId(1);
		param.setCityId(1);
		param.setGmtCreate(new Date());
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		param.setMemberRechargeBalance(new BigDecimal(1));
		param.setMemberRechargePoint(new BigDecimal(1));
		param.setMerchantRechargeBalance(new BigDecimal(1));
		param.setMerchantRechargePoint(new BigDecimal(1));
		param.setProvinceId(1);
		param.setTotalRechargeBalance(new BigDecimal(2));
		param.setTotalRechargePoint(new BigDecimal(2));
		saveParams.add(param);
		reportAreaRechargeService.saveDaily(saveParams);
		RequestBuilder request = delete("/reportAreaRecharge/deleteDailyByReportDate").param("reportDate", DateUtil.getDateFormat(DateUtil.getMonthBefore(new Date())));
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
		List<AgentReportRechargeSaveParam> saveParams = new ArrayList<>();
		AgentReportRechargeSaveParam param = new AgentReportRechargeSaveParam();
		param.setAreaId(1);
		param.setCityId(1);
		param.setGmtCreate(new Date());
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		param.setMemberRechargeBalance(new BigDecimal(1));
		param.setMemberRechargePoint(new BigDecimal(1));
		param.setMerchantRechargeBalance(new BigDecimal(1));
		param.setMerchantRechargePoint(new BigDecimal(1));
		param.setProvinceId(1);
		param.setTotalRechargeBalance(new BigDecimal(2));
		param.setTotalRechargePoint(new BigDecimal(2));
		saveParams.add(param);
		reportAreaRechargeService.saveMonth(saveParams);
		RequestBuilder request = delete("/reportAreaRecharge/deleteMonthByReportDate").param("reportDate", DateUtil.getDateFormat(DateUtil.getMonthBefore(new Date())));
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
		List<AgentReportRechargeSaveParam> saveParams = new ArrayList<>();
		AgentReportRechargeSaveParam param = new AgentReportRechargeSaveParam();
		param.setAreaId(1);
		param.setCityId(1);
		param.setGmtCreate(new Date());
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		param.setMemberRechargeBalance(new BigDecimal(1));
		param.setMemberRechargePoint(new BigDecimal(1));
		param.setMerchantRechargeBalance(new BigDecimal(1));
		param.setMerchantRechargePoint(new BigDecimal(1));
		param.setProvinceId(1);
		param.setTotalRechargeBalance(new BigDecimal(2));
		param.setTotalRechargePoint(new BigDecimal(2));
		saveParams.add(param);
		reportAreaRechargeService.saveDaily(saveParams);
		RequestBuilder request = get("/reportAreaRecharge/getDailyList").param("reportDate", DateUtil.getDateFormat(DateUtil.getMonthBefore(new Date())));
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
	public void getAreaRechargeList() {
		List<AgentReportRechargeSaveParam> saveParams = new ArrayList<>();
		AgentReportRechargeSaveParam param = new AgentReportRechargeSaveParam();
		param.setAreaId(1);
		param.setCityId(1);
		param.setGmtCreate(new Date());
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		param.setMemberRechargeBalance(new BigDecimal(1));
		param.setMemberRechargePoint(new BigDecimal(1));
		param.setMerchantRechargeBalance(new BigDecimal(1));
		param.setMerchantRechargePoint(new BigDecimal(1));
		param.setProvinceId(1);
		param.setTotalRechargeBalance(new BigDecimal(2));
		param.setTotalRechargePoint(new BigDecimal(2));
		saveParams.add(param);
		reportAreaRechargeService.saveDaily(saveParams);
		
		AgentReportParam agentReportParam = new AgentReportParam();
		agentReportParam.setRegionPath("1/1/1");
		agentReportParam.setBeginTime(DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		agentReportParam.setEndTime(DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		RequestBuilder request = post("/reportAreaRecharge/getAreaRechargeList").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(agentReportParam));
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

package com.lawu.eshop.statistics.srv.controller;

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
import com.lawu.eshop.statistics.param.AgentSelectAreaAdPointParam;
import com.lawu.eshop.statistics.param.ReportAreaAdPointDailyParams;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.service.ReportAreaAdPointDailyService;
import com.lawu.framework.web.HttpCode;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
@WebAppConfiguration
public class ReportAreaAdPointDailyControllerTest {

	private MockMvc mvc;
	
	@Autowired
	private ReportAreaAdPointDailyController reportAreaAdPointDailyController;
	
	@Autowired
	private ReportAreaAdPointDailyService reportAreaAdPointDailyService;
	
	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(reportAreaAdPointDailyController).build();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectReportAreaAdPointDaily() {
		ReportAreaAdPointDailyParams param = new ReportAreaAdPointDailyParams();
		param.setAreaId(1);
		param.setCityId(1);
		param.setProvinceId(1);
		param.setGmtCreate(new Date());
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		param.setProvinceId(1);
		param.setReportTotalPoint(new BigDecimal(1));
		reportAreaAdPointDailyService.insertReportAreaAdPointDaily(param);
		RequestBuilder request = get("/reportAreaAdPointDaily/selectReportAreaAdPointDaily/"+1).param("areaId", "1").param("date", DateUtil.getDateFormat(DateUtil.getMonthBefore(new Date())));
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
	public void insertReportAreaAdPointDaily() {
		ReportAreaAdPointDailyParams param = new ReportAreaAdPointDailyParams();
		param.setAreaId(1);
		param.setCityId(1);
		param.setProvinceId(1);
		param.setGmtCreate(new Date());
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		param.setProvinceId(1);
		param.setReportTotalPoint(new BigDecimal(1));
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/reportAreaAdPointDaily/insertReportAreaAdPointDaily").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
	public void deleteReportAreaAdPointDaily() {
		ReportAreaAdPointDailyParams param = new ReportAreaAdPointDailyParams();
		param.setAreaId(1);
		param.setCityId(1);
		param.setProvinceId(1);
		param.setGmtCreate(new Date());
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		param.setProvinceId(1);
		param.setReportTotalPoint(new BigDecimal(1));
		reportAreaAdPointDailyService.insertReportAreaAdPointDaily(param);
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/reportAreaAdPointDaily/deleteReportAreaAdPointDaily").param("id", "1");
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
	public void selectReportAreaAdPointDailyInMonth() {
		ReportAreaAdPointDailyParams param = new ReportAreaAdPointDailyParams();
		param.setAreaId(1);
		param.setCityId(1);
		param.setProvinceId(1);
		param.setGmtCreate(new Date());
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		param.setProvinceId(1);
		param.setReportTotalPoint(new BigDecimal(1));
		reportAreaAdPointDailyService.insertReportAreaAdPointDaily(param);
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = get("/reportAreaAdPointDaily/selectReportAreaAdPointDailyInMonth")
				.param("bdate", DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()))))
				.param("edate", DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()))));
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
	public void selectReportAreaAdPointDailyByAgentSelectAreaAdPointParam() {
		ReportAreaAdPointDailyParams param = new ReportAreaAdPointDailyParams();
		param.setAreaId(1);
		param.setCityId(1);
		param.setProvinceId(1);
		param.setGmtCreate(new Date());
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		param.setProvinceId(1);
		param.setReportTotalPoint(new BigDecimal(1));
		reportAreaAdPointDailyService.insertReportAreaAdPointDaily(param);
		
		AgentSelectAreaAdPointParam param1 = new AgentSelectAreaAdPointParam();
		param1.setCityId(1);
		param1.setBdate(DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		param1.setEdate(DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		String requestJson = JSONObject.toJSONString(param1);
		RequestBuilder request = post("/reportAreaAdPointDaily/selectReportAreaAdPointDailyByAgentSelectAreaAdPointParam")
				.contentType(MediaType.APPLICATION_JSON).content(requestJson);
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED))
					.andDo(MockMvcResultHandlers.print()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
}

package com.lawu.eshop.statistics.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureQueryParam;
import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureSaveParam;
import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureSaveWrapperParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
@WebAppConfiguration
public class ReportUserIncomeExpenditureControllerTest {
	private MockMvc mvc;

	@Autowired
	private ReportUserIncomeExpenditureController reportUserIncomeExpenditureController;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(reportUserIncomeExpenditureController).build();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void save() {
		ReportUserIncomeExpenditureSaveParam param = new ReportUserIncomeExpenditureSaveParam();
		param.setAccount("13025458808");
		param.setExpenditure(new BigDecimal(10));
		param.setIncome(new BigDecimal(10));
		param.setUserNum("MMM");
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/reportUserIncomeExpenditure/").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
	public void batch() {
		ReportUserIncomeExpenditureSaveParam param = new ReportUserIncomeExpenditureSaveParam();
		param.setAccount("13025458808");
		param.setExpenditure(new BigDecimal(10));
		param.setIncome(new BigDecimal(10));
		param.setUserNum("MMM");
		List<ReportUserIncomeExpenditureSaveParam> list = new ArrayList<ReportUserIncomeExpenditureSaveParam>();
		list.add(param);
		
		ReportUserIncomeExpenditureSaveWrapperParam param1 = new ReportUserIncomeExpenditureSaveWrapperParam();
		param1.setParams(list);
		String requestJson = JSONObject.toJSONString(param1);
		RequestBuilder request = post("/reportUserIncomeExpenditure/batch").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
	public void page() {
		ReportUserIncomeExpenditureQueryParam param = new ReportUserIncomeExpenditureQueryParam();
		param.setCurrentPage(1);
		param.setPageSize(10);
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = put("/reportUserIncomeExpenditure/page").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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

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
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.statistics.constants.AdStatusEnum;
import com.lawu.eshop.statistics.constants.ReportAdEarningsStatusEnum;
import com.lawu.eshop.statistics.param.ReportAdEarningsParam;
import com.lawu.eshop.statistics.param.ReportAdEarningsQueryParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.mapper.ReportAdEarningsDOMapper;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
@WebAppConfiguration
public class ReportAdEarningsControllerTest {

	private MockMvc mvc;

	@Autowired
	private ReportAdEarningsController reportAdEarningsController;

	@Autowired 
	private ReportAdEarningsDOMapper reportAdEarningsDOMapper;
	
	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(reportAdEarningsController).build();
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveReportAdEarnings() {
		ReportAdEarningsParam reportAdEarningsParam = new ReportAdEarningsParam();
		reportAdEarningsParam.setAdCreateTime(new Date());
		reportAdEarningsParam.setAdId(10L);
		reportAdEarningsParam.setAdStatusEnum(AdStatusEnum.AD_STATUS_ADD);
		reportAdEarningsParam.setAdTitle("test");
		reportAdEarningsParam.setAdTotalPoint(new BigDecimal(10));
		reportAdEarningsParam.setAdTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
		reportAdEarningsParam.setLoveTotalPoint(new BigDecimal(10));
		reportAdEarningsParam.setMerchantName("test");
		reportAdEarningsParam.setMerchantNum("BTEST");
		reportAdEarningsParam.setAdStatusEnum(AdStatusEnum.AD_STATUS_PUTED);
		reportAdEarningsParam.setReportAdEarningsStatusEnum(ReportAdEarningsStatusEnum.NORMAL);
		reportAdEarningsParam.setUserTotalPoint(new BigDecimal(100));
		String requestJson = JSONObject.toJSONString(reportAdEarningsParam);
		RequestBuilder request = post("/reportAdEarnings/saveReportAdEarnings").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
	public void selectReportAdEarnings() {
		ReportAdEarningsQueryParam param = new ReportAdEarningsQueryParam();
		param.setCurrentPage(1);
		param.setPageSize(10);
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/reportAdEarnings/selectReportAdEarnings").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
	public void getReportAdEarningsIds() {
		saveReportAdEarnings();
		RequestBuilder request = get("/reportAdEarnings/getReportAdEarningsIds");
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

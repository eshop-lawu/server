package com.lawu.eshop.statistics.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.gexin.fastjson.JSONObject;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.domain.ReportAreaUserRegMonthDO;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaUserRegMonthDOMapper;
import com.lawu.framework.web.HttpCode;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
@WebAppConfiguration
public class ReportAreaRegUserMonthControllerTest {

	private MockMvc mvc;
	
	@Autowired
	private ReportAreaRegUserMonthController reportAreaRegUserMonthController;
	
	@Autowired
	private  ReportAreaUserRegMonthDOMapper reportAreaUserRegMonthDOMapper;
	
	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(reportAreaRegUserMonthController).build();
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getUserRegListDaily() {
		ReportAreaUserRegMonthDO reportAreaUserRegMonthDO = new ReportAreaUserRegMonthDO();
		reportAreaUserRegMonthDO.setCityId(1);
		reportAreaUserRegMonthDO.setCityName("");
		reportAreaUserRegMonthDO.setGmtCreate(new Date());
		reportAreaUserRegMonthDO.setGmtReport(DateUtil.getMonthBefore(new Date()));
		reportAreaUserRegMonthDO.setMemberCount(1);
		reportAreaUserRegMonthDO.setMerchantCount(1);
		reportAreaUserRegMonthDO.setMerchantEntityCount(1);
		reportAreaUserRegMonthDO.setMerchantNormalCount(1);
		reportAreaUserRegMonthDOMapper.insertSelective(reportAreaUserRegMonthDO);
		
		AgentReportParam agentReportParam = new AgentReportParam();
		agentReportParam.setRegionPath("1/1/1");
		agentReportParam.setBeginTime(DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		agentReportParam.setEndTime(DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		String requestJson = JSONObject.toJSONString(agentReportParam);
		RequestBuilder request = post("/regUserMonth/getUserRegListMonth").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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

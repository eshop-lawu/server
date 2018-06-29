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
import com.lawu.eshop.statistics.srv.domain.ReportAreaUserRegDailyDO;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaUserRegDailyDOMapper;
import com.lawu.framework.web.HttpCode;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
@WebAppConfiguration
public class ReportAreaRegUserDailyControllerTest {

	private MockMvc mvc;
	
	@Autowired
	private ReportAreaRegUserDailyController reportAreaRegUserDailyController;
	
	@Autowired
	private  ReportAreaUserRegDailyDOMapper reportAreaUserRegDailyDOMapper;
	
	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(reportAreaRegUserDailyController).build();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getUserRegListDaily() {
		ReportAreaUserRegDailyDO reportAreaUserRegDailyDO = new ReportAreaUserRegDailyDO();
		reportAreaUserRegDailyDO.setCityId(1);
		reportAreaUserRegDailyDO.setCityName("");
		reportAreaUserRegDailyDO.setGmtCreate(new Date());
		reportAreaUserRegDailyDO.setGmtReport(DateUtil.getMonthBefore(new Date()));
		reportAreaUserRegDailyDO.setMemberCount(1);
		reportAreaUserRegDailyDO.setMerchantCount(1);
		reportAreaUserRegDailyDO.setMerchantEntityCount(1);
		reportAreaUserRegDailyDO.setMerchantNormalCount(1);
		reportAreaUserRegDailyDOMapper.insertSelective(reportAreaUserRegDailyDO);
		
		AgentReportParam agentReportParam = new AgentReportParam();
		agentReportParam.setRegionPath("1/1/1");
		agentReportParam.setBeginTime(DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		agentReportParam.setEndTime(DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		String requestJson = JSONObject.toJSONString(agentReportParam);
		RequestBuilder request = post("/regUserDaily/getUserRegListDaily").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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

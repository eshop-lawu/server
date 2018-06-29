package com.lawu.eshop.statistics.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
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

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.statistics.param.ReportKCommonParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.domain.ReportPointConsumeDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportPointConsumeDailyDOExample;
import com.lawu.eshop.statistics.srv.mapper.ReportPointConsumeDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportPointConsumeMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.PointConsumeService;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
@WebAppConfiguration
public class PointConsumeControllerTest {
	
	private MockMvc mvc;

    @Autowired
    private PointConsumeController pointConsumeController;

    @Autowired
	private ReportPointConsumeDailyDOMapper reportPointConsumeDailyDOMapper;
    
	@Autowired
	private ReportPointConsumeMonthDOMapper reportPointConsumeMonthDOMapper;
	
	@Autowired
	private PointConsumeService pointConsumeService;
	
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(pointConsumeController).build();
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveDaily() {
    	ReportKCommonParam param = new ReportKCommonParam();
    	param.setMemberMoney(new BigDecimal(10));
    	param.setMerchantMoney(new BigDecimal(10));
    	param.setTotalMoney(new BigDecimal(20));
    	param.setGmtReport(new Date());
    	param.setGmtCreate(new Date());
    	String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/pointConsume/saveDaily").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();

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
    	param.setMemberMoney(new BigDecimal(10));
    	param.setMerchantMoney(new BigDecimal(10));
    	param.setTotalMoney(new BigDecimal(20));
    	param.setGmtReport(new Date());
    	param.setGmtCreate(new Date());
    	String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/pointConsume/saveMonth").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();

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
    	RequestBuilder request = get("/pointConsume/getDailyList").param("reportDate", "2017-7");
    	 try {
             ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }
    }
    
    
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
	public void deleteDailyByReportDate() {
    	RequestBuilder request = delete("/pointConsume/deleteDailyByReportDate").param("reportDate", "2017-7-1");
   	 try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
	}
    
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
	public void deleteMonthByReportDate() {
    	RequestBuilder request = delete("/pointConsume/deleteMonthByReportDate").param("reportDate", "2017-7-1");
   	 try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
	}
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
	public void selectReport() {
		List<ReportPointConsumeDailyDO> list = reportPointConsumeDailyDOMapper.selectByExample(new ReportPointConsumeDailyDOExample());
    	RequestBuilder request = get("/pointConsume/selectReport").param("bdate", "2017-7-1").param("edate", "2017-7-31");
   	 try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
	}
	
}

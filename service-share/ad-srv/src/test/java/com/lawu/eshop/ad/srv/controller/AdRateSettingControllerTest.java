package com.lawu.eshop.ad.srv.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

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
import com.lawu.eshop.ad.param.RateParam;
import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.eshop.ad.srv.domain.AdRateSettingDO;
import com.lawu.eshop.ad.srv.mapper.AdRateSettingDOMapper;
import com.lawu.framework.web.HttpCode;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2017年11月28日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
@WebAppConfiguration
public class AdRateSettingControllerTest {

	private MockMvc mvc;

	@Autowired
	private AdRateSettingController adRateSettingcontroller;

	@Autowired
	private AdRateSettingDOMapper adRateSettingDOMapper;


	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(adRateSettingcontroller).build();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void queryAdRateSetting() {
		AdRateSettingDO record = new AdRateSettingDO();
		record.setGameTime(1);
		record.setGmtCreate(new Date());
		record.setRate(20);
		adRateSettingDOMapper.insertSelective(record);
		
		RequestBuilder request = get("/adRateSetting/queryAdRateSetting");
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
	public void queryRatePage() {
		AdRateSettingDO record = new AdRateSettingDO();
		record.setGameTime(1);
		record.setGmtCreate(new Date());
		record.setRate(20);
		adRateSettingDOMapper.insertSelective(record);
		RateParam param = new RateParam();
		param.setCurrentPage(1);
		param.setPageSize(20);
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = get("/adRateSetting/queryRatePage").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
	public void saveRateSetting() {
		
		RequestBuilder request = post("/adRateSetting/saveRateSetting").param("gameTime", "1").param("rate", "20");
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
	public void deleteRateSetting() {
		AdRateSettingDO record = new AdRateSettingDO();
		record.setGameTime(1);
		record.setGmtCreate(new Date());
		record.setRate(20);
		adRateSettingDOMapper.insertSelective(record);
		RequestBuilder request = delete("/adRateSetting/deleteRateSetting/"+record.getId());
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

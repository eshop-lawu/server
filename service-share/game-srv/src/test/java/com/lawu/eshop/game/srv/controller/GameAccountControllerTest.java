package com.lawu.eshop.game.srv.controller;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.game.param.GameAccountParam;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.domain.GameAccountDO;
import com.lawu.eshop.game.srv.domain.GameMindAccountDO;
import com.lawu.eshop.game.srv.mapper.GameAccountDOMapper;
import com.lawu.eshop.game.srv.mapper.GameMindAccountDOMapper;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GameAccountControllerTest {
	
	private MockMvc mvc;

	@Autowired
	private GameAccountController gameAccountController;
	
	@Autowired
	private GameAccountDOMapper gameAccountDOMapper;
	
	@Autowired
	private GameMindAccountDOMapper gameMindAccountDOMapper;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(gameAccountController).build();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getAccountInfo() {
		GameAccountDO record = new GameAccountDO();
		record.setAccount("18512345678");
		record.setStarCount(0);
		record.setUserNum("M945115889651417105");
		record.setGmtCreate(new Date());
		record.setGmtModified(new Date());
		gameAccountDOMapper.insertSelective(record);
		
		GameMindAccountDO mindRecord = new GameMindAccountDO();
		mindRecord.setFreeCount(1);
		mindRecord.setIsGetFree(false);
		mindRecord.setUserNum("M945115889651417105");
		mindRecord.setGmtCreate(new Date());
		mindRecord.setGmtModified(new Date());
		gameMindAccountDOMapper.insertSelective(mindRecord);
		
		GameAccountParam param = new GameAccountParam();
		param.setAccount("18512345678");
		param.setType(GameTypeEnum.MIND);
		param.setUserNum("M945115889651417105");
		String requestJson = JSONObject.toJSONString(param);
		
		RequestBuilder request = post("/gameAccount/getAccountInfo").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson);
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print())
					.andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void shareAddAttendCount() {
		GameAccountDO record = new GameAccountDO();
		record.setAccount("18512345678");
		record.setStarCount(0);
		record.setUserNum("M945115889651417105");
		record.setGmtCreate(new Date());
		record.setGmtModified(new Date());
		gameAccountDOMapper.insertSelective(record);
		
		GameMindAccountDO mindRecord = new GameMindAccountDO();
		mindRecord.setFreeCount(1);
		mindRecord.setIsGetFree(false);
		mindRecord.setUserNum("M945115889651417105");
		mindRecord.setGmtCreate(new Date());
		mindRecord.setGmtModified(new Date());
		gameMindAccountDOMapper.insertSelective(mindRecord);
		
		RequestBuilder request = post("/gameAccount/shareAddAttendCount").param("userNum", "M945115889651417105").param("typeEnum", "MIND");
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print())
					.andReturn();
			Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void isFreeCount() {
		GameAccountDO record = new GameAccountDO();
		record.setAccount("18512345678");
		record.setStarCount(0);
		record.setUserNum("M945115889651417105");
		record.setGmtCreate(new Date());
		record.setGmtModified(new Date());
		gameAccountDOMapper.insertSelective(record);
		
		GameMindAccountDO mindRecord = new GameMindAccountDO();
		mindRecord.setFreeCount(1);
		mindRecord.setIsGetFree(false);
		mindRecord.setUserNum("M945115889651417105");
		mindRecord.setGmtCreate(new Date());
		mindRecord.setGmtModified(new Date());
		gameMindAccountDOMapper.insertSelective(mindRecord);
		
		RequestBuilder request = post("/gameAccount/isFreeCount").param("userNum", "M945115889651417105").param("typeEnum", "MIND");
		try {
			ResultActions perform = mvc.perform(request);
			MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print())
					.andReturn();
			Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

}

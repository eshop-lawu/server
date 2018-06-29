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
import com.lawu.eshop.game.constants.GameInformStatusEnum;
import com.lawu.eshop.game.param.DealInformParam;
import com.lawu.eshop.game.param.GameInformParam;
import com.lawu.eshop.game.query.GameInformQuery;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.domain.GameInformDO;
import com.lawu.eshop.game.srv.mapper.GameInformDOMapper;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GameInformControllerTest {
	
	private MockMvc mvc;

	@Autowired
	private GameInformController gameInformController;
	
	@Autowired
	private GameInformDOMapper gameInformDOMapper;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(gameInformController).build();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveGameInform() {
		GameInformParam param = new GameInformParam();
		param.setAttendNum("PU975699441409327126");
		param.setCheat(true);
		param.setQuestionError(true);
		param.setResultError(false);
		param.setGameType(GameTypeEnum.MIND);
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/gameInform/saveGameInform").contentType(MediaType.APPLICATION_JSON)
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
	public void page() {
		GameInformQuery query = new GameInformQuery();
		query.setCurrentPage(1);
		query.setPageSize(20);
		String requestJson = JSONObject.toJSONString(query);
		RequestBuilder request = post("/gameInform/page").contentType(MediaType.APPLICATION_JSON)
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
	public void dealInform() {
		
		GameInformDO record = new GameInformDO();
		record.setAttendNum("PU975699441409327126");
		record.setCheat(true);
		record.setGameType(GameTypeEnum.MIND.getVal());
		record.setQuestionError(true);
		record.setResultError(false);
		record.setStatus(GameInformStatusEnum.ON_DEAL_WITH.getVal());
		record.setUserNum("M944129901240254464");
		record.setGmtCreate(new Date());
		record.setGmtModified(new Date());
		Integer id =gameInformDOMapper.insertSelective(record);
		
		DealInformParam param = new DealInformParam();
		param.setAuditorId(1);
		param.setAuditorName("test");
		param.setId(Long.parseLong(id.toString()));
		param.setRemark("test");
		param.setStatusEnum(GameInformStatusEnum.DEAL_WITH);
		String requestJson = JSONObject.toJSONString(param);
		
		RequestBuilder request = post("/gameInform/dealInform").contentType(MediaType.APPLICATION_JSON)
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

}

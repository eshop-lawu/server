package com.lawu.eshop.game.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.param.GameMindConfigParam;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.param.GamePointAllotParam;
import com.lawu.eshop.common.param.SecScoreParam;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.domain.GameMindConfigDO;
import com.lawu.eshop.game.srv.domain.GamePointAllotDO;
import com.lawu.eshop.game.srv.mapper.GameMindConfigDOMapper;
import com.lawu.eshop.game.srv.mapper.GamePointAllotDOMapper;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GameMindConfigControllerTest {
	
	private MockMvc mvc;

	@Autowired
	private GameMindConfigController gameMindConfigController;
	
	@Autowired
	private GameMindConfigDOMapper gameMindConfigDOMapper;
	
	@Autowired
	private GamePointAllotDOMapper gamePointAllotDOMapper;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(gameMindConfigController).build();
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveGameMindConfig() {
		GameMindConfigParam param = new GameMindConfigParam();
		param.setAttendMaxPoint(2000);
		param.setAttendPoint(8);
		param.setAwardPoint(8);
		param.setAwardStar(1);
		param.setCountDown(10);
		param.setDeductStar(1);
		param.setFreeCount(1);
		param.setLastScoreMultiple(2);
		param.setQuestionCount(5);
		param.setRoomMaxNum(8);
		param.setShareAttendCount(1);
		param.setSuccessScore(250);
		List<SecScoreParam> secScore = new ArrayList<>();
		SecScoreParam score = new SecScoreParam();
		//score.setLevel(Byte.parseByte("1"));
		score.setPoint(20);
		score.setSecond(1);
		secScore.add(score);
		param.setSecScore(secScore);
		List<GamePointAllotParam> allotList = new ArrayList<>();
		GamePointAllotParam allot = new GamePointAllotParam();
		allot.setAttendCount(2);
		List<String> rankPoint = new ArrayList<>();
		rankPoint.add("0.9");
		rankPoint.add("0");
		List<String> rankStar = new ArrayList<>();
		rankStar.add("1");
		rankStar.add("-1");
		allot.setRankPoint(rankPoint);
		allot.setRankStar(rankStar);
		param.setAllotList(allotList);
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/gameMindConfig/saveGameMindConfig").contentType(MediaType.APPLICATION_JSON)
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
	public void setEnable() {
		saveGameMindConfig();
		RequestBuilder request = post("/gameMindConfig/setEnable").param("statusEnum", "DISABLE");
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
	public void updateGameMindConfig() {
		GameMindConfigDO record = new GameMindConfigDO();
		record.setAttendMaxPoint(2000);
		record.setAttendPoint(8);
		record.setAwardPoint(8);
		record.setAwardStar(1);
		record.setCountDown(10);
		record.setDeductStar(1);
		record.setFreeCount(1);
		record.setLastScoreMultiple(2);
		record.setQuestionCount(5);
		record.setRoomMaxNum(8);
		record.setShareAttendCount(1);
		record.setSuccessScore(250);
		record.setSecScore("[{'level':1,'point':90,'second':1},{'level':2,'point':80,'second':20},{'level':2,'point':70,'second':30},{'level':3,'point':60,'second':40},{'level':3,'point':20,'second':60}]");
		record.setStatus(GameConfigStatusEnum.ENABLE.getVal());
		gameMindConfigDOMapper.insertSelective(record);
		
		GamePointAllotDO allotRecoed = new GamePointAllotDO();
		allotRecoed.setAttendCount(5);
		allotRecoed.setStatus(StatusEnum.VALID.getValue());
		allotRecoed.setGameType(GameTypeEnum.MIND.getVal());
		allotRecoed.setRankPoint("['0.48','0.28','0.1','0.05','0']");
		allotRecoed.setRankStar("['1','1','0','-1','-1']");
		Integer id =gamePointAllotDOMapper.insertSelective(allotRecoed);
		
		GameMindConfigParam param = new GameMindConfigParam();
		param.setAttendMaxPoint(2000);
		param.setAttendPoint(8);
		param.setAwardPoint(8);
		param.setAwardStar(1);
		param.setCountDown(10);
		param.setDeductStar(1);
		param.setFreeCount(1);
		param.setLastScoreMultiple(2);
		param.setQuestionCount(5);
		param.setRoomMaxNum(8);
		param.setShareAttendCount(1);
		param.setSuccessScore(250);
		List<SecScoreParam> secScore = new ArrayList<>();
		SecScoreParam score = new SecScoreParam();
		//score.setLevel(Byte.parseByte("1"));
		score.setPoint(20);
		score.setSecond(1);
		secScore.add(score);
		param.setSecScore(secScore);
		List<GamePointAllotParam> allotList = new ArrayList<>();
		GamePointAllotParam allot = new GamePointAllotParam();
		allot.setAttendCount(2);
		List<String> rankPoint = new ArrayList<>();
		rankPoint.add("0.9");
		rankPoint.add("0");
		List<String> rankStar = new ArrayList<>();
		rankStar.add("1");
		rankStar.add("-1");
		allot.setRankPoint(rankPoint);
		allot.setRankStar(rankStar);
		param.setAllotList(allotList);
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/gameMindConfig/updateGameMindConfig/"+id.toString()).contentType(MediaType.APPLICATION_JSON)
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
	public void getGameMindConfig() {
		GameMindConfigDO record = new GameMindConfigDO();
		record.setAttendMaxPoint(2000);
		record.setAttendPoint(8);
		record.setAwardPoint(8);
		record.setAwardStar(1);
		record.setCountDown(10);
		record.setDeductStar(1);
		record.setFreeCount(1);
		record.setLastScoreMultiple(2);
		record.setQuestionCount(5);
		record.setRoomMaxNum(8);
		record.setShareAttendCount(1);
		record.setSuccessScore(250);
		record.setSecScore("[{'level':1,'point':90,'second':1},{'level':2,'point':80,'second':20},{'level':2,'point':70,'second':30},{'level':3,'point':60,'second':40},{'level':3,'point':20,'second':60}]");
		record.setStatus(GameConfigStatusEnum.ENABLE.getVal());
		gameMindConfigDOMapper.insertSelective(record);
		
		GamePointAllotDO allotRecoed = new GamePointAllotDO();
		allotRecoed.setAttendCount(5);
		allotRecoed.setStatus(StatusEnum.VALID.getValue());
		allotRecoed.setGameType(GameTypeEnum.MIND.getVal());
		allotRecoed.setRankPoint("['0.48','0.28','0.1','0.05','0']");
		allotRecoed.setRankStar("['1','1','0','-1','-1']");
		Integer id =gamePointAllotDOMapper.insertSelective(allotRecoed);
		
		RequestBuilder request = get("/gameMindConfig/getGameMindConfig");
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

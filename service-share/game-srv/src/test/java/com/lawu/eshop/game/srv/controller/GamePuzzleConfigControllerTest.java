package com.lawu.eshop.game.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameCommonNumDTO;
import com.lawu.eshop.cache.param.GameDifficultyParam;
import com.lawu.eshop.cache.param.GamePuzzleConfigParam;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.param.GamePointAllotParam;
import com.lawu.eshop.common.param.SecScoreParam;
import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.param.GameAttendSaveParam;
import com.lawu.eshop.game.param.GamePuzzleGetPicSaveAttendParam;
import com.lawu.eshop.game.param.GamePuzzleRewardPointAndStarParam;
import com.lawu.eshop.game.param.ValidatePointStatus;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.domain.GamePointAllotDO;
import com.lawu.eshop.game.srv.domain.GamePuzzleAttendRecordDO;
import com.lawu.eshop.game.srv.domain.GamePuzzleConfigDO;
import com.lawu.eshop.game.srv.domain.GamePuzzleDifficultyDO;
import com.lawu.eshop.game.srv.mapper.GamePointAllotDOMapper;
import com.lawu.eshop.game.srv.mapper.GamePuzzleAttendRecordDOMapper;
import com.lawu.eshop.game.srv.mapper.GamePuzzleConfigDOMapper;
import com.lawu.eshop.game.srv.mapper.GamePuzzleDifficultyDOMapper;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GamePuzzleConfigControllerTest {
	
	private MockMvc mvc;

	@Autowired
	private GamePuzzleConfigController gamePuzzleConfigController;
	
	@Autowired
	private GamePuzzleConfigDOMapper gamePuzzleConfigDOMapper;
	
	@Autowired
	private GamePointAllotDOMapper gamePointAllotDOMapper;
	
	@Autowired
	private GamePuzzleDifficultyDOMapper gamePuzzleDifficultyDOMapper;
	
	@Autowired
	private GamePuzzleAttendRecordDOMapper gamePuzzleAttendRecordDOMapper;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(gamePuzzleConfigController).build();
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveGamePuzzleConfig() {
		GamePuzzleConfigParam param = new GamePuzzleConfigParam();
		param.setAttendMaxPoint(2000);
		param.setAttendPoint(8);
		param.setAwardPoint(8);
		param.setAwardStar(1);
		param.setCountDown(10);
		param.setDeductStar(1);
		param.setFreeCount(1);
		param.setRoomMaxNum(8);
		param.setShareAttendCount(1);
		param.setPicCount(3);
		List<SecScoreParam> secScore = new ArrayList<>();
		SecScoreParam score = new SecScoreParam();
		//score.setLevel(Byte.parseByte("1"));
		score.setPoint(20);
		score.setSecond(1);
		secScore.add(score);
		
		List<GameDifficultyParam> difficultys = new ArrayList<>();
		GameDifficultyParam difficulty = new GameDifficultyParam();
		difficulty.setChallengeTime(60);
		difficulty.setCoefficient(3);
		difficulty.setLeverEnum(GameHardLevelEnum.SIMPLE);
		difficulty.setSecScore(secScore);
		difficultys.add(difficulty);
		param.setDifficultys(difficultys);
		
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
		RequestBuilder request = post("/gamePuzzleConfig/saveGamePuzzleConfig").contentType(MediaType.APPLICATION_JSON)
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
	public void getPicByHardLevel() {
		GamePuzzleGetPicSaveAttendParam param = new GamePuzzleGetPicSaveAttendParam();
		param.setAttendType(AttendTypeEnum.MANYPEOPLE);
		param.setLevel(GameHardLevelEnum.SIMPLE);
		param.setPicCount(3);
		param.setRoomNum("PU975699441409327126");
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/gamePuzzleConfig/getPicByHardLevel").contentType(MediaType.APPLICATION_JSON)
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
		saveGamePuzzleConfig();
		RequestBuilder request = post("/gamePuzzleConfig/setEnable").param("statusEnum", "DISABLE");
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
	public void updateGamePuzzleConfig() {
		GamePuzzleConfigDO record = new GamePuzzleConfigDO();
		record.setAttendMaxPoint(2000);
		record.setAttendPoint(8);
		record.setAwardPoint(8);
		record.setAwardStar(1);
		record.setCountDown(10);
		record.setDeductStar(1);
		record.setFreeCount(1);
		record.setRoomMaxNum(8);
		record.setShareAttendCount(1);
		record.setStatus(GameConfigStatusEnum.ENABLE.getVal());
		record.setPicCount(3);
		Integer id= gamePuzzleConfigDOMapper.insertSelective(record);
		
		GamePointAllotDO allotRecoed = new GamePointAllotDO();
		allotRecoed.setAttendCount(5);
		allotRecoed.setStatus(StatusEnum.VALID.getValue());
		allotRecoed.setGameType(GameTypeEnum.MIND.getVal());
		allotRecoed.setRankPoint("['0.48','0.28','0.1','0.05','0']");
		allotRecoed.setRankStar("['1','1','0','-1','-1']");
		gamePointAllotDOMapper.insertSelective(allotRecoed);
		
		GamePuzzleDifficultyDO difficultyDO = new GamePuzzleDifficultyDO();
		difficultyDO.setChallengeTime(60);
		difficultyDO.setCoefficient(3);
		difficultyDO.setGmtCreate(new Date());
		difficultyDO.setGmtModified(new Date());
		difficultyDO.setType(GameHardLevelEnum.SIMPLE.getVal());
		difficultyDO.setSecScore("[{'level':1,'point':90,'second':1},{'level':2,'point':80,'second':20},{'level':2,'point':70,'second':30},{'level':3,'point':60,'second':40}]");
		gamePuzzleDifficultyDOMapper.insertSelective(difficultyDO);
		
		GamePuzzleConfigParam param = new GamePuzzleConfigParam();
		param.setAttendMaxPoint(2000);
		param.setAttendPoint(8);
		param.setAwardPoint(8);
		param.setAwardStar(1);
		param.setCountDown(10);
		param.setDeductStar(1);
		param.setFreeCount(1);
		param.setRoomMaxNum(8);
		param.setShareAttendCount(1);
		param.setPicCount(3);
		List<SecScoreParam> secScore = new ArrayList<>();
		SecScoreParam score = new SecScoreParam();
		//score.setLevel(Byte.parseByte("1"));
		score.setPoint(20);
		score.setSecond(1);
		secScore.add(score);
		
		List<GameDifficultyParam> difficultys = new ArrayList<>();
		GameDifficultyParam difficulty = new GameDifficultyParam();
		difficulty.setChallengeTime(60);
		difficulty.setCoefficient(3);
		difficulty.setLeverEnum(GameHardLevelEnum.SIMPLE);
		difficulty.setSecScore(secScore);
		difficultys.add(difficulty);
		param.setDifficultys(difficultys);
		
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
		allotList.add(allot);
		param.setAllotList(allotList);
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/gamePuzzleConfig/updateGamePuzzleConfig/"+id.toString()).contentType(MediaType.APPLICATION_JSON)
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
	public void saveRandomPuzzleGameAttendInfo() {
		GameAttendSaveParam param = new GameAttendSaveParam();
		param.setAttendNum("PU975699441409327126");
		param.setAttendPoint(8);
		param.setAttendStar(1);
		param.setAttendType(AttendTypeEnum.MANYPEOPLE);
		param.setDifficultyId(1);
		param.setFree(false);
		param.setPicCount(3);
		param.setSubStar(1);
		param.setUserAccount("13111111111");
		param.setUserNum("M950617291974770696");
		List<GameCommonNumDTO> userNums = new ArrayList<>();
		GameCommonNumDTO dto = new GameCommonNumDTO();
		dto.setRoomMaster(true);
		dto.setUserNum("M950617291974770696");
		//param.setUserNums(userNums);
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/gamePuzzleConfig/saveRandomPuzzleGameAttendInfo").contentType(MediaType.APPLICATION_JSON)
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
	public void validatePointDeductionStatus() {
		ValidatePointStatus param = new ValidatePointStatus();
		param.setAttendNum("PU975699441409327126");
		param.setUserNum("M950617291974770696");
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/gamePuzzleConfig/validatePointDeductionStatus").contentType(MediaType.APPLICATION_JSON)
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
	public void rewardPointAndStar() {
		GamePuzzleAttendRecordDO game = new GamePuzzleAttendRecordDO();
		game.setUserNum("M950617291974770696");
		game.setAttendNum("PU975699441409327126");
		game.setAttendType(AttendTypeEnum.MANYPEOPLE.getVal());
		game.setRoomNum("R975691");
		game.setAttendCount(8);
		game.setDifficulty(Byte.parseByte("1"));
		game.setAttendPoint(8);
		game.setAttendStar(1);
		game.setPuzzlePicId(1l);
		game.setStatus(Byte.parseByte("1"));
		game.setGameScore(8);
		game.setGameRank(3);
		game.setGameUseTime(30);
		game.setRewardPoint(new BigDecimal(10));
		game.setRewardStar(1);
		game.setGmtModified(new Date());
		game.setGmtCreate(new Date());
		gamePuzzleAttendRecordDOMapper.insertSelective(game);
		
		GamePuzzleRewardPointAndStarParam param = new GamePuzzleRewardPointAndStarParam();
		param.setAttendNum("PU975699441409327126");
		param.setUserNum("M950617291974770696");
		param.setGameRank(3);
		param.setGameUseTime(50);
		param.setSocre(8);
		param.setStar(1);
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/gamePuzzleConfig/rewardPointAndStar").contentType(MediaType.APPLICATION_JSON)
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
	public void getGamePuzzleConfig() {
		GamePuzzleConfigDO record = new GamePuzzleConfigDO();
		record.setAttendMaxPoint(2000);
		record.setAttendPoint(8);
		record.setAwardPoint(8);
		record.setAwardStar(1);
		record.setCountDown(10);
		record.setDeductStar(1);
		record.setFreeCount(1);
		record.setRoomMaxNum(8);
		record.setShareAttendCount(1);
		record.setStatus(GameConfigStatusEnum.ENABLE.getVal());
		record.setPicCount(3);
		Integer id= gamePuzzleConfigDOMapper.insertSelective(record);
		
		GamePointAllotDO allotRecoed = new GamePointAllotDO();
		allotRecoed.setAttendCount(5);
		allotRecoed.setStatus(StatusEnum.VALID.getValue());
		allotRecoed.setGameType(GameTypeEnum.MIND.getVal());
		allotRecoed.setRankPoint("['0.48','0.28','0.1','0.05','0']");
		allotRecoed.setRankStar("['1','1','0','-1','-1']");
		gamePointAllotDOMapper.insertSelective(allotRecoed);
		
		GamePuzzleDifficultyDO difficultyDO = new GamePuzzleDifficultyDO();
		difficultyDO.setChallengeTime(60);
		difficultyDO.setCoefficient(3);
		difficultyDO.setGmtCreate(new Date());
		difficultyDO.setGmtModified(new Date());
		difficultyDO.setType(GameHardLevelEnum.SIMPLE.getVal());
		difficultyDO.setSecScore("[{'level':1,'point':90,'second':1},{'level':2,'point':80,'second':20},{'level':2,'point':70,'second':30},{'level':3,'point':60,'second':40}]");
		gamePuzzleDifficultyDOMapper.insertSelective(difficultyDO);
		
		RequestBuilder request = get("/gamePuzzleConfig/getGamePuzzleConfig");
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

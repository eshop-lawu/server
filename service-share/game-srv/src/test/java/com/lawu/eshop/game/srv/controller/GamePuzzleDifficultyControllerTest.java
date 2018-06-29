package com.lawu.eshop.game.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.domain.GamePuzzleDifficultyDO;
import com.lawu.eshop.game.srv.mapper.GamePuzzleDifficultyDOMapper;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GamePuzzleDifficultyControllerTest {
	
	private MockMvc mvc;

	@Autowired
	private GamePuzzleDifficultyController gamePuzzleDifficultyController;
	
	@Autowired
	private GamePuzzleDifficultyDOMapper gamePuzzleDifficultyDOMapper;
	

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(gamePuzzleDifficultyController).build();
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getPuzzleDifficulty() {
		GamePuzzleDifficultyDO difficulty = new GamePuzzleDifficultyDO();
		difficulty.setCoefficient(3);
		difficulty.setType(GameHardLevelEnum.SIMPLE.getVal());
		difficulty.setGmtCreate(new Date());
		difficulty.setGmtModified(new Date());
		difficulty.setChallengeTime(30);
		difficulty.setSecScore("[{'level':1,'point':90,'second':1}]");
		gamePuzzleDifficultyDOMapper.insertSelective(difficulty);
		
		RequestBuilder request = get("/gamePuzzleDifficulty/getPuzzleDifficulty");
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
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getPuzzleDifficultyById() {
		GamePuzzleDifficultyDO difficulty = new GamePuzzleDifficultyDO();
		difficulty.setCoefficient(3);
		difficulty.setType(GameHardLevelEnum.SIMPLE.getVal());
		difficulty.setGmtCreate(new Date());
		difficulty.setGmtModified(new Date());
		difficulty.setChallengeTime(30);
		difficulty.setSecScore("[{'level':1,'point':90,'second':1}]");
		Integer id = gamePuzzleDifficultyDOMapper.insertSelective(difficulty);
		
		RequestBuilder request = get("/gamePuzzleDifficulty/getPuzzleDifficultyById").param("id", id.toString());
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

package com.lawu.eshop.game.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.param.GameMindQuestionParam;
import com.lawu.eshop.game.param.MindAttendParam;
import com.lawu.eshop.game.query.GameMindQuestionQuery;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.domain.GameMindQuestionDO;
import com.lawu.eshop.game.srv.mapper.GameMindQuestionDOMapper;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GameMindQuestionControllerTest {
	
	private MockMvc mvc;

	@Autowired
	private GameMindQuestionController gameMindQuestionController;
	
	@Autowired
	private GameMindQuestionDOMapper gameMindQuestionDOMapper;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(gameMindQuestionController).build();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveGameMindQuestion() {
		GameMindQuestionParam param = new GameMindQuestionParam();
		param.setCategoryId(2l);
		param.setRightAnswer(1);
		param.setTitle("1+1=？");
		List<String> answers = new ArrayList<>();
		answers.add("1");
		answers.add("2");
		answers.add("3");
		answers.add("4");
		param.setAnswers(answers);
		String requestJson = JSONObject.toJSONString(param);
		RequestBuilder request = post("/gameMindQuestion/saveGameMindQuestion").contentType(MediaType.APPLICATION_JSON)
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
	public void setGameMindQuestion() {
		GameMindQuestionDO param = new GameMindQuestionDO();
		param.setCategoryId(2l);
		param.setRightAnswer(1);
		param.setTitle("1+1=？");
		List<String> answers = new ArrayList<>();
		answers.add("1");
		answers.add("2");
		answers.add("3");
		answers.add("4");
		param.setAnswers(JSONObject.toJSONString(param.getAnswers()));
		Integer id =gameMindQuestionDOMapper.insertSelective(param);
		
		RequestBuilder request = put("/gameMindQuestion/saveGameMindQuestion"+id.toString());
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
		GameMindQuestionDO param = new GameMindQuestionDO();
		param.setCategoryId(2l);
		param.setRightAnswer(1);
		param.setTitle("1+1=？");
		List<String> answers = new ArrayList<>();
		answers.add("1");
		answers.add("2");
		answers.add("3");
		answers.add("4");
		param.setAnswers(JSONObject.toJSONString(param.getAnswers()));
		Integer id =gameMindQuestionDOMapper.insertSelective(param);
		
		GameMindQuestionQuery query = new GameMindQuestionQuery();
		query.setCurrentPage(1);
		query.setPageSize(20);
		String requestJson = JSONObject.toJSONString(param);
		
		RequestBuilder request = post("/gameMindQuestion/page").contentType(MediaType.APPLICATION_JSON)
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
	public void getMindQuestionList() {
		GameMindQuestionDO param = new GameMindQuestionDO();
		param.setCategoryId(2l);
		param.setRightAnswer(1);
		param.setTitle("1+1=？");
		List<String> answers = new ArrayList<>();
		answers.add("1");
		answers.add("2");
		answers.add("3");
		answers.add("4");
		param.setAnswers(JSONObject.toJSONString(param.getAnswers()));
		Integer id =gameMindQuestionDOMapper.insertSelective(param);
		
		MindAttendParam attend  = new MindAttendParam();
		attend.setAttendPoint(8);
		attend.setAttendStar(1);
		attend.setAttendType(AttendTypeEnum.STANDALONE);
		attend.setQuestionIds("1,2,4,5");
		attend.setRoomNum("R12345");
		String requestJson = JSONObject.toJSONString(param);
		
		RequestBuilder request = post("/gameMindQuestion/getMindQuestionList").contentType(MediaType.APPLICATION_JSON)
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
	public void getGameMindQuestionCategory() {
	
		RequestBuilder request = get("/gameMindQuestion/getGameMindQuestionCategory");
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

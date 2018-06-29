package com.lawu.eshop.game.srv.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.param.GameMindQuestionParam;
import com.lawu.eshop.game.param.MindAttendParam;
import com.lawu.eshop.game.query.GameMindQuestionQuery;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.bo.GameMindQuestionBO;
import com.lawu.eshop.game.srv.bo.GameMindQuestionCategoryBO;
import com.lawu.eshop.game.srv.bo.GameQuestionResultBO;
import com.lawu.eshop.game.srv.domain.GameMindQuestionDO;
import com.lawu.eshop.game.srv.domain.GameMindQuestionDOExample;
import com.lawu.eshop.game.srv.mapper.GameMindQuestionDOMapper;
import com.lawu.eshop.game.srv.service.GameMindQuestionService;
import com.lawu.framework.core.page.Page;

/**
 * 题库单元测试
 * 
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月20日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GameMindQuestionServiceImplTest {
	
	@Autowired
	private GameMindQuestionService gameMindQuestionService;
	
	@Autowired
	private GameMindQuestionDOMapper gameMindQuestionDOMapper;
	
	@Transactional
    @Test
    @Rollback
    public void saveGameMindQuestion(){
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
		gameMindQuestionService.saveGameMindQuestion(param);
		GameMindQuestionDOExample example = new GameMindQuestionDOExample();
		example.createCriteria().andStatusEqualTo(GameConfigStatusEnum.ENABLE.getVal());
		List<GameMindQuestionDO> list = gameMindQuestionDOMapper.selectByExample(example);
		Assert.assertEquals(1,list.size());
       
    }
	
	
	@Transactional
    @Test
    @Rollback
    public void setGameMindQuestion(){
		GameMindQuestionDO param = new GameMindQuestionDO();
		param.setCategoryId(2l);
		param.setRightAnswer(1);
		param.setTitle("1+1=？");
		param.setAnswers("[\"1\",\"2\",\"3\",\"以上都错\"]");
		Integer id =gameMindQuestionDOMapper.insertSelective(param);
		
		gameMindQuestionService.setGameMindQuestion(Long.parseLong(id.toString()));
		
		GameMindQuestionDOExample example = new GameMindQuestionDOExample();
		example.createCriteria().andStatusEqualTo(GameConfigStatusEnum.DISABLE.getVal());
		List<GameMindQuestionDO> list = gameMindQuestionDOMapper.selectByExample(example);
		Assert.assertEquals(1,list.size());
       
    }
	
	
	@Transactional
    @Test
    @Rollback
    public void page(){
		GameMindQuestionDO param = new GameMindQuestionDO();
		param.setCategoryId(2l);
		param.setRightAnswer(1);
		param.setTitle("1+1=？");
		param.setAnswers("[\"1\",\"2\",\"3\",\"以上都错\"]");
		Integer id =gameMindQuestionDOMapper.insertSelective(param);
		
		GameMindQuestionQuery query = new GameMindQuestionQuery();
		query.setCurrentPage(1);
		query.setPageSize(20);
		Page<GameMindQuestionBO> page = gameMindQuestionService.page(query);
		
		Assert.assertEquals(1,page.getRecords().size());
       
    }
	
	@Transactional
    @Test
    @Rollback
    public void getMindQuestionList(){
		for (int i=0 ;i<10;i++){
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
		}
		
		MindAttendParam attend  = new MindAttendParam();
		attend.setAttendPoint(8);
		attend.setAttendStar(1);
		attend.setAttendType(AttendTypeEnum.STANDALONE);
		attend.setQuestionIds("1,2,4,5");
		attend.setRoomNum("R12345");
		GameQuestionResultBO bo = gameMindQuestionService.getMindQuestionList(attend);
		
		Assert.assertNotNull(bo);
       
    }
	
	@Transactional
    @Test
    @Rollback
    public void getGameMindQuestionCategory(){
		List<GameMindQuestionCategoryBO> list = gameMindQuestionService.getGameMindQuestionCategory();
		Assert.assertTrue(list.size()>0);
       
    }
	
	
	@Transactional
    @Test
    @Rollback
    public void getQuestion(){
		GameMindQuestionDO param = new GameMindQuestionDO();
		param.setCategoryId(2L);
		param.setRightAnswer(1);
		param.setTitle("1+1=？");
		param.setAnswers("[\"1\",\"2\",\"3\",\"以上都错\"]");
		Integer id =gameMindQuestionDOMapper.insertSelective(param);
		GameMindQuestionBO gameMindQuestionBO =gameMindQuestionService.getQuestion(Long.parseLong(id.toString()),1);
		Assert.assertNotNull(gameMindQuestionBO);
       
    }
	
	

}

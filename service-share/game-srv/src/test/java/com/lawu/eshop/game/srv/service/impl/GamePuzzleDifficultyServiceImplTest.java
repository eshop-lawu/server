package com.lawu.eshop.game.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.game.param.DifficultyInfoParam;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.bo.GamePuzzleDifficultyBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleMaxPointStartByDiffBO;
import com.lawu.eshop.game.srv.bo.GamePuzzlePointStartByDiffBO;
import com.lawu.eshop.game.srv.domain.GamePuzzleDifficultyDO;
import com.lawu.eshop.game.srv.mapper.GamePuzzleDifficultyDOMapper;
import com.lawu.eshop.game.srv.service.GamePuzzleDifficultyService;

/**
 * 困难级别单元测试
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月21日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GamePuzzleDifficultyServiceImplTest {
	
	@Autowired
	private GamePuzzleDifficultyDOMapper gamePuzzleDifficultyDOMapper;
	
	@Autowired
	private GamePuzzleDifficultyService gamePuzzleDifficultyService;
	
	
	@Transactional
    @Test
    @Rollback
    public void getPuzzleDifficultyByHardLevel(){
		
		GamePuzzleDifficultyDO difficulty = new GamePuzzleDifficultyDO();
		difficulty.setCoefficient(3);
		difficulty.setType(GameHardLevelEnum.SIMPLE.getVal());
		difficulty.setGmtCreate(new Date());
		difficulty.setGmtModified(new Date());
		difficulty.setChallengeTime(30);
		difficulty.setSecScore("[{'level':1,'point':90,'second':1}]");
		gamePuzzleDifficultyDOMapper.insertSelective(difficulty);
		GamePuzzleDifficultyBO gamePuzzleDifficultyBO = gamePuzzleDifficultyService.getPuzzleDifficultyByHardLevel(GameHardLevelEnum.SIMPLE);
		
		Assert.assertNotNull(gamePuzzleDifficultyBO);
    }
	
	@Transactional
    @Test
    @Rollback
    public void getPuzzleDifficulty(){
		
		GamePuzzleDifficultyDO difficulty = new GamePuzzleDifficultyDO();
		difficulty.setCoefficient(3);
		difficulty.setType(GameHardLevelEnum.SIMPLE.getVal());
		difficulty.setGmtCreate(new Date());
		difficulty.setGmtModified(new Date());
		difficulty.setChallengeTime(30);
		difficulty.setSecScore("[{'level':1,'point':90,'second':1}]");
		gamePuzzleDifficultyDOMapper.insertSelective(difficulty);
		List<GamePuzzleDifficultyBO> list = gamePuzzleDifficultyService.getPuzzleDifficulty();
		
		Assert.assertEquals(1,list.size());
    }
	
	
	@Transactional
    @Test
    @Rollback
    public void getPuzzleDifficultyById(){
		
		GamePuzzleDifficultyDO difficulty = new GamePuzzleDifficultyDO();
		difficulty.setCoefficient(3);
		difficulty.setType(GameHardLevelEnum.SIMPLE.getVal());
		difficulty.setGmtCreate(new Date());
		difficulty.setGmtModified(new Date());
		difficulty.setChallengeTime(30);
		difficulty.setSecScore("[{'level':1,'point':90,'second':1}]");
		Integer id = gamePuzzleDifficultyDOMapper.insertSelective(difficulty);
		GamePuzzleDifficultyBO gamePuzzleDifficultyBO = gamePuzzleDifficultyService.getPuzzleDifficultyById(Long.parseLong(id.toString()));
		
		Assert.assertNotNull(gamePuzzleDifficultyBO);
    }
	
	
	@Transactional
    @Test
    @Rollback
    public void getPuzzlePointStart(){
		DifficultyInfoParam param = new DifficultyInfoParam();
		param.setLevelEnum(GameHardLevelEnum.SIMPLE);
		param.setSecond(10);
		GamePuzzlePointStartByDiffBO gamePuzzlePointStartByDiffBO = gamePuzzleDifficultyService.getPuzzlePointStart(param);
		Assert.assertNotNull(gamePuzzlePointStartByDiffBO);
    }
	
	
	@Transactional
    @Test
    @Rollback
    public void getPuzzleMaxPointLevel(){
		GamePuzzleMaxPointStartByDiffBO gamePuzzlePointStartByDiffBO = gamePuzzleDifficultyService.getPuzzleMaxPointLevel(GameHardLevelEnum.SIMPLE);
		Assert.assertNotNull(gamePuzzlePointStartByDiffBO);
    }
	
 
}

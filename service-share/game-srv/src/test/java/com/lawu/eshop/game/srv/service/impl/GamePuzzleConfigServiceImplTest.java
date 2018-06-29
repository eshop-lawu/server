package com.lawu.eshop.game.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.lawu.eshop.game.param.GamePuzzleRewardPointAndStarParam;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.bo.GameAttendSaveReturnBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleConfigBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleResultBO;
import com.lawu.eshop.game.srv.domain.GamePointAllotDO;
import com.lawu.eshop.game.srv.domain.GamePuzzleAttendRecordDO;
import com.lawu.eshop.game.srv.domain.GamePuzzleConfigDO;
import com.lawu.eshop.game.srv.domain.GamePuzzleConfigDOExample;
import com.lawu.eshop.game.srv.domain.GamePuzzleDifficultyDO;
import com.lawu.eshop.game.srv.mapper.GamePointAllotDOMapper;
import com.lawu.eshop.game.srv.mapper.GamePuzzleAttendRecordDOMapper;
import com.lawu.eshop.game.srv.mapper.GamePuzzleConfigDOMapper;
import com.lawu.eshop.game.srv.mapper.GamePuzzleDifficultyDOMapper;
import com.lawu.eshop.game.srv.service.GamePuzzleConfigService;

/**
 * 拼图游戏配置单元测试
 * 
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月20日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GamePuzzleConfigServiceImplTest {
	
	@Autowired
	private GamePuzzleConfigService gamePuzzleConfigService;
	
	@Autowired
	private GamePuzzleConfigDOMapper gamePuzzleConfigDOMapper;
	
	@Autowired
	private GamePointAllotDOMapper gamePointAllotDOMapper;
	
	@Autowired
	private GamePuzzleDifficultyDOMapper gamePuzzleDifficultyDOMapper;
	
	@Autowired
	private GamePuzzleAttendRecordDOMapper gamePuzzleAttendRecordDOMapper;
	
	@Transactional
    @Test
    @Rollback
    public void saveGameMindConfig(){
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
		gamePuzzleConfigService.saveGamePuzzleConfig(param);
		
		List<GamePuzzleConfigDO> list = gamePuzzleConfigDOMapper.selectByExample(null);
		Assert.assertEquals(1,list.size());
       
    }
	
	
	@Transactional
    @Test
    @Rollback
    public void setEnable(){
		GamePuzzleConfigDO param = new GamePuzzleConfigDO();
		param.setAttendMaxPoint(2000);
		param.setAttendPoint(8);
		param.setAwardPoint(8);
		param.setAwardStar(1);
		param.setCountDown(10);
		param.setDeductStar(1);
		param.setFreeCount(1);
		param.setRoomMaxNum(8);
		param.setShareAttendCount(1);
		param.setStatus(GameConfigStatusEnum.ENABLE.getVal());
		param.setPicCount(3);
		gamePuzzleConfigDOMapper.insertSelective(param);
		
		GamePointAllotDO allotRecoed = new GamePointAllotDO();
		allotRecoed.setAttendCount(5);
		allotRecoed.setStatus(StatusEnum.VALID.getValue());
		allotRecoed.setGameType(GameTypeEnum.MIND.getVal());
		allotRecoed.setRankPoint("['0.48','0.28','0.1','0.05','0']");
		allotRecoed.setRankStar("['1','1','0','-1','-1']");
		gamePointAllotDOMapper.insertSelective(allotRecoed);
		
		GamePuzzleDifficultyDO difficulty = new GamePuzzleDifficultyDO();
		difficulty.setChallengeTime(60);
		difficulty.setCoefficient(3);
		difficulty.setGmtCreate(new Date());
		difficulty.setGmtModified(new Date());
		difficulty.setType(GameHardLevelEnum.SIMPLE.getVal());
		difficulty.setSecScore("[{'level':1,'point':90,'second':1},{'level':2,'point':80,'second':20},{'level':2,'point':70,'second':30},{'level':3,'point':60,'second':40}]");
		gamePuzzleDifficultyDOMapper.insertSelective(difficulty);
		
		gamePuzzleConfigService.setEnable(GameConfigStatusEnum.DISABLE);
		GamePuzzleConfigDOExample example = new GamePuzzleConfigDOExample();
		example.createCriteria().andStatusEqualTo(GameConfigStatusEnum.DISABLE.getVal());
		List<GamePuzzleConfigDO> list = gamePuzzleConfigDOMapper.selectByExample(example);
		Assert.assertEquals(1,list.size());
		
		
	}
	
	
	@Transactional
    @Test
    @Rollback
    public void updateGameMindConfig(){
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
		record.setPicCount(3);
		record.setStatus(GameConfigStatusEnum.ENABLE.getVal());
		gamePuzzleConfigDOMapper.insertSelective(record);
		
		GamePointAllotDO allotRecoed = new GamePointAllotDO();
		allotRecoed.setAttendCount(5);
		allotRecoed.setStatus(StatusEnum.VALID.getValue());
		allotRecoed.setGameType(GameTypeEnum.MIND.getVal());
		allotRecoed.setRankPoint("['0.48','0.28','0.1','0.05','0']");
		allotRecoed.setRankStar("['1','1','0','-1','-1']");
		Integer id =gamePointAllotDOMapper.insertSelective(allotRecoed);
		
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
		param.setAllotList(allotList);
		gamePuzzleConfigService.updateGamePuzzleConfig(Long.parseLong(id.toString()), param);
		
		List<GamePuzzleConfigDO> list = gamePuzzleConfigDOMapper.selectByExample(null);
		Assert.assertEquals(1,list.size());
       
    }
	
	
	@Transactional
    @Test
    @Rollback
    public void getGameMindConfigById(){
		GamePuzzleConfigDO record = new GamePuzzleConfigDO();
		record.setAttendMaxPoint(2000);
		record.setAttendPoint(8);
		record.setAwardPoint(8);
		record.setAwardStar(1);
		record.setCountDown(10);
		record.setDeductStar(1);
		record.setFreeCount(1);
		record.setPicCount(3);
		record.setRoomMaxNum(8);
		record.setShareAttendCount(1);
		record.setSecScore("[{'level':1,'point':90,'second':1},{'level':2,'point':80,'second':20},{'level':2,'point':70,'second':30},{'level':3,'point':60,'second':40},{'level':3,'point':20,'second':60}]");
		record.setStatus(GameConfigStatusEnum.ENABLE.getVal());
		gamePuzzleConfigDOMapper.insertSelective(record);
		
		GamePointAllotDO allotRecoed = new GamePointAllotDO();
		allotRecoed.setAttendCount(5);
		allotRecoed.setStatus(StatusEnum.VALID.getValue());
		allotRecoed.setGameType(GameTypeEnum.MIND.getVal());
		allotRecoed.setRankPoint("['0.48','0.28','0.1','0.05','0']");
		allotRecoed.setRankStar("['1','1','0','-1','-1']");
		Integer id =gamePointAllotDOMapper.insertSelective(allotRecoed);
		
		
		GamePuzzleConfigBO gamePuzzleConfigBO  =gamePuzzleConfigService.getGamePuzzleConfig();
		
		Assert.assertNotNull(gamePuzzleConfigBO);
       
    }
	
	
	@Transactional
    @Test
    @Rollback
    public void rewardPointAndStar(){
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
		
		
		GamePuzzleResultBO gamePuzzleResultBO  =gamePuzzleConfigService.rewardPointAndStar(param);
		
		Assert.assertNotNull(gamePuzzleResultBO);
       
    }
	
	@Transactional
    @Test
    @Rollback
    public void saveRandomPuzzleGameAttendInfo(){
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
		
		GameAttendSaveReturnBO gameAttendSaveReturnBO  =gamePuzzleConfigService.saveRandomPuzzleGameAttendInfo(param);
		
		Assert.assertNotNull(gameAttendSaveReturnBO);
       
    }
	

}

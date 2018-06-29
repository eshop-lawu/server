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

import com.gexin.fastjson.JSON;
import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.constants.GamePuzzlePicStatusEnum;
import com.lawu.eshop.game.constants.GamePuzzleTypeEnum;
import com.lawu.eshop.game.constants.GamePuzzleUserPicStatusEnum;
import com.lawu.eshop.game.param.GameAccountParam;
import com.lawu.eshop.game.param.GamePuzzleCheckDeductionPointParam;
import com.lawu.eshop.game.param.GamePuzzleParam;
import com.lawu.eshop.game.param.GamePuzzleRewardPointAndStarParam;
import com.lawu.eshop.game.param.GamePuzzleStandaloneParam;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.bo.CheckPointStatusBo;
import com.lawu.eshop.game.srv.bo.GameAttendSaveReturnBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleAttendRecordBO;
import com.lawu.eshop.game.srv.domain.GamePuzzleDifficultyDO;
import com.lawu.eshop.game.srv.mapper.GamePuzzleDifficultyDOMapper;
import com.lawu.eshop.game.srv.service.GameAccountService;
import com.lawu.eshop.game.srv.service.GameConfigCacheService;
import com.lawu.eshop.game.srv.service.GamePuzzleAttendRecordService;
import com.lawu.eshop.game.srv.service.GamePuzzlePicService;

/**
 * @author lihj
 * @date 2018/3/20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GamePuzzleAttendRecordServiceImplTest {
    @Autowired
    private GamePuzzlePicService gamePuzzlePicService;
    @Autowired
    private GameAccountService gameAccountService;
    @Autowired
    private GamePuzzleDifficultyDOMapper gamePuzzleDifficultyDOMapper;
    @Autowired
    private GameConfigCacheService gameConfigCacheService;
    @Autowired
    private GamePuzzleAttendRecordService gamePuzzleAttendRecordService;



    @Transactional
    @Test
    @Rollback
    public void batchSavePuzzleAttendRecord(){
        List<GamePuzzleAttendRecordBO> attendBO =new ArrayList<>();
        GamePuzzleAttendRecordBO bo =initGamePuzzleAttendRecordBO("M1","PU1",GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
        attendBO.add(bo);
        gamePuzzleAttendRecordService.batchSavePuzzleAttendRecord(attendBO);
    }

    @Transactional
    @Test
    @Rollback
    public void savePuzzleAttendRecord(){
        GamePuzzleAttendRecordBO bo =initGamePuzzleAttendRecordBO("M1","PU1",GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
        Long id = gamePuzzleAttendRecordService.savePuzzleAttendRecord(bo);
        Assert.assertNotNull(id);
    }

    @Transactional
    @Test
    @Rollback
    public void getPuzzleAttendRecordById(){
    	GamePuzzleAttendRecordBO bo =initGamePuzzleAttendRecordBO("M1","PU1",GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
    	Long id = gamePuzzleAttendRecordService.savePuzzleAttendRecord(bo);
    	GamePuzzleAttendRecordBO attendRecord = gamePuzzleAttendRecordService.getPuzzleAttendRecordById(id);
    	Assert.assertEquals(id,attendRecord.getId());
    	
    	
    }
    
    @Transactional
    @Test
    @Rollback
    public void updateGamePuzzleAttendRecordStatus(){
    	GamePuzzleAttendRecordBO bo =initGamePuzzleAttendRecordBO("M1","PU1",GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
    	Long id = gamePuzzleAttendRecordService.savePuzzleAttendRecord(bo);
    	gamePuzzleAttendRecordService.updateGamePuzzleAttendRecordStatus(id, GameAttendRecordStatusEnum.ATTENDSUCCESS);
    	GamePuzzleAttendRecordBO attendRecord = gamePuzzleAttendRecordService.getPuzzleAttendRecordById(id);
    	Assert.assertEquals(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal(),attendRecord.getStatus());
    }
    
    @Transactional
    @Test
    @Rollback
    public void updatePuzzleAttendRecord(){
    	GamePuzzleAttendRecordBO bo =initGamePuzzleAttendRecordBO("M1","PU1",GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
    	Long id = gamePuzzleAttendRecordService.savePuzzleAttendRecord(bo);
    	GamePuzzleRewardPointAndStarParam param =new GamePuzzleRewardPointAndStarParam();
    	param.setPoint(100);
    	param.setStar(101);
    	param.setGameUseTime(102);
    	param.setGameRank(103);
    	param.setSocre(104);
    	param.setUserNum("M1");
    	param.setAttendNum("AM2");
    	GamePuzzleAttendRecordBO attend = gamePuzzleAttendRecordService.updatePuzzleAttendRecord(param);
    	Assert.assertEquals(new BigDecimal(100), attend.getRewardPoint());
    	Assert.assertEquals(Integer.valueOf(101), attend.getRewardStar());
    	Assert.assertEquals(Integer.valueOf(102), attend.getGameUseTime());
    	Assert.assertEquals(Integer.valueOf(103), attend.getGameRank());
    	Assert.assertEquals(Integer.valueOf(104), attend.getGameScore());
    }
    
    
    @Transactional
    @Test
    @Rollback
    public void checkDeductionPointSucc(){
    	GamePuzzleAttendRecordBO bo =initGamePuzzleAttendRecordBO("M1","PU1",GameAttendRecordStatusEnum.INITSTATUS.getVal());
    	GamePuzzleAttendRecordBO bo1 =initGamePuzzleAttendRecordBO("M2","PU1",GameAttendRecordStatusEnum.ATTENDFAIL.getVal());
    	Long id = gamePuzzleAttendRecordService.savePuzzleAttendRecord(bo);
    	Long id1 = gamePuzzleAttendRecordService.savePuzzleAttendRecord(bo1);
    	GamePuzzleCheckDeductionPointParam param =new GamePuzzleCheckDeductionPointParam();
    	param.setAttendNum("PU1");
    	CheckPointStatusBo point = gamePuzzleAttendRecordService.checkDeductionPointSucc(param);
    	System.out.println(JSON.toJSON(point));
    }
    
    @Transactional
    @Test
    @Rollback
    public void loadingStandalone(){
        //初始化游戏账户
        GameAccountParam accountParam =new GameAccountParam();
        accountParam.setAccount("15012345678");
        accountParam.setType(GameTypeEnum.PUZZLE);
        accountParam.setUserNum("M1");
        gameAccountService.getAccountInfo(accountParam);
        //初始化拼图数据
        GamePuzzleParam puzzParam =new GamePuzzleParam();
        puzzParam.setTypeEnum(GamePuzzleTypeEnum.PUZZLE);
        puzzParam.setUserNum("M1");
        puzzParam.setUserPicStatusEnum(GamePuzzleUserPicStatusEnum.HAVE_USE);
        puzzParam.setPicStatusEnum(GamePuzzlePicStatusEnum.ENABLED);
        puzzParam.setUserNickname("测试001");
        puzzParam.setImgPath("www.baidu.com");
        puzzParam.setIsCommon(true);
        puzzParam.setIsHard(true);
        puzzParam.setIsSimple(true);
        gamePuzzlePicService.saveGamePuzzlePic(puzzParam);

        GamePuzzleParam puzzParam1 =new GamePuzzleParam();
        puzzParam1.setTypeEnum(GamePuzzleTypeEnum.PUZZLE);
        puzzParam1.setUserNum("M1");
        puzzParam1.setUserPicStatusEnum(GamePuzzleUserPicStatusEnum.HAVE_USE);
        puzzParam1.setPicStatusEnum(GamePuzzlePicStatusEnum.ENABLED);
        puzzParam1.setUserNickname("测试001");
        puzzParam1.setImgPath("www.youku.com");
        puzzParam1.setIsCommon(true);
        puzzParam1.setIsHard(true);
        puzzParam1.setIsSimple(true);
        gamePuzzlePicService.saveGamePuzzlePic(puzzParam1);

        GamePuzzleParam puzzParam3 =new GamePuzzleParam();
        puzzParam3.setTypeEnum(GamePuzzleTypeEnum.PUZZLE);
        puzzParam3.setUserNum("M1");
        puzzParam3.setUserPicStatusEnum(GamePuzzleUserPicStatusEnum.HAVE_USE);
        puzzParam3.setPicStatusEnum(GamePuzzlePicStatusEnum.ENABLED);
        puzzParam3.setUserNickname("测试001");
        puzzParam3.setImgPath("www.google.com");
        puzzParam3.setIsCommon(true);
        puzzParam3.setIsHard(true);
        puzzParam3.setIsSimple(true);
        gamePuzzlePicService.saveGamePuzzlePic(puzzParam3);

        //
        GamePuzzleDifficultyDO difficulty = new GamePuzzleDifficultyDO();
        difficulty.setCoefficient(3);
        difficulty.setType(GameHardLevelEnum.SIMPLE.getVal());
        difficulty.setGmtCreate(new Date());
        difficulty.setGmtModified(new Date());
        difficulty.setChallengeTime(30);
        difficulty.setSecScore("[{'level':1,'point':90,'second':1}]");
        gamePuzzleDifficultyDOMapper.insertSelective(difficulty);
        GamePuzzleDifficultyDO difficulty1 = new GamePuzzleDifficultyDO();
        difficulty1.setCoefficient(3);
        difficulty1.setType(GameHardLevelEnum.COMMONLY.getVal());
        difficulty1.setGmtCreate(new Date());
        difficulty1.setGmtModified(new Date());
        difficulty1.setChallengeTime(30);
        difficulty1.setSecScore("[{'level':1,'point':90,'second':1}]");
        gamePuzzleDifficultyDOMapper.insertSelective(difficulty1);
        GamePuzzleDifficultyDO difficulty2 = new GamePuzzleDifficultyDO();
        difficulty2.setCoefficient(3);
        difficulty2.setType(GameHardLevelEnum.DIFFICULTY.getVal());
        difficulty2.setGmtCreate(new Date());
        difficulty2.setGmtModified(new Date());
        difficulty2.setChallengeTime(30);
        difficulty2.setSecScore("[{'level':1,'point':90,'second':1}]");
        gamePuzzleDifficultyDOMapper.insertSelective(difficulty2);
    	GamePuzzleStandaloneParam param =new GamePuzzleStandaloneParam();
    	param.setUserNum("M1");
    	param.setSubStar(8);
    	GameAttendSaveReturnBO attend = gamePuzzleAttendRecordService.loadingStandalone(param);
    	Assert.assertEquals(true,attend.isFlag());
      /*  Result<String> puzzleJson = gameConfigCacheService.getRedisKeyGamePuzzleStartType(GameCacheKeyEnum.PUZZLE_JSON+attend.getAttendNum());
        Result<String> puzzleAnswer = gameConfigCacheService.getRedisKeyGamePuzzleStartType(GameCacheKeyEnum.PUZZLE_ANSWER+attend.getAttendNum()+"M1");
        System.out.println(JSON.toJSON(attend));
        System.out.println(JSON.toJSON(puzzleJson));
        System.out.println(JSON.toJSON(puzzleAnswer));*/
    }
    
    
    private GamePuzzleAttendRecordBO initGamePuzzleAttendRecordBO(String userNum,String attendNum,Byte status) {
        GamePuzzleAttendRecordBO bo =new GamePuzzleAttendRecordBO();
        bo.setUserNum(userNum);
        bo.setAttendNum(attendNum);
        bo.setAttendType(AttendTypeEnum.STANDALONE.getVal());
        bo.setRoomNum("1");
        bo.setAttendCount(1);
        bo.setDifficulty(GameHardLevelEnum.SIMPLE.getVal());
        bo.setAttendPoint(1);
        bo.setAttendStar(1);
        bo.setPuzzlePicId(1L);
        bo.setStatus(status);//GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal()
        bo.setGameUseTime(100);
        bo.setGameScore(100);
        bo.setGameRank(1);
        bo.setRewardPoint(new BigDecimal(2));
        bo.setRewardStar(1);
        bo.setGmtModified(new Date());
        bo.setGmtCreate(new Date());
        return bo;
    }


}

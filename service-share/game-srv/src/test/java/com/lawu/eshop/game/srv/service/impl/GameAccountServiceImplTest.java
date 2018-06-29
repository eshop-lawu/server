package com.lawu.eshop.game.srv.service.impl;

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

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.game.param.GameAccountAllotParam;
import com.lawu.eshop.game.param.GameAccountParam;
import com.lawu.eshop.game.param.GameAccountStarParam;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.domain.GameAccountDO;
import com.lawu.eshop.game.srv.domain.GameAccountDOExample;
import com.lawu.eshop.game.srv.domain.GameDialAccountDO;
import com.lawu.eshop.game.srv.domain.GameDialAccountDOExample;
import com.lawu.eshop.game.srv.domain.GameMindAccountDO;
import com.lawu.eshop.game.srv.domain.GameMindAccountDOExample;
import com.lawu.eshop.game.srv.domain.GamePuzzleAccountDO;
import com.lawu.eshop.game.srv.domain.GamePuzzleAccountDOExample;
import com.lawu.eshop.game.srv.domain.UserStarRecordDO;
import com.lawu.eshop.game.srv.mapper.GameAccountDOMapper;
import com.lawu.eshop.game.srv.mapper.GameDialAccountDOMapper;
import com.lawu.eshop.game.srv.mapper.GameMindAccountDOMapper;
import com.lawu.eshop.game.srv.mapper.GamePuzzleAccountDOMapper;
import com.lawu.eshop.game.srv.mapper.UserStarRecordDOMapper;
import com.lawu.eshop.game.srv.service.GameAccountService;
import com.lawu.utils.DateUtil;

/**
 * 游戏账户单元测试
 * 
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月21日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GameAccountServiceImplTest {
	
	@Autowired
	private GameAccountService gameAccountService;
	
	@Autowired
	private GameAccountDOMapper gameAccountDOMapper;
	
	@Autowired
	private GameMindAccountDOMapper gameMindAccountDOMapper;
	
	@Autowired
	private GamePuzzleAccountDOMapper gamePuzzleAccountDOMapper;
	
	@Autowired
	private GameDialAccountDOMapper gameDialAccountDOMapper;
	
	@Autowired
	private UserStarRecordDOMapper userStarRecordDOMapper;
	
	@Transactional
    @Test
    @Rollback
    public void getAccountInfo(){
		GameAccountParam param = new GameAccountParam();
		param.setAccount("18512345678");
		param.setType(GameTypeEnum.MIND);
		param.setUserNum("M945115889651417105");
		gameAccountService.getAccountInfo(param);
		GameAccountDOExample example = new GameAccountDOExample();
		example.createCriteria().andUserNumEqualTo(param.getUserNum());
		List<GameAccountDO> list = gameAccountDOMapper.selectByExample(example);
		
		GameMindAccountDOExample mindAccountDOExample = new GameMindAccountDOExample();
		mindAccountDOExample.createCriteria().andUserNumEqualTo(param.getUserNum());
		List<GameMindAccountDO> mindList = gameMindAccountDOMapper.selectByExample(mindAccountDOExample);
		Assert.assertEquals(1,list.size());
		Assert.assertEquals(1,mindList.size());
    }
	
	@Transactional
    @Test
    @Rollback
    public void getPuzzleAccountInfo(){
		GameAccountParam param = new GameAccountParam();
		param.setAccount("18512345678");
		param.setType(GameTypeEnum.PUZZLE);
		param.setUserNum("M945115889651417105");
		gameAccountService.getAccountInfo(param);
		GameAccountDOExample example = new GameAccountDOExample();
		example.createCriteria().andUserNumEqualTo(param.getUserNum());
		List<GameAccountDO> list = gameAccountDOMapper.selectByExample(example);
		
		GamePuzzleAccountDOExample puzzleAccountDOExample = new GamePuzzleAccountDOExample();
		puzzleAccountDOExample.createCriteria().andUserNumEqualTo(param.getUserNum());
		List<GamePuzzleAccountDO> puzzleList = gamePuzzleAccountDOMapper.selectByExample(puzzleAccountDOExample);
		Assert.assertEquals(1,list.size());
		Assert.assertEquals(1,puzzleList.size());
    }
	
	@Transactional
    @Test
    @Rollback
    public void getDialAccountInfo(){
		GameAccountParam param = new GameAccountParam();
		param.setAccount("18512345678");
		param.setType(GameTypeEnum.DIAL);
		param.setUserNum("M945115889651417105");
		gameAccountService.getAccountInfo(param);
		GameAccountDOExample example = new GameAccountDOExample();
		example.createCriteria().andUserNumEqualTo(param.getUserNum());
		List<GameAccountDO> list = gameAccountDOMapper.selectByExample(example);
		
		GameDialAccountDOExample dialAccountDOExample = new GameDialAccountDOExample();
		dialAccountDOExample.createCriteria().andUserNumEqualTo(param.getUserNum());
		List<GameDialAccountDO> dialList = gameDialAccountDOMapper.selectByExample(dialAccountDOExample);
		Assert.assertEquals(1,list.size());
		Assert.assertEquals(1,dialList.size());
    }
	
	@Transactional
    @Test
    @Rollback
    public void shareAddAttendCount(){
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
		
		gameAccountService.shareAddAttendCount("M945115889651417105",GameTypeEnum.MIND);
		GameMindAccountDOExample mindAccountDOExample = new GameMindAccountDOExample();
		mindAccountDOExample.createCriteria().andUserNumEqualTo("M945115889651417105").andShareAttendCountGreaterThan(0);
		List<GameMindAccountDO> mindList = gameMindAccountDOMapper.selectByExample(mindAccountDOExample);
		Assert.assertEquals(1,mindList.size());
    }
	
	@Transactional
    @Test
    @Rollback
    public void dealStar(){
		GameAccountDO record = new GameAccountDO();
		record.setAccount("18512345678");
		record.setStarCount(1);
		record.setUserNum("M945115889651417105");
		record.setGmtCreate(new Date());
		record.setGmtModified(new Date());
		gameAccountDOMapper.insertSelective(record);
		
		GameAccountDO record2 = new GameAccountDO();
		record2.setAccount("18512345679");
		record2.setStarCount(1);
		record2.setUserNum("M945115889651417104");
		record2.setGmtCreate(new Date());
		record2.setGmtModified(new Date());
		gameAccountDOMapper.insertSelective(record2);
		
		GameMindAccountDO mindRecord = new GameMindAccountDO();
		mindRecord.setFreeCount(1);
		mindRecord.setIsGetFree(false);
		mindRecord.setUserNum("M945115889651417105");
		mindRecord.setGmtCreate(new Date());
		mindRecord.setGmtModified(new Date());
		gameMindAccountDOMapper.insertSelective(mindRecord);
		
		GameMindAccountDO mindRecord2 = new GameMindAccountDO();
		mindRecord2.setFreeCount(1);
		mindRecord2.setIsGetFree(false);
		mindRecord2.setUserNum("M945115889651417104");
		mindRecord2.setGmtCreate(new Date());
		mindRecord2.setGmtModified(new Date());
		gameMindAccountDOMapper.insertSelective(mindRecord2);
		
		 UserStarRecordDO starRecordDO = new UserStarRecordDO();
         starRecordDO.setUserNum("M945115889651417105");
         starRecordDO.setAccount("18512345678");
         starRecordDO.setGmtCreate(new Date());
         starRecordDO.setGmtReport(DateUtil.getFirstDayOfMonth(new Date()));
         starRecordDO.setGmtModified(new Date());
         starRecordDO.setMonthStarCount(0);
         userStarRecordDOMapper.insertSelective(starRecordDO);
		
		GameAccountStarParam param = new  GameAccountStarParam();
		param.setAttendNum("PU975905687341105167");
		param.setGameType(GameTypeEnum.MIND);
		param.setRecordEnum(GameAttendRecordStatusEnum.ATTENDSUCCESS);
		
		List<GameAccountAllotParam> list = new ArrayList<>();
		GameAccountAllotParam allot = new GameAccountAllotParam();
		allot.setStar(1);
		allot.setUserNum("M945115889651417105");
		list.add(allot);
		
		GameAccountAllotParam allot2 = new GameAccountAllotParam();
		allot2.setStar(1);
		allot2.setUserNum("M945115889651417104");
		list.add(allot2);
		param.setList(list);
		
		gameAccountService.dealStar(param);
		
		GameAccountDOExample example = new GameAccountDOExample();
		example.createCriteria().andUserNumEqualTo("M945115889651417105").andStarCountEqualTo(0);
		List<GameAccountDO> gamelist = gameAccountDOMapper.selectByExample(example);
		Assert.assertEquals(1,gamelist.size());
    }
	
	
	@Transactional
    @Test
    @Rollback
    public void accountStarReset(){
		GameAccountDO record = new GameAccountDO();
		record.setAccount("18512345678");
		record.setStarCount(1);
		record.setUserNum("M945115889651417105");
		record.setGmtCreate(new Date());
		record.setGmtModified(new Date());
		gameAccountDOMapper.insertSelective(record);
		
		gameAccountService.accountStarReset();
		
		GameAccountDOExample example = new GameAccountDOExample();
		example.createCriteria().andUserNumEqualTo("M945115889651417105").andStarCountEqualTo(0);
		List<GameAccountDO> list = gameAccountDOMapper.selectByExample(example);
		
		Assert.assertEquals(1,list.size());
    }
	
	@Transactional
    @Test
    @Rollback
    public void getShareCount(){
		GameAccountDO record = new GameAccountDO();
		record.setAccount("18512345678");
		record.setStarCount(1);
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
		mindRecord.setShareAttendCount(1);
		gameMindAccountDOMapper.insertSelective(mindRecord);
		Boolean flag = gameAccountService.getShareCount("M945115889651417105",GameTypeEnum.MIND);
		
		Assert.assertTrue(flag);
    }

	@Transactional
    @Test
    @Rollback
    public void reduceFreeCount(){
		GameAccountDO record = new GameAccountDO();
		record.setAccount("18512345678");
		record.setStarCount(1);
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
		mindRecord.setShareAttendCount(1);
		gameMindAccountDOMapper.insertSelective(mindRecord);
		gameAccountService.reduceFreeCount("M945115889651417105",GameTypeEnum.MIND);
		
		GameMindAccountDOExample mindAccountDOExample = new GameMindAccountDOExample();
		mindAccountDOExample.createCriteria().andUserNumEqualTo("M945115889651417105").andFreeCountEqualTo(0);
		List<GameMindAccountDO> mindList = gameMindAccountDOMapper.selectByExample(mindAccountDOExample);
		Assert.assertEquals(1,mindList.size());
    }

	@Transactional
    @Test
    @Rollback
    public void accountFreeCountReset(){
		GameAccountDO record = new GameAccountDO();
		record.setAccount("18512345678");
		record.setStarCount(1);
		record.setUserNum("M945115889651417105");
		record.setGmtCreate(new Date());
		record.setGmtModified(new Date());
		gameAccountDOMapper.insertSelective(record);
		
		GameMindAccountDO mindRecord = new GameMindAccountDO();
		mindRecord.setFreeCount(0);
		mindRecord.setIsGetFree(false);
		mindRecord.setUserNum("M945115889651417105");
		mindRecord.setGmtCreate(new Date());
		mindRecord.setGmtModified(new Date());
		mindRecord.setShareAttendCount(1);
		gameMindAccountDOMapper.insertSelective(mindRecord);
		gameAccountService.accountFreeCountReset();
		
		GameMindAccountDOExample mindAccountDOExample = new GameMindAccountDOExample();
		mindAccountDOExample.createCriteria().andUserNumEqualTo("M945115889651417105").andFreeCountEqualTo(1);
		List<GameMindAccountDO> mindList = gameMindAccountDOMapper.selectByExample(mindAccountDOExample);
		Assert.assertEquals(1,mindList.size());
    }

}

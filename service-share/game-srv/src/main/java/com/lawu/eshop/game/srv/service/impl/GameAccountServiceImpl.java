package com.lawu.eshop.game.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.cache.dto.GamePuzzleConfigCacheDTO;
import com.lawu.eshop.game.constants.GameDialStatusEnum;
import com.lawu.eshop.game.constants.StarRecordStatusEnum;
import com.lawu.eshop.game.param.GameAccountAllotParam;
import com.lawu.eshop.game.param.GameAccountParam;
import com.lawu.eshop.game.param.GameAccountStarParam;
import com.lawu.eshop.game.param.UserStarRecordParam;
import com.lawu.eshop.game.srv.bo.GameAccountBO;
import com.lawu.eshop.game.srv.domain.GameAccountDO;
import com.lawu.eshop.game.srv.domain.GameAccountDOExample;
import com.lawu.eshop.game.srv.domain.GameDialAccountDO;
import com.lawu.eshop.game.srv.domain.GameDialAccountDOExample;
import com.lawu.eshop.game.srv.domain.GameDialDO;
import com.lawu.eshop.game.srv.domain.GameDialDOExample;
import com.lawu.eshop.game.srv.domain.GameMindAccountDO;
import com.lawu.eshop.game.srv.domain.GameMindAccountDOExample;
import com.lawu.eshop.game.srv.domain.GameMindAttendRecordDO;
import com.lawu.eshop.game.srv.domain.GameMindAttendRecordDOExample;
import com.lawu.eshop.game.srv.domain.GamePuzzleAccountDO;
import com.lawu.eshop.game.srv.domain.GamePuzzleAccountDOExample;
import com.lawu.eshop.game.srv.domain.GamePuzzleAttendRecordDO;
import com.lawu.eshop.game.srv.domain.GamePuzzleAttendRecordDOExample;
import com.lawu.eshop.game.srv.domain.extend.DeductStarDOView;
import com.lawu.eshop.game.srv.domain.extend.FreeCountDOView;
import com.lawu.eshop.game.srv.mapper.GameAccountDOMapper;
import com.lawu.eshop.game.srv.mapper.GameDialAccountDOMapper;
import com.lawu.eshop.game.srv.mapper.GameDialDOMapper;
import com.lawu.eshop.game.srv.mapper.GameMindAccountDOMapper;
import com.lawu.eshop.game.srv.mapper.GameMindAttendRecordDOMapper;
import com.lawu.eshop.game.srv.mapper.GamePuzzleAccountDOMapper;
import com.lawu.eshop.game.srv.mapper.GamePuzzleAttendRecordDOMapper;
import com.lawu.eshop.game.srv.mapper.extend.GameAccountDOMapperExtend;
import com.lawu.eshop.game.srv.service.GameAccountService;
import com.lawu.eshop.game.srv.service.GameConfigCacheService;
import com.lawu.eshop.game.srv.service.UserStarRecordService;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
@Service
public class GameAccountServiceImpl implements GameAccountService {
	
	@Autowired
	private GameAccountDOMapper gameAccountDOMapper;
	
	@Autowired
	private GameMindAccountDOMapper gameMindAccountDOMapper;
	
	@Autowired
	private GamePuzzleAccountDOMapper gamePuzzleAccountDOMapper;
	
	@Autowired
	private GameAccountDOMapperExtend gameAccountDOMapperExtend;
	
	
	@Autowired
	private GameMindAttendRecordDOMapper gameMindAttendRecordDOMapper;
	
	@Autowired
	private GamePuzzleAttendRecordDOMapper gamePuzzleAttendRecordDOMapper;
	
	@Autowired
	private UserStarRecordService userStarRecordService;

	@Autowired
	private GameConfigCacheService gameConfigCacheService;
	
	@Autowired
	private GameDialDOMapper gameDialDOMapper;
	
	@Autowired
	private GameDialAccountDOMapper  gameDialAccountDOMapper;
	

	@Override
	public GameAccountBO getAccountInfo(GameAccountParam param) {
		GameAccountDOExample example = new GameAccountDOExample();
		example.createCriteria().andUserNumEqualTo(param.getUserNum());
		long count = gameAccountDOMapper.countByExample(example);
		if (count == 0) {
			GameAccountDO record = new GameAccountDO();
			record.setAccount(param.getAccount());
			record.setStarCount(0);
			record.setUserNum(param.getUserNum());
			record.setGmtCreate(new Date());
			record.setGmtModified(new Date());
			gameAccountDOMapper.insertSelective(record);
		}
		List<GameAccountDO> gameAccountList = gameAccountDOMapper.selectByExample(example);
		GameAccountBO account = new GameAccountBO();
		account.setStarCount(gameAccountList.get(0).getStarCount());
		if(param.getType() == GameTypeEnum.MIND){
			GameMindAccountDOExample mindExample = new GameMindAccountDOExample();
			mindExample.createCriteria().andUserNumEqualTo(param.getUserNum());
			long mindCount = gameMindAccountDOMapper.countByExample(mindExample);
			Result<GameMindConfigDTO> config = gameConfigCacheService.getGameMindConfig();
			GameMindConfigDTO configDTO = config.getModel();
			if (configDTO == null) return account;
			if(mindCount == 0) {
				GameMindAccountDO mindRecord = new GameMindAccountDO();
				mindRecord.setFreeCount(configDTO.getFreeCount());
				mindRecord.setIsGetFree(false);
				mindRecord.setUserNum(param.getUserNum());
				mindRecord.setGmtCreate(new Date());
				mindRecord.setGmtModified(new Date());
				gameMindAccountDOMapper.insertSelective(mindRecord);
			}
			List<GameMindAccountDO> list = gameMindAccountDOMapper.selectByExample(mindExample);
			account.setAttendPoint(configDTO.getAttendPoint());
			account.setFreeCount(list.get(0).getFreeCount()+list.get(0).getShareAttendCount());
			account.setAttendStarCount(configDTO.getDeductStar());
		}
		if(param.getType() == GameTypeEnum.PUZZLE){
			GamePuzzleAccountDOExample puzzleExample = new GamePuzzleAccountDOExample();
			puzzleExample.createCriteria().andUserNumEqualTo(param.getUserNum());
			long puzzleCount = gamePuzzleAccountDOMapper.countByExample(puzzleExample);
			Result<GamePuzzleConfigCacheDTO> result = gameConfigCacheService.getGamePuzzleConfig();
			GamePuzzleConfigCacheDTO config = result.getModel();
			if (config == null) return account;
			if(puzzleCount == 0){
				GamePuzzleAccountDO puzzleRecord = new GamePuzzleAccountDO();
				puzzleRecord.setFreeCount(config.getFreeCount());
				puzzleRecord.setIsGetFree(false);
				puzzleRecord.setUserNum(param.getUserNum());
				puzzleRecord.setGmtCreate(new Date());
				puzzleRecord.setGmtModified(new Date());
				gamePuzzleAccountDOMapper.insertSelective(puzzleRecord);
			}
			List<GamePuzzleAccountDO> list = gamePuzzleAccountDOMapper.selectByExample(puzzleExample);
			account.setAttendPoint(config.getAttendPoint());
			account.setFreeCount(list.get(0).getFreeCount()+list.get(0).getShareAttendCount());
			account.setAttendStarCount(config.getDeductStar());
		}
		
		if(param.getType() == GameTypeEnum.DIAL){
			GameDialAccountDOExample dialExample = new GameDialAccountDOExample();
			dialExample.createCriteria().andUserNumEqualTo(param.getUserNum());
			long dialCount = gameDialAccountDOMapper.countByExample(dialExample);
			List<GameDialDO> list = gameDialDOMapper.selectByExample(null);
			if (list.isEmpty()) return account;
			if(dialCount == 0){
				GameDialAccountDO dialDO = new GameDialAccountDO();
				dialDO.setFreeCount(list.get(0).getFreeCount());
				dialDO.setIsGetFree(false);
				dialDO.setUserNum(param.getUserNum());
				dialDO.setGmtCreate(new Date());
				dialDO.setGmtModified(new Date());
				gameDialAccountDOMapper.insertSelective(dialDO);
			}
			List<GameDialAccountDO> dialList = gameDialAccountDOMapper.selectByExample(dialExample);
			account.setAttendPoint(list.get(0).getPoint());
			account.setFreeCount(dialList.get(0).getFreeCount()+dialList.get(0).getShareAttendCount());
			account.setAttendStarCount(0);
		}
		return account;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int shareAddAttendCount(String userNum, GameTypeEnum typeEnum) {
		GameAccountDOExample example = new GameAccountDOExample();
		example.createCriteria().andUserNumEqualTo(userNum);
		List<GameAccountDO> gameAccountList = gameAccountDOMapper.selectByExample(example);
		if(gameAccountList.isEmpty()) return 0;
		if(typeEnum == GameTypeEnum.MIND){
			GameMindAccountDOExample mindExample = new GameMindAccountDOExample();
			mindExample.createCriteria().andUserNumEqualTo(userNum).andIsGetFreeEqualTo(true);
			int count = (int) gameMindAccountDOMapper.countByExample(mindExample);
			if(count > 0) return 0;
			Result<GameMindConfigDTO> mindConfig = gameConfigCacheService.getGameMindConfig();
			FreeCountDOView view = new FreeCountDOView();
			view.setShareCount(mindConfig.getModel().getShareAttendCount());
			view.setUserNum(userNum);
			gameAccountDOMapperExtend.shareMindAttendCount(view);
			return mindConfig.getModel().getShareAttendCount();
			
		}
		if(typeEnum == GameTypeEnum.PUZZLE){
			GamePuzzleAccountDOExample puzzleExample = new GamePuzzleAccountDOExample();
			puzzleExample.createCriteria().andUserNumEqualTo(userNum).andIsGetFreeEqualTo(true);
			int count = (int) gamePuzzleAccountDOMapper.countByExample(puzzleExample);
			if(count > 0) return 0;
			Result<GamePuzzleConfigCacheDTO> puzzleConfig =gameConfigCacheService.getGamePuzzleConfig();
			FreeCountDOView view = new FreeCountDOView();
			view.setShareCount(puzzleConfig.getModel().getShareAttendCount());
			view.setUserNum(userNum);
			gameAccountDOMapperExtend.sharePuzzleAttendCount(view);
			return puzzleConfig.getModel().getShareAttendCount();
		}
		if(typeEnum == GameTypeEnum.DIAL){
			GameDialAccountDOExample dialExample = new GameDialAccountDOExample();
			dialExample.createCriteria().andUserNumEqualTo(userNum).andIsGetFreeEqualTo(true);
			int count = (int) gameDialAccountDOMapper.countByExample(dialExample);
			if(count > 0) return 0;
			List<GameDialDO> dials =gameDialDOMapper.selectByExample(null);
			FreeCountDOView view = new FreeCountDOView();
			view.setShareCount(dials.get(0).getShareAttendCount());
			view.setUserNum(userNum);
			gameAccountDOMapperExtend.shareDialAttendCount(view);
			return dials.get(0).getShareAttendCount();
		}
		return 0;
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void dealStar(GameAccountStarParam param) {
		
		if (param.getRecordEnum() == GameAttendRecordStatusEnum.ATTENDSUCCESS) {
			List<GameAccountAllotParam> list = param.getList();
			for (GameAccountAllotParam gameAccountAllotParam : list) {
				GameAccountDOExample example = new GameAccountDOExample();
				example.createCriteria().andUserNumEqualTo(gameAccountAllotParam.getUserNum());
				List<GameAccountDO> accounts = gameAccountDOMapper.selectByExample(example);
				GameAccountDO gameAccountDO = accounts.get(0);
				// 判断实际游戏账户的星星是否大于要扣除的星星，如果不足则扣除账户实际星星并返回
				int star =0;
				if (gameAccountDO.getStarCount() >= gameAccountAllotParam.getStar()) {
					star=gameAccountAllotParam.getStar();
				} else {
					star=gameAccountDO.getStarCount();
				}
				DeductStarDOView view = new DeductStarDOView();
				view.setStar(star);
				view.setUserNum(gameAccountAllotParam.getUserNum());
				gameAccountDOMapperExtend.deductStar(view);
				
				//修改月统计
				UserStarRecordParam userParam = new UserStarRecordParam();
				userParam.setUserNum(gameAccountAllotParam.getUserNum());
				userParam.setStarCount(gameAccountAllotParam.getStar());
				userParam.setAccount(gameAccountDO.getAccount());
				userParam.setStatus(StarRecordStatusEnum.REDUCE);
				userStarRecordService.addUserStartRecord(userParam);
				
				if (param.getGameType() == GameTypeEnum.MIND) {
					//修改参与表参与星星
					GameMindAttendRecordDOExample recordDOExample = new GameMindAttendRecordDOExample();
					recordDOExample.createCriteria().andAttendNumEqualTo(param.getAttendNum()).andUserNumEqualTo(gameAccountAllotParam.getUserNum());
					GameMindAttendRecordDO record = new GameMindAttendRecordDO();
					record.setAttendStar(star);
					record.setGmtModified(new Date());
					gameMindAttendRecordDOMapper.updateByExampleSelective(record, recordDOExample);
				}else if (param.getGameType() == GameTypeEnum.PUZZLE) { //拼图游戏
					GamePuzzleAttendRecordDOExample recordDOExample = new GamePuzzleAttendRecordDOExample();
					recordDOExample.createCriteria().andAttendNumEqualTo(param.getAttendNum()).andUserNumEqualTo(gameAccountAllotParam.getUserNum());
					GamePuzzleAttendRecordDO record = new GamePuzzleAttendRecordDO();
					record.setAttendStar(star);
					record.setGmtModified(new Date());
					gamePuzzleAttendRecordDOMapper.updateByExampleSelective(record, recordDOExample);
				}
				
			}

		}else if(param.getRecordEnum() == GameAttendRecordStatusEnum.GAMEPLAYSUCCESS){
			List<GameAccountAllotParam> list = param.getList();
			if (param.getGameType() == GameTypeEnum.MIND) {
				for (GameAccountAllotParam gameAccountAllotParam : list) {
					
					GameMindAttendRecordDOExample recordDOExample = new GameMindAttendRecordDOExample();
					recordDOExample.createCriteria().andAttendNumEqualTo(param.getAttendNum()).andUserNumEqualTo(gameAccountAllotParam.getUserNum());
					List<GameMindAttendRecordDO> recordList = gameMindAttendRecordDOMapper.selectByExample(recordDOExample);
					//挑战成功需要加上之前扣除的星星和奖励的星星
					DeductStarDOView view = new DeductStarDOView();
					int rewardStar =gameAccountAllotParam.getStar()+recordList.get(0).getAttendStar();
					view.setStar(rewardStar);
					view.setUserNum(gameAccountAllotParam.getUserNum());
					gameAccountDOMapperExtend.addStar(view);
					
					//修改月统计
					GameAccountDOExample example = new GameAccountDOExample();
					example.createCriteria().andUserNumEqualTo(gameAccountAllotParam.getUserNum());
					List<GameAccountDO> accounts = gameAccountDOMapper.selectByExample(example);
					GameAccountDO gameAccountDO = accounts.get(0);
					UserStarRecordParam userParam = new UserStarRecordParam();
					userParam.setUserNum(gameAccountAllotParam.getUserNum());
					userParam.setStarCount(rewardStar);
					userParam.setAccount(gameAccountDO.getAccount());
					userParam.setStatus(StarRecordStatusEnum.INCREASE);
					userStarRecordService.addUserStartRecord(userParam);
					
					//修改参与表奖励星星
					GameMindAttendRecordDO record = new GameMindAttendRecordDO();
					record.setRewardStar(gameAccountAllotParam.getStar());
					record.setGmtModified(new Date());
					gameMindAttendRecordDOMapper.updateByExampleSelective(record, recordDOExample);
				}
			}else if (param.getGameType() == GameTypeEnum.PUZZLE) { //拼图游戏
				for (GameAccountAllotParam gameAccountAllotParam : list) {

					GamePuzzleAttendRecordDOExample recordDOExample = new GamePuzzleAttendRecordDOExample();
					recordDOExample.createCriteria().andAttendNumEqualTo(param.getAttendNum()).andUserNumEqualTo(gameAccountAllotParam.getUserNum());
					List<GamePuzzleAttendRecordDO> recordList = gamePuzzleAttendRecordDOMapper.selectByExample(recordDOExample);
					// 挑战成功需要加上之前扣除的星星和奖励的星星
					DeductStarDOView view = new DeductStarDOView();
					int rewardStar = gameAccountAllotParam.getStar() + recordList.get(0).getAttendStar();
					view.setStar(rewardStar);
					view.setUserNum(gameAccountAllotParam.getUserNum());
					gameAccountDOMapperExtend.addStar(view);
					
					//修改月统计
					GameAccountDOExample example = new GameAccountDOExample();
					example.createCriteria().andUserNumEqualTo(gameAccountAllotParam.getUserNum());
					List<GameAccountDO> accounts = gameAccountDOMapper.selectByExample(example);
					GameAccountDO gameAccountDO = accounts.get(0);
					UserStarRecordParam userParam = new UserStarRecordParam();
					userParam.setUserNum(gameAccountAllotParam.getUserNum());
					userParam.setStarCount(rewardStar);
					userParam.setAccount(gameAccountDO.getAccount());
					userParam.setStatus(StarRecordStatusEnum.INCREASE);
					userStarRecordService.addUserStartRecord(userParam);

					// 修改参与表奖励星星
					GamePuzzleAttendRecordDO record = new GamePuzzleAttendRecordDO();
					record.setRewardStar(gameAccountAllotParam.getStar());
					record.setGmtModified(new Date());
					gamePuzzleAttendRecordDOMapper.updateByExampleSelective(record, recordDOExample);  
				}
			}else if(param.getGameType() == GameTypeEnum.DIAL){
				
				for (GameAccountAllotParam gameAccountAllotParam : list) {
					
					DeductStarDOView view = new DeductStarDOView();
					view.setStar(gameAccountAllotParam.getStar());
					view.setUserNum(gameAccountAllotParam.getUserNum());
					gameAccountDOMapperExtend.addStar(view);
					
					//修改月统计
					GameAccountDOExample example = new GameAccountDOExample();
					example.createCriteria().andUserNumEqualTo(gameAccountAllotParam.getUserNum());
					List<GameAccountDO> accounts = gameAccountDOMapper.selectByExample(example);
					GameAccountDO gameAccountDO = accounts.get(0);
					UserStarRecordParam userParam = new UserStarRecordParam();
					userParam.setUserNum(gameAccountAllotParam.getUserNum());
					userParam.setStarCount(gameAccountAllotParam.getStar());
					userParam.setAccount(gameAccountDO.getAccount());
					userParam.setStatus(StarRecordStatusEnum.INCREASE);
					userStarRecordService.addUserStartRecord(userParam);
					
				}
				
			}
				

		} else if(param.getRecordEnum() == GameAttendRecordStatusEnum.REFUND) {
			List<GameAccountAllotParam> list = param.getList();
			if (param.getGameType() == GameTypeEnum.MIND) {
				for (GameAccountAllotParam gameAccountAllotParam : list) {
					
					GameMindAttendRecordDOExample recordDOExample = new GameMindAttendRecordDOExample();
					recordDOExample.createCriteria().andAttendNumEqualTo(param.getAttendNum()).andUserNumEqualTo(gameAccountAllotParam.getUserNum());
					List<GameMindAttendRecordDO> recordList = gameMindAttendRecordDOMapper.selectByExample(recordDOExample);
					
					DeductStarDOView view = new DeductStarDOView();
					int rewardStar =recordList.get(0).getAttendStar();
					view.setStar(rewardStar);
					view.setUserNum(gameAccountAllotParam.getUserNum());
					gameAccountDOMapperExtend.addStar(view);
					
					//修改月统计
					GameAccountDOExample example = new GameAccountDOExample();
					example.createCriteria().andUserNumEqualTo(gameAccountAllotParam.getUserNum());
					List<GameAccountDO> accounts = gameAccountDOMapper.selectByExample(example);
					GameAccountDO gameAccountDO = accounts.get(0);
					UserStarRecordParam userParam = new UserStarRecordParam();
					userParam.setUserNum(gameAccountAllotParam.getUserNum());
					userParam.setStarCount(rewardStar);
					userParam.setAccount(gameAccountDO.getAccount());
					userParam.setStatus(StarRecordStatusEnum.INCREASE);
					userStarRecordService.addUserStartRecord(userParam);
				
				}
			}else if (param.getGameType() == GameTypeEnum.PUZZLE) { //拼图游戏
				for (GameAccountAllotParam gameAccountAllotParam : list) {

					GamePuzzleAttendRecordDOExample recordDOExample = new GamePuzzleAttendRecordDOExample();
					recordDOExample.createCriteria().andAttendNumEqualTo(param.getAttendNum()).andUserNumEqualTo(gameAccountAllotParam.getUserNum());
					List<GamePuzzleAttendRecordDO> recordList = gamePuzzleAttendRecordDOMapper.selectByExample(recordDOExample);
					// 挑战成功需要加上之前扣除的星星和奖励的星星
					DeductStarDOView view = new DeductStarDOView();
					int rewardStar = recordList.get(0).getAttendStar();
					view.setStar(rewardStar);
					view.setUserNum(gameAccountAllotParam.getUserNum());
					gameAccountDOMapperExtend.addStar(view);
					
					//修改月统计
					GameAccountDOExample example = new GameAccountDOExample();
					example.createCriteria().andUserNumEqualTo(gameAccountAllotParam.getUserNum());
					List<GameAccountDO> accounts = gameAccountDOMapper.selectByExample(example);
					GameAccountDO gameAccountDO = accounts.get(0);
					UserStarRecordParam userParam = new UserStarRecordParam();
					userParam.setUserNum(gameAccountAllotParam.getUserNum());
					userParam.setStarCount(rewardStar);
					userParam.setAccount(gameAccountDO.getAccount());
					userParam.setStatus(StarRecordStatusEnum.INCREASE);
					userStarRecordService.addUserStartRecord(userParam);

				}
			}
				
			
		}
		
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void accountStarReset() {
		gameAccountDOMapperExtend.accountStarReset();
	}

	@Override
	public Boolean getShareCount(String userNum,GameTypeEnum typeEnum) {
		if(typeEnum == GameTypeEnum.MIND){
			int count = gameAccountDOMapperExtend.getMindFreeCount(userNum);
			return count>0;
		}else if(typeEnum == GameTypeEnum.PUZZLE){
			int count = gameAccountDOMapperExtend.getPuzzleFreeCount(userNum);
			return count>0;
		}else{
			int count = gameAccountDOMapperExtend.getDialFreeCount(userNum);
			return count>0;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void reduceFreeCount(String userNum, GameTypeEnum typeEnum) {
		if(typeEnum == GameTypeEnum.MIND){
			GameMindAccountDOExample example = new GameMindAccountDOExample();
			example.createCriteria().andUserNumEqualTo(userNum);
			List<GameMindAccountDO> list = gameMindAccountDOMapper.selectByExample(example);
			int freeCount = list.get(0).getFreeCount();
			int shareCount = list.get(0).getShareAttendCount();
			if(freeCount ==0 && shareCount ==0) return;
			FreeCountDOView view = new FreeCountDOView();
			view.setFreeCount(freeCount);
			view.setShareCount(shareCount);
			view.setUserNum(userNum);
			gameAccountDOMapperExtend.reduceMindFree(view);
		}else if(typeEnum == GameTypeEnum.PUZZLE){
			GamePuzzleAccountDOExample example= new GamePuzzleAccountDOExample();
			example.createCriteria().andUserNumEqualTo(userNum);
			List<GamePuzzleAccountDO> list = gamePuzzleAccountDOMapper.selectByExample(example);
			int freeCount = list.get(0).getFreeCount();
			int shareCount = list.get(0).getShareAttendCount();
			if(freeCount ==0 && shareCount ==0) return;
			FreeCountDOView view = new FreeCountDOView();
			view.setFreeCount(freeCount);
			view.setShareCount(shareCount);
			view.setUserNum(userNum);
			gameAccountDOMapperExtend.reducePuzzleFree(view);
		}else{
			GameDialAccountDOExample example = new GameDialAccountDOExample();
			example.createCriteria().andUserNumEqualTo(userNum);
			List<GameDialAccountDO> list = gameDialAccountDOMapper.selectByExample(example);
			int freeCount = list.get(0).getFreeCount();
			int shareCount = list.get(0).getShareAttendCount();
			if(freeCount ==0 && shareCount ==0) return;
			FreeCountDOView view = new FreeCountDOView();
			view.setFreeCount(freeCount); 
			view.setShareCount(shareCount);
			view.setUserNum(userNum);
			gameAccountDOMapperExtend.reduceDialFree(view);
		}
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void accountFreeCountReset() {
		//更新头脑账户
		Result<GameMindConfigDTO> mindConfig = gameConfigCacheService.getGameMindConfig();
		if(mindConfig.getModel().getStatusEnum() == GameConfigStatusEnum.ENABLE){
			gameAccountDOMapperExtend.mindAccountFreeCountReset(mindConfig.getModel().getFreeCount());
		}
		
		//更新拼图账户
		Result<GamePuzzleConfigCacheDTO> puzzleConfig =gameConfigCacheService.getGamePuzzleConfig();
		if(puzzleConfig.getModel().getStatusEnum() == GameConfigStatusEnum.ENABLE){
			gameAccountDOMapperExtend.puzzleAccountFreeCountReset(puzzleConfig.getModel().getFreeCount());
		}
		
		//更新转盘账户
		GameDialDOExample example = new GameDialDOExample();
		example.createCriteria().andStatusEqualTo(GameDialStatusEnum.ENABLED.getVal());
		List<GameDialDO> list = gameDialDOMapper.selectByExample(example);
		if(!list.isEmpty()){
			gameAccountDOMapperExtend.dialAccountFreeCountReset(list.get(0).getFreeCount());
		}
		
	}

}

package com.lawu.eshop.game.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.cache.constants.CacheGameTypeEnum;
import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameCommonNumDTO;
import com.lawu.eshop.cache.dto.GameMatchResultDTO;
import com.lawu.eshop.cache.dto.GamePuzzleCacheAnswerDetailInfo;
import com.lawu.eshop.cache.dto.GamePuzzleConfigCacheDTO;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.cache.dto.StartTheGameUserDTO;
import com.lawu.eshop.cache.param.CheckCacheMatchParam;
import com.lawu.eshop.cache.param.GameDifficultyParam;
import com.lawu.eshop.common.constants.GameScoreLevelEnum;
import com.lawu.eshop.common.param.GamePointAllotParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.constants.GameCacheKeyEnum;
import com.lawu.eshop.game.dto.GamePuzzleGetPicDTO;
import com.lawu.eshop.game.param.DifficultyInfoParam;
import com.lawu.eshop.game.param.ExitGameRoomParam;
import com.lawu.eshop.game.param.GameAccountAllotParam;
import com.lawu.eshop.game.param.GameAccountStarParam;
import com.lawu.eshop.game.param.GameAttendSaveParam;
import com.lawu.eshop.game.param.GamePuzzleChallengeParam;
import com.lawu.eshop.game.param.GamePuzzleCheckDeductionPointParam;
import com.lawu.eshop.game.param.GamePuzzleGetPicSaveAttendParam;
import com.lawu.eshop.game.param.GamePuzzleLoadRankingParam;
import com.lawu.eshop.game.param.GamePuzzleRankIndex;
import com.lawu.eshop.game.param.GamePuzzleRewardPointAndStarParam;
import com.lawu.eshop.game.param.GamePuzzleStandaloneParam;
import com.lawu.eshop.game.param.GamePuzzleValidSuccSrvParam;
import com.lawu.eshop.game.param.PuzzleStartParam;
import com.lawu.eshop.game.srv.GameSrvConfig;
import com.lawu.eshop.game.srv.bo.CheckPointStatusBo;
import com.lawu.eshop.game.srv.bo.GameAttendSaveReturnBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleAttendRecordBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleGetPicBO;
import com.lawu.eshop.game.srv.bo.GamePuzzlePointStarBO;
import com.lawu.eshop.game.srv.bo.GamePuzzlePointStarDetail;
import com.lawu.eshop.game.srv.bo.GamePuzzlePointStartByDiffBO;
import com.lawu.eshop.game.srv.bo.GamePuzzlePointStartReturnBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleRankBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleRankReturnBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleResultBO;
import com.lawu.eshop.game.srv.bo.PuzzleCheckIsOverBO;
import com.lawu.eshop.game.srv.bo.StartTheGameBO;
import com.lawu.eshop.game.srv.controller.GetPointAndStarUtil;
import com.lawu.eshop.game.srv.converter.GamePuzzleAttendRecordConverter;
import com.lawu.eshop.game.srv.domain.GamePuzzleAttendRecordDO;
import com.lawu.eshop.game.srv.domain.GamePuzzleAttendRecordDOExample;
import com.lawu.eshop.game.srv.mapper.GamePuzzleAttendRecordDOMapper;
import com.lawu.eshop.game.srv.service.GameAccountService;
import com.lawu.eshop.game.srv.service.GameConfigCacheService;
import com.lawu.eshop.game.srv.service.GamePuzzleAttendRecordService;
import com.lawu.eshop.game.srv.service.GamePuzzleConfigService;
import com.lawu.eshop.game.srv.service.GamePuzzleDifficultyService;
import com.lawu.eshop.game.srv.service.GameRobotAccountService;
import com.lawu.eshop.game.srv.service.GameRoomService;
import com.lawu.eshop.game.srv.service.RandomMatchService;
import com.lawu.eshop.mq.dto.game.reply.PuzzleGameDeductionReply;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.RandomPicUtil;

/**
 * @author lihj <br>
 * @date 2018/3/12
 */
@Service
public class GamePuzzleAttendRecordServiceImpl extends BaseController implements GamePuzzleAttendRecordService {

	@Autowired
	private GamePuzzleAttendRecordDOMapper gamePuzzleAttendRecordDOMapper;

	@Autowired
	private GameConfigCacheService gameConfigCacheService;
	
	@Autowired
	private GamePuzzleConfigService gamePuzzleConfigService;
	
	@Autowired
	private GameAccountService gameAccountService;
	
	@Autowired
	private GameSrvConfig gameSrvConfig;
	
	@Autowired
	private GamePuzzleDifficultyService gamePuzzleDifficultyService;
	
	@Autowired
	private GameRoomService gameRoomService;
	
	@Autowired
	@Qualifier("puzzleGameIncreasePointTransactionMainServiceImpl")
	private TransactionMainService<PuzzleGameDeductionReply> puzzleGameIncreasePointTransactionMainServiceImpl;
	
	@Autowired
	private RandomMatchService randomMatchService;
	@Autowired
	private GameRobotAccountService gameRobotAccountService;

	
	private Logger logger =LoggerFactory.getLogger(GamePuzzleAttendRecordServiceImpl.class);
	@Override
	@Transactional
	public void batchSavePuzzleAttendRecord(List<GamePuzzleAttendRecordBO> attendBO) {
		for (GamePuzzleAttendRecordBO bo : attendBO) {
			GamePuzzleAttendRecordDO game = GamePuzzleAttendRecordConverter.convertGamePuzzleAttendRecordDO(bo);
			gamePuzzleAttendRecordDOMapper.insert(game);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Long savePuzzleAttendRecord(GamePuzzleAttendRecordBO attendBo) {
		GamePuzzleAttendRecordDO game = GamePuzzleAttendRecordConverter.convertGamePuzzleAttendRecordDO(attendBo);
		gamePuzzleAttendRecordDOMapper.insert(game);
		return game.getId();
	}

	@Override
	public GamePuzzleAttendRecordBO getPuzzleAttendRecordById(Long id) {
		GamePuzzleAttendRecordDO game = gamePuzzleAttendRecordDOMapper.selectByPrimaryKey(id);
		return GamePuzzleAttendRecordConverter.convertGamePuzzleAttendRecordDO(game);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateGamePuzzleAttendRecordStatus(Long id, GameAttendRecordStatusEnum attendsuccess) {
		GamePuzzleAttendRecordDO game = gamePuzzleAttendRecordDOMapper.selectByPrimaryKey(id);
		if (GameAttendRecordStatusEnum.INITSTATUS.getVal().equals(game.getStatus())) {
			game.setStatus(attendsuccess.getVal());
			game.setGmtModified(new Date());
			gamePuzzleAttendRecordDOMapper.updateByPrimaryKeySelective(game);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public GamePuzzleAttendRecordBO updatePuzzleAttendRecord(GamePuzzleRewardPointAndStarParam param) {
		GamePuzzleAttendRecordDOExample example = new GamePuzzleAttendRecordDOExample();
		example.createCriteria().andUserNumEqualTo(param.getUserNum()).andAttendNumEqualTo(param.getAttendNum());
		GamePuzzleAttendRecordDO gameDO = gamePuzzleAttendRecordDOMapper.selectByExample(example).get(0);
		gameDO.setRewardPoint(new BigDecimal(param.getPoint()));
		gameDO.setRewardStar(param.getStar());
		gameDO.setGameUseTime(param.getGameUseTime());
		gameDO.setGameRank(param.getGameRank());
		gameDO.setGameScore(param.getSocre());
		gameDO.setGmtModified(new Date());
		gamePuzzleAttendRecordDOMapper.updateByPrimaryKeySelective(gameDO);
		GamePuzzleAttendRecordBO bo = GamePuzzleAttendRecordConverter.convertGamePuzzleAttendRecordDO(gameDO);
		return bo;
	}

	/**
	 * 单机游戏loading
	 *
	 * @param param
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public GameAttendSaveReturnBO loadingStandalone(GamePuzzleStandaloneParam param) {
		GameAttendSaveReturnBO returnResult = new GameAttendSaveReturnBO();
		returnResult.setFlag(false);
		Result<GamePuzzleConfigCacheDTO> result = gameConfigCacheService.getGamePuzzleConfig();
		GamePuzzleConfigCacheDTO dto = result.getModel();
		GameAttendSaveParam saveParam = new GameAttendSaveParam();
		saveParam.setUserNum(param.getUserNum());
		saveParam.setAttendType(AttendTypeEnum.STANDALONE);
		List<String> list = new ArrayList<String>();
		list.add(param.getUserNum());
		saveParam.setUserNums(list);
		saveParam.setSubStar(dto.getDeductStar());
		saveParam.setPicCount(dto.getPicCount());
		saveParam.setAttendPoint(0);
		saveParam.setFree(true);
		saveParam.setAttendStar(dto.getDeductStar());
		GameAttendSaveReturnBO gameSaveBO = gamePuzzleConfigService.saveRandomPuzzleGameAttendInfo(saveParam);// 扣除积分
		if (!gameSaveBO.isFlag()) {
			return returnResult;
		}
		GamePuzzleGetPicSaveAttendParam gameParam = new GamePuzzleGetPicSaveAttendParam();
		gameParam.setAttendType(AttendTypeEnum.STANDALONE);
		gameParam.setPicCount(dto.getPicCount());
		List<GamePuzzleGetPicBO> listPic = gamePuzzleConfigService.getPicByHardLevel(gameParam);
		for(int i=0;i<listPic.size();i++){
			listPic.get(i).setSingleMaxScore(dto.getSuccessScore());
		}
		gameConfigCacheService.setRedisKeyGamePuzzleStartType(GameCacheKeyEnum.PUZZLE_JSON + gameSaveBO.getAttendNum(),
				JSON.toJSONString(listPic));
		GamePuzzleCacheAnswerDetailInfo listSucKey = initGameSuccPicBO(listPic, param.getUserNum(), gameSaveBO.getAttendNum());
		gameConfigCacheService.setRedisKeyGamePuzzleStartType(
				GameCacheKeyEnum.PUZZLE_ANSWER + gameSaveBO.getAttendNum() + param.getUserNum(), JSON.toJSONString(listSucKey));
		returnResult.setFlag(true);
		returnResult.setAttendNum(gameSaveBO.getAttendNum());
		return returnResult;
	}

	/**
	 * 随机PK loading
	 *
	 * @param param
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public GameAttendSaveReturnBO loadingChallenge(GamePuzzleChallengeParam param) {
		GameAttendSaveReturnBO returnBO = new GameAttendSaveReturnBO();
		returnBO.setFlag(false);
		CheckCacheMatchParam checkParam = new CheckCacheMatchParam();
		checkParam.setType(CacheGameTypeEnum.PUZZLE);
		checkParam.setKey(param.getUserNum());
		GameMatchResultDTO gameMatchResult = randomMatchService.checkMatch(checkParam);
		if (gameMatchResult.getCommonInfo() == null) {
			returnBO.setResultCode(ResultCode.GAME_MATCH_LOADING);
			return returnBO;
		}
		returnBO.setFlag(true);
		returnBO.setAttendNum(gameMatchResult.getAttendNum());
		List<GameCommonNumDTO> listGame = gameMatchResult.getCommonInfo();
		for (GameCommonNumDTO game : listGame) {
			if (param.getUserNum().equals(game.getUserNum())) {
				returnBO.setRoomMaster(game.isRoomMaster());
			}
		}
		returnBO.setResultCode(ResultCode.SUCCESS);
		return returnBO;
	}

	@Override
	public GameAttendSaveReturnBO loadingRoomMasterRequest(GamePuzzleChallengeParam param) {
		GameAttendSaveReturnBO returnBO = new GameAttendSaveReturnBO();
		returnBO.setFlag(false);
		CheckCacheMatchParam checkParam = new CheckCacheMatchParam();
		checkParam.setType(CacheGameTypeEnum.PUZZLE);
		checkParam.setKey(param.getUserNum());
		GameMatchResultDTO gameMatchResult = randomMatchService.checkMatch(checkParam);
		if (gameMatchResult.getCommonInfo() == null) {
			returnBO.setResultCode(ResultCode.GAME_MATCH_LOADING);
			return returnBO;
		}
		List<GameCommonNumDTO> gameCommons = gameMatchResult.getCommonInfo();
		List<String> listUser =new ArrayList();
		for(GameCommonNumDTO game:gameCommons){
			listUser.add(game.getUserNum());
		}
		for (GameCommonNumDTO game : gameCommons) {
			if (game.getUserNum().equals(param.getUserNum()) && game.isRoomMaster()) {
				// 获取配置信息
				Result<GamePuzzleConfigCacheDTO> resultJson = gameConfigCacheService.getGamePuzzleConfig();
				GamePuzzleConfigCacheDTO dto = resultJson.getModel();
				GameAttendSaveParam saveParam = new GameAttendSaveParam();
				saveParam.setUserNum(param.getUserNum());
				saveParam.setAttendNum(gameMatchResult.getAttendNum());
				saveParam.setAttendType(AttendTypeEnum.RANDOM);
				saveParam.setUserNums(listUser);
				saveParam.setSubStar(dto.getDeductStar());
				saveParam.setPicCount(dto.getPicCount());
				saveParam.setAttendPoint(dto.getAttendPoint());
				saveParam.setAttendStar(dto.getDeductStar());
				GameAttendSaveReturnBO attendBO = gamePuzzleConfigService.saveRandomPuzzleGameAttendInfo(saveParam);// 扣除积分
				if (!attendBO.isFlag()) {
					returnBO.setResultCode(ResultCode.GAME_PUZZLE_DEDUCTION_POINT_FAIL);
					return returnBO;
				}
				GamePuzzleGetPicSaveAttendParam gameParam = new GamePuzzleGetPicSaveAttendParam();
				gameParam.setPicCount(dto.getPicCount());
				gameParam.setAttendType(AttendTypeEnum.RANDOM);
				List<GamePuzzleGetPicBO> gamePuzzleGetPicBO = gamePuzzleConfigService.getPicByHardLevel(gameParam);
				gameConfigCacheService.setRedisKeyGamePuzzleStartType(GameCacheKeyEnum.PUZZLE_JSON + attendBO.getAttendNum(),
						JSON.toJSONString(gamePuzzleGetPicBO));
				for (int i = 0; i < gameMatchResult.getCommonInfo().size(); i++) {
					GamePuzzleCacheAnswerDetailInfo listSucKey = initGameSuccPicBO(gamePuzzleGetPicBO,
							gameMatchResult.getCommonInfo().get(i).getUserNum(), attendBO.getAttendNum());
					gameConfigCacheService.setRedisKeyGamePuzzleStartType(GameCacheKeyEnum.PUZZLE_ANSWER + attendBO.getAttendNum()
							+ gameMatchResult.getCommonInfo().get(i).getUserNum(), JSON.toJSONString(listSucKey));
				}
				returnBO.setFlag(true);
				returnBO.setResultCode(ResultCode.SUCCESS);
				returnBO.setAttendNum(attendBO.getAttendNum());
			} else {
				returnBO.setFlag(true);
				returnBO.setResultCode(ResultCode.SUCCESS);
				// returnBO.setAttendNum(param.getAttendNum());
				return returnBO;
			}
		}
		return returnBO;
	}

	@Override
	public CheckPointStatusBo checkDeductionPointSucc(GamePuzzleCheckDeductionPointParam checkParam) {
		CheckPointStatusBo bo =new CheckPointStatusBo();
		GamePuzzleAttendRecordDOExample exampleAllSucc = new GamePuzzleAttendRecordDOExample();
		exampleAllSucc.createCriteria().andAttendNumEqualTo(checkParam.getAttendNum());
		List<GamePuzzleAttendRecordDO> allSucc = gamePuzzleAttendRecordDOMapper.selectByExample(exampleAllSucc);
		boolean flag =true;
		bo.setWaitDepoint(false);
		for(GamePuzzleAttendRecordDO puzzle :allSucc){
			if(puzzle.getStatus().equals(GameAttendRecordStatusEnum.INITSTATUS.getVal())){
				bo.setWaitDepoint(true);
			}
			if(!puzzle.getStatus().equals(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal())){
				flag =false;
				bo.setAllUserCheckPoint(flag);
				return bo;
			}
		}
		bo.setAllUserCheckPoint(flag);
		return bo;
	}

	/**
	 * 好友开房
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public GameAttendSaveReturnBO friendPuzzleGameInit(PuzzleStartParam param) {
		StartTheGameBO theGame = gameRoomService.startTheGame(param.getRoomNum(), GameTypeEnum.PUZZLE);
		List<String> userNums = new ArrayList<>();
		for (StartTheGameUserDTO userInfo : theGame.getUserInfos()) {
		    userNums.add(userInfo.getUserNum());
		}
		GameAttendSaveReturnBO returnBO = new GameAttendSaveReturnBO();
		returnBO.setFlag(false);
		// 获取配置信息
		Result<GamePuzzleConfigCacheDTO> resultJson = gameConfigCacheService.getGamePuzzleConfig();
		GamePuzzleConfigCacheDTO dto = resultJson.getModel();
		GameAttendSaveParam saveParam = new GameAttendSaveParam();
		saveParam.setUserNum(userNums.get(0));
		saveParam.setAttendType(AttendTypeEnum.MANYPEOPLE);
		saveParam.setUserNums(userNums);
		saveParam.setSubStar(dto.getDeductStar());
		saveParam.setPicCount(dto.getPicCount());
		saveParam.setAttendPoint(param.getCostPoint());
		saveParam.setAttendStar(dto.getDeductStar());
		GameAttendSaveReturnBO attendBO = gamePuzzleConfigService.saveRandomPuzzleGameAttendInfo(saveParam);// 扣除积分
		if (!attendBO.isFlag()) {
			returnBO.setResultCode(ResultCode.GAME_PUZZLE_DEDUCTION_POINT_FAIL);
			return returnBO;
		}
		GamePuzzleGetPicSaveAttendParam gameParam = new GamePuzzleGetPicSaveAttendParam();
		gameParam.setPicCount(dto.getPicCount());
		gameParam.setAttendType(AttendTypeEnum.MANYPEOPLE);
		gameParam.setLevel(param.getGameLevel());
		List<GamePuzzleGetPicBO> gamePuzzleGetPicBO = gamePuzzleConfigService.getPicByHardLevel(gameParam);
		gameConfigCacheService.setRedisKeyGamePuzzleStartType(GameCacheKeyEnum.PUZZLE_JSON + attendBO.getAttendNum(),
				JSON.toJSONString(gamePuzzleGetPicBO));
		for (int i = 0; i < userNums.size(); i++) {
			GamePuzzleCacheAnswerDetailInfo listSucKey = initGameSuccPicBO(gamePuzzleGetPicBO, userNums.get(i),
					attendBO.getAttendNum());
			gameConfigCacheService.setRedisKeyGamePuzzleStartType(
					GameCacheKeyEnum.PUZZLE_ANSWER + attendBO.getAttendNum() + userNums.get(i), JSON.toJSONString(listSucKey));
		}

		returnBO.setFlag(true);
		returnBO.setResultCode(ResultCode.SUCCESS);
		returnBO.setAttendNum(attendBO.getAttendNum());
		return returnBO;
	}

	private GamePuzzleCacheAnswerDetailInfo initGameSuccPicBO(List<GamePuzzleGetPicBO> pic, String userNum, String attendNum) {
		GamePuzzleCacheAnswerDetailInfo picBo = new GamePuzzleCacheAnswerDetailInfo();
		picBo.setUserNum(userNum);
		picBo.setAttendNum(attendNum);
		List<String> listRig = new ArrayList<String>();
		for (GamePuzzleGetPicBO getPic : pic) {
			listRig.add(RandomPicUtil.getSuccessKey(getPic.getId(), getPic.getKey(), getPic.getCoefficient()));
		}
		picBo.setSingleUseTime(new ArrayList<String>());
		picBo.setRightAnswer(listRig);
		picBo.setTmpAnswer(listRig);
		return picBo;
	}

	/**
	 * 判断拼图是否正确
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public GamePuzzleResultBO checkPuzzlePicStatus(GamePuzzleValidSuccSrvParam srvParam) {
		Result<String> startTime = gameConfigCacheService.getGamePuzzleStartTimeValue(srvParam.getUserNum());// 获取题目开始时间
		long endTime = System.currentTimeMillis();
		long useTime = (endTime - Long.valueOf(startTime.getModel())) / 1000;
		GamePuzzleResultBO resulBO = new GamePuzzleResultBO();
		resulBO.setUserNum(srvParam.getUserNum());
		resulBO.setResultCode(ResultCode.FAIL);
		resulBO.setFlag(false);
		// 获取缓存数据
		Result<String> successKeyResult = gameConfigCacheService
				.getRedisKeyGamePuzzleStartType(GameCacheKeyEnum.PUZZLE_JSON + srvParam.getAttendNum());
		Result<GamePuzzleConfigCacheDTO> result = gameConfigCacheService.getGamePuzzleConfig();
		GamePuzzleConfigCacheDTO dto = result.getModel();
		if (null == successKeyResult || null == successKeyResult.getModel()) {
			resulBO.setResultCode(ResultCode.NOT_FOUND_DATA);
			resulBO.setFlag(false);
			return resulBO;
		}
		List<GamePuzzleGetPicDTO> dtoCache = JSON.parseArray(successKeyResult.getModel(), GamePuzzleGetPicDTO.class);// 缓存数据
		int currentPicTime = getChallengeTimeByHardLevel(srvParam.getGameId(), dtoCache, dto);
		if (currentPicTime == 0) {
			resulBO.setResultCode(ResultCode.NOT_FOUND_DATA);
			return resulBO;// 异常
		}
		// 获取游戏缓存数据
		Result<String> puzzleResult = gameConfigCacheService
				.getRedisKeyGamePuzzleStartType(GameCacheKeyEnum.PUZZLE_ANSWER + srvParam.getAttendNum() + srvParam.getUserNum());
		GamePuzzleCacheAnswerDetailInfo listSucKey = JSON.parseObject(puzzleResult.getModel(), GamePuzzleCacheAnswerDetailInfo.class);
		int canIgnoreTime = gameSrvConfig.getDelayTime();
		GameHardLevelEnum levelEnum = getHardLevelByGameId(srvParam.getGameId(), dtoCache);
		GamePuzzlePointStartByDiffBO gameSocre = getCurrentPuzzlePicScore(srvParam.getGameId(), Integer.valueOf(String.valueOf(useTime)),dtoCache,levelEnum);//获取当前游戏得分
		if (listSucKey.getTmpAnswer().size() == 0) {
			resulBO.setResultCode(ResultCode.NOT_FOUND_DATA);
			return resulBO;// 异常
		}
		if (srvParam.getPicNum().equals(listSucKey.getTmpAnswer().get(0))
				&& (useTime <= currentPicTime || useTime <= currentPicTime + canIgnoreTime)) { // 拼图正确
			if (useTime <= currentPicTime + canIgnoreTime && useTime > currentPicTime) {
				useTime = useTime - canIgnoreTime;
			}
			resulBO.setFlag(true);
			resulBO.setResultCode(ResultCode.SUCCESS);
			listSucKey.getTmpAnswer().remove(0);
			listSucKey.getSingleUseTime().add(String.valueOf(useTime));// 将当前拼图时间放进去
			listSucKey.setTotalScore(listSucKey.getTotalScore()+gameSocre.getScore());
			listSucKey.setTotalUseTime(listSucKey.getTotalUseTime()+Integer.valueOf(String.valueOf(useTime)));//总时间
			if (listSucKey.getTmpAnswer().size() == 0) { // 拼图完毕
				gameConfigCacheService.setRedisKeyGamePuzzleStartType(GameCacheKeyEnum.PUZZLE_ANSWER + srvParam.getAttendNum() + srvParam.getUserNum(), JSON.toJSONString(listSucKey));
				gameConfigCacheService.setGamePuzzleStartTimeValue(srvParam.getUserNum(), String.valueOf(System.currentTimeMillis()));
				// 加积分
				resulBO.setGameScore(gameSocre.getScore());
				PuzzleCheckIsOverBO lastPop = checkPuzzleIsOver(srvParam.getUserNum(), srvParam.getAttendNum());// 获取剩余人数
				resulBO.setLastCount(lastPop.getCount());
				boolean flag = checkMyPuzzleIsOverByAttendNum(srvParam.getUserNum(),srvParam.getAttendNum());
				resulBO.setOver(flag);
				if (lastPop.getCount() <= 0) {
					GamePuzzleLoadRankingParam lrp = new GamePuzzleLoadRankingParam();
					lrp.setAttendNum(srvParam.getAttendNum());
					lrp.setUserNum(srvParam.getUserNum());
					loadRankingInfo(lrp,listSucKey.getTotalScore(),false);//更新所有人
				}
				return resulBO;
			}
			gameConfigCacheService.setRedisKeyGamePuzzleStartType(
					GameCacheKeyEnum.PUZZLE_ANSWER + srvParam.getAttendNum() + srvParam.getUserNum(), JSON.toJSONString(listSucKey));
			gameConfigCacheService.setGamePuzzleStartTimeValue(srvParam.getUserNum(), String.valueOf(System.currentTimeMillis()));
			PuzzleCheckIsOverBO lastPop = checkPuzzleIsOver(srvParam.getUserNum(), srvParam.getAttendNum());// 获取剩余人数
			resulBO.setLastCount(lastPop.getCount());
			resulBO.setScoreLevel(getLevel(useTime, levelEnum));
			resulBO.setGameScore(gameSocre.getScore());
			return resulBO;
		} else { // 拼图失败
			resulBO.setFlag(false);
			resulBO.setResultCode(ResultCode.SUCCESS);
			listSucKey.getTmpAnswer().remove(0);
			listSucKey.getSingleUseTime().add(String.valueOf(useTime));// 将当前拼图时间放进去
			listSucKey.setTotalUseTime(listSucKey.getTotalUseTime()+Integer.valueOf(String.valueOf(useTime)));//总时间
			if (listSucKey.getTmpAnswer().size() == 0) { // 拼图完毕
				gameConfigCacheService.setRedisKeyGamePuzzleStartType(
						GameCacheKeyEnum.PUZZLE_ANSWER + srvParam.getAttendNum() + srvParam.getUserNum(), JSON.toJSONString(listSucKey));
				gameConfigCacheService.setGamePuzzleStartTimeValue(srvParam.getUserNum(), String.valueOf(System.currentTimeMillis()));
				PuzzleCheckIsOverBO lastPop = checkPuzzleIsOver(srvParam.getUserNum(), srvParam.getAttendNum());// 获取剩余人数
				resulBO.setLastCount(lastPop.getCount());
				boolean flag = checkMyPuzzleIsOverByAttendNum(srvParam.getUserNum(),srvParam.getAttendNum());
				resulBO.setOver(flag);
				if (lastPop.getCount() <= 0) {
					GamePuzzleLoadRankingParam lrp = new GamePuzzleLoadRankingParam();
					lrp.setAttendNum(srvParam.getAttendNum());
					lrp.setUserNum(srvParam.getUserNum());
					loadRankingInfo(lrp,listSucKey.getTotalScore(),false);//更新所有人
				}
				resulBO.setGameScore(0);
				// 超时拼图
				return resulBO;
			}
			gameConfigCacheService.setRedisKeyGamePuzzleStartType(
					GameCacheKeyEnum.PUZZLE_ANSWER + srvParam.getAttendNum() + srvParam.getUserNum(), JSON.toJSONString(listSucKey));
			gameConfigCacheService.setGamePuzzleStartTimeValue(srvParam.getUserNum(), String.valueOf(System.currentTimeMillis()));
			PuzzleCheckIsOverBO lastPop = checkPuzzleIsOver(srvParam.getUserNum(), srvParam.getAttendNum());// 获取剩余人数
			resulBO.setLastCount(lastPop.getCount());
			resulBO.setGameScore(0);
			boolean flag = checkMyPuzzleIsOverByAttendNum(srvParam.getUserNum(),srvParam.getAttendNum());
			resulBO.setOver(flag);
			return resulBO;
		}
	}

	private boolean checkMyPuzzleIsOverByAttendNum(String userNum, String attendNum) {
		Result<String> result = gameConfigCacheService.getRedisKeyGamePuzzleStartType(GameCacheKeyEnum.PUZZLE_ANSWER+attendNum+userNum);
		GamePuzzleCacheAnswerDetailInfo listSucKey = JSON.parseObject(result.getModel(), GamePuzzleCacheAnswerDetailInfo.class);
		if(listSucKey.getTmpAnswer().size()==0){
			return true;
		}
		return false;
	}

	private GameScoreLevelEnum getLevel(long useTime, GameHardLevelEnum level) {
		DifficultyInfoParam param = new DifficultyInfoParam();
		param.setSecond(Integer.valueOf(String.valueOf(useTime)));
		param.setLevelEnum(level);
		GamePuzzlePointStartByDiffBO gameP = gamePuzzleDifficultyService.getPuzzlePointStart(param);
		return gameP.getLevelEnum();

	}

	/**
	 * 根据游戏用时获取到得分
	 *
	 * @param gameId
	 * @param useTime
	 * @param dtoCache
	 * @return
	 */
	private GamePuzzlePointStartByDiffBO getCurrentPuzzlePicScore(String gameId, int useTime, List<GamePuzzleGetPicDTO> dtoCache,GameHardLevelEnum levelEnum) {
		DifficultyInfoParam param = new DifficultyInfoParam();
		//GameHardLevelEnum levelEnum = getHardLevelByGameId(gameId, dtoCache);
		param.setLevelEnum(levelEnum);
		param.setSecond(Integer.valueOf(useTime));
		GamePuzzlePointStartByDiffBO mybo = gamePuzzleDifficultyService.getPuzzlePointStart(param);
		return mybo;
	}

	private void updateTotalScore(GamePuzzleValidSuccSrvParam srvParam, List<String> useTimelist, List<GamePuzzleGetPicDTO> dtoCache) {
		int useTime = 0;
		int score = 0;
		for(int i=0;i<useTimelist.size();i++){
			DifficultyInfoParam param = new DifficultyInfoParam();
			GameHardLevelEnum levelEnum = dtoCache.get(i).getLevel();
			param.setLevelEnum(levelEnum);
			param.setSecond(Integer.valueOf(useTimelist.get(i)));
			GamePuzzlePointStartByDiffBO point = gamePuzzleDifficultyService.getPuzzlePointStart(param);
			score = score + point.getScore();
			useTime += Integer.valueOf(useTimelist.get(i));
		}
		GamePuzzleAttendRecordDOExample example = new GamePuzzleAttendRecordDOExample();
		example.createCriteria().andUserNumEqualTo(srvParam.getUserNum()).andAttendNumEqualTo(srvParam.getAttendNum());
		List<GamePuzzleAttendRecordDO> list = gamePuzzleAttendRecordDOMapper.selectByExample(example);
		GamePuzzleAttendRecordDO game = list.get(0);
		game.setGameScore(score);
		game.setGameUseTime(useTime);
		gamePuzzleAttendRecordDOMapper.updateByPrimaryKeySelective(game);

	}

	private GameHardLevelEnum getHardLevelByGameId(String gameId, List<GamePuzzleGetPicDTO> dtoCache) {
		for (GamePuzzleGetPicDTO puzz : dtoCache) {
			if (Long.valueOf(gameId).equals(puzz.getId())) {
				return puzz.getLevel();
			}
		}
		return null;
	}

	/**
	 * 根据id 获取当前难度挑战最大时间
	 *
	 * @param gameId
	 * @param dtoCache
	 * @param dto
	 * @return
	 */
	private int getChallengeTimeByHardLevel(String gameId, List<GamePuzzleGetPicDTO> dtoCache, GamePuzzleConfigCacheDTO dto) {
		for (GamePuzzleGetPicDTO puzz : dtoCache) {
			if (Long.valueOf(gameId).equals(puzz.getId())) {
				List<GameDifficultyParam> listDiff = dto.getDifficultys();
				for (GameDifficultyParam diff : listDiff) {
					if (diff.getCoefficient() == puzz.getCoefficient()) {
						return diff.getChallengeTime();
					}
				}
				return 0;
			}
		}
		return 0;
	}

	public void loadRankingInfo(GamePuzzleLoadRankingParam param,int totalScore,boolean flag) {
		// 获取到当前参与编号下的json记录
		Result<List<String>> puzzleResult = gameConfigCacheService
				.getLikeRedisKeyGamePuzzleStartType(GameCacheKeyEnum.PUZZLE_ANSWER + param.getAttendNum());
		Result<String> successKeyResult = gameConfigCacheService
				.getRedisKeyGamePuzzleStartType(GameCacheKeyEnum.PUZZLE_JSON + param.getAttendNum());
		List<GamePuzzleGetPicDTO> dtoCache = JSON.parseArray(successKeyResult.getModel(), GamePuzzleGetPicDTO.class);// 缓存数据
		Map<String, Integer> map = sortRank(puzzleResult.getModel());//排序后的包含并列
		GamePuzzlePointStarBO point = getPointAndStar(dtoCache.get(0).getAttendType(), param.getAttendNum(),map,totalScore);//获取星星积分分配
		for (String json : puzzleResult.getModel()) {
			GamePuzzleCacheAnswerDetailInfo listSucKey = JSON.parseObject(json, GamePuzzleCacheAnswerDetailInfo.class);
				if (listSucKey.getSingleUseTime().size() == 3) { // 更新游戏状态为成功
					GamePuzzlePointStarDetail pointStar = getPointStar(point, listSucKey.getUserNum());
					updateAttendRecordInfo(param.getAttendNum(), listSucKey, map.get(listSucKey.getUserNum()),
							GameAttendRecordStatusEnum.GAMEPLAYSUCCESS, pointStar);
				} else { // 更新游戏状态为失败
					GameAttendRecordStatusEnum status =GameAttendRecordStatusEnum.GAMEPLAYFAIL;
					updateAttendRecordInfo(param.getAttendNum(), listSucKey, map.get(listSucKey.getUserNum()),status, null);
				}
		}
	}

	private GamePuzzlePointStarDetail getPointStar(GamePuzzlePointStarBO point, String userNum) {
		for (GamePuzzlePointStarDetail detail : point.getPointDetail()) {
			if (detail.getUserNum().equals(userNum)) {
				return detail;
			}
		}
		return null;
	}

	private void updateAttendRecordInfo(String attendNum, GamePuzzleCacheAnswerDetailInfo cacheAnswerDetailInfo, Integer rank, GameAttendRecordStatusEnum gameplayfail,
			GamePuzzlePointStarDetail pointStar) {
		GamePuzzleAttendRecordDOExample example = new GamePuzzleAttendRecordDOExample();
		List<Byte> listStatus =new ArrayList<>();
		listStatus.add(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
		listStatus.add(GameAttendRecordStatusEnum.GAMEPLAYSUCCESS.getVal());
		listStatus.add(GameAttendRecordStatusEnum.GAMEPLAYFAIL.getVal());
		example.createCriteria().andAttendNumEqualTo(attendNum).andUserNumEqualTo(cacheAnswerDetailInfo.getUserNum())
				.andStatusIn(listStatus);
		List<GamePuzzleAttendRecordDO> list = gamePuzzleAttendRecordDOMapper.selectByExample(example);
		if (null != list && list.size() > 0) {
			GamePuzzleAttendRecordDO record = list.get(0);
			record.setGameRank(rank);
			record.setGameScore(cacheAnswerDetailInfo.getTotalScore());
			record.setGameUseTime(cacheAnswerDetailInfo.getTotalUseTime());
			record.setGmtModified(new Date());
			if (pointStar != null) {
				if(pointStar.getPoint().compareTo(BigDecimal.ZERO)==0){
					if(!record.getAttendType().equals(AttendTypeEnum.STANDALONE.getVal())){
						record.setStatus(GameAttendRecordStatusEnum.GAMEPLAYFAIL.getVal());
					}else{
						record.setStatus(gameplayfail.getVal());
					}
				}else{
					record.setStatus(gameplayfail.getVal());
				}
				record.setRewardPoint(pointStar.getPoint());
				record.setRewardStar(pointStar.getStar());
			}else{
				record.setRewardPoint(new BigDecimal(0));
				record.setRewardStar(0);
				record.setStatus(GameAttendRecordStatusEnum.GAMEPLAYFAIL.getVal());
			}
			gamePuzzleAttendRecordDOMapper.updateByPrimaryKeySelective(record);
			//if(new BigDecimal(record.getAttendPoint()).subtract(record.getRewardPoint()).compareTo(BigDecimal.ZERO)>0){//正数加积分
			if(!record.getAttendType().equals(AttendTypeEnum.STANDALONE.getVal())){
				if(record.getRewardPoint().compareTo(BigDecimal.ZERO)!=0){
					boolean chRobot = gameRobotAccountService.checkMemberIsRobot(record.getUserNum());
					if(!chRobot){
						puzzleGameIncreasePointTransactionMainServiceImpl.sendNotice(record.getId());
					}
				}
			}else{ //单机成功加星星否则不加
				if(record.getStatus().equals(GameAttendRecordStatusEnum.GAMEPLAYSUCCESS.getVal())){
					if(record.getRewardStar()>0){
						List<GameAccountAllotParam> listAllot =new ArrayList<>();
						GameAccountAllotParam allotParam = new GameAccountAllotParam();
						allotParam.setStar(record.getRewardStar());
						allotParam.setUserNum(record.getUserNum());
						listAllot.add(allotParam);
						GameAccountStarParam gameAccount =new GameAccountStarParam();
						gameAccount.setAttendNum(record.getAttendNum());
						gameAccount.setGameType(GameTypeEnum.PUZZLE);
						gameAccount.setRecordEnum(GameAttendRecordStatusEnum.GAMEPLAYSUCCESS);
						gameAccount.setList(listAllot);
						gameAccountService.dealStar(gameAccount);
					}
				}
			}
			//}
		}
	}

	/**
	 * 排序
	 *
	 * @param model
	 * @return
	 */
	private Map<String, Integer> sortRank(List<String> model) {
		List<GamePuzzleRankIndex> listTimes = new ArrayList<GamePuzzleRankIndex>();
		List<Integer> lt = new ArrayList<Integer>();
		Map<String, Integer> map = new HashMap<>();
		for (String json : model) {
			GamePuzzleCacheAnswerDetailInfo listSucKey = JSON.parseObject(json, GamePuzzleCacheAnswerDetailInfo.class);
			GamePuzzleRankIndex rank = new GamePuzzleRankIndex();
			int userTime = getTotalUseTime(listSucKey.getSingleUseTime());
			rank.setTotalUseTime(userTime);
			rank.setUserNum(listSucKey.getUserNum());
			rank.setTotalScore(listSucKey.getTotalScore());
			listTimes.add(rank);
		}
		Collections.sort(listTimes, new Comparator<GamePuzzleRankIndex>() {
			@Override
			public int compare(GamePuzzleRankIndex o1, GamePuzzleRankIndex o2) {
				return o1.getTotalScore() - o2.getTotalScore();
			}

		});
		for(GamePuzzleRankIndex index :listTimes){
        	lt.add(index.getTotalScore());
        }
		lt=scoreRank(lt);
		for (int i = 0; i < listTimes.size(); i++) {
			map.put(listTimes.get(i).getUserNum(), lt.get(i));
		}
		return map;
	}
	
	private static List scoreRank(List score) {
		List lt = new ArrayList();
		List lis = new ArrayList();
		for(int i=0;i<score.size();i++){
			if(!lis.contains(score.get(i))){
				lis.add(score.get(i));
			}
		}
		Collections.sort(lis);
		Collections.reverse(lis);
		for(int i=0;i<score.size();i++){
			lt.add(lis.indexOf(score.get(i))+1);
		}
		
		return lt;
	}

	private int getTotalUseTime(List<String> singleUseTime) {
		int totalTime = 0;
		if (singleUseTime.size() != 3) {// 有异常答题
			totalTime = 100000;
		} else {
			for (String curTime : singleUseTime) {
				totalTime += Integer.valueOf(curTime);
			}
		}
		return totalTime;
	}

	@Override
	public List<GamePuzzleRankReturnBO> getMyGameRank(GamePuzzleLoadRankingParam param) {
		GamePuzzleAttendRecordDOExample example = new GamePuzzleAttendRecordDOExample();
		List lt =new ArrayList();
		lt.add(GameAttendRecordStatusEnum.GAMEPLAYSUCCESS.getVal());
		lt.add(GameAttendRecordStatusEnum.GAMEPLAYFAIL.getVal());
		example.createCriteria().andAttendNumEqualTo(param.getAttendNum()).andStatusIn(lt);
		List<GamePuzzleAttendRecordDO> list = gamePuzzleAttendRecordDOMapper.selectByExample(example);
		List<GamePuzzleRankReturnBO> renkBo = GamePuzzleAttendRecordConverter.convertGamePuzzleRankReturnBO(list);
		return renkBo;
	}

	/**
	 * 获取积分以及星星
	 * 
	 * @param attendType
	 * @param attendNum
	 * @param map key 用户编号，value 排名，包含并列
	 * @param totalScore
	 * @return
	 */
	public GamePuzzlePointStarBO getPointAndStar(AttendTypeEnum attendType, String attendNum,Map<String,Integer> map,int totalScore) {
		GamePuzzlePointStarBO gamePuzzle = new GamePuzzlePointStarBO();
		List<GamePuzzlePointStarDetail> pointDetail = new ArrayList<GamePuzzlePointStarDetail>();
		Result<GamePuzzleConfigCacheDTO> result = gameConfigCacheService.getGamePuzzleConfig();
		GamePuzzleConfigCacheDTO config = result.getModel();
		if (attendType.equals(AttendTypeEnum.STANDALONE)) { // 单机全部题目答对就取配置的
			GamePuzzlePointStarDetail detail = new GamePuzzlePointStarDetail();
			List<GamePuzzleAttendRecordDO> records = getAttendByAttendNum(attendNum);
			detail.setUserNum(records.get(0).getUserNum());
			detail.setPoint(new BigDecimal(0));
			if (totalScore >= config.getSuccessScore()) {//挑战成功
				detail.setStar(config.getAwardStar());
			} else {
				detail.setStar(0);
			}
			pointDetail.add(detail);
			gamePuzzle.setPointDetail(pointDetail);
			return gamePuzzle;
		} else {
			List<GamePuzzleAttendRecordDO> records = getAttendByAttendNum(attendNum);//数据库查询参与人数
			List<GamePuzzlePointStartReturnBO> rtn =new ArrayList<GamePuzzlePointStartReturnBO>();
			List<GamePointAllotParam> listGamePoint = config.getAllotList();
			for (GamePointAllotParam allot : listGamePoint) {
				if (allot.getAttendCount() == records.size()) {
					List<BigDecimal> pointRateList =initpointRateList(allot.getRankPoint());
					List<Integer> starRateList =initstarRateList(allot.getRankStar());
					List<GamePuzzleRankBO> rankLitsBO = rankLitsBO(map);
					List<Integer> rankList =initrankList(rankLitsBO);
					BigDecimal totalPoint = new BigDecimal(records.size()).multiply(new BigDecimal(records.get(0).getAttendPoint()));
					GetPointAndStarUtil.getScoreAndStar(rtn, 0, rankList, pointRateList, starRateList, totalPoint);
					for(int i=0;i<rtn.size();i++){
						GamePuzzlePointStarDetail detail =new GamePuzzlePointStarDetail();
						detail.setUserNum(rankLitsBO.get(i).getUserNum());
						detail.setPoint(rtn.get(i).getPoint().setScale(0,BigDecimal.ROUND_DOWN));
						detail.setStar(rtn.get(i).getStar());
						pointDetail.add(detail);
					}
					gamePuzzle.setPointDetail(pointDetail);
				}
			}
		}
		return gamePuzzle;
	}

	private List<Integer> initrankList(List<GamePuzzleRankBO> rankLitsBO) {
		List<Integer> list =new ArrayList<Integer>();
		for(GamePuzzleRankBO rank : rankLitsBO){
			list.add(rank.getRank());
		}
		return list;
	}

	private List<GamePuzzleRankBO> rankLitsBO(Map<String, Integer> map) {
		List<GamePuzzleRankBO> list =new ArrayList<GamePuzzleRankBO>();
		for(String key:map.keySet()){
			GamePuzzleRankBO rank =new GamePuzzleRankBO();
			rank.setRank(map.get(key));
			rank.setUserNum(key);
			list.add(rank);
		}
		Collections.sort(list,new Comparator<GamePuzzleRankBO>() {
			@Override
			public int compare(GamePuzzleRankBO o1, GamePuzzleRankBO o2) {
				return o1.getRank() - o2.getRank();
			}

		});
		return list;
	}

	private List<Integer> initstarRateList(List<String> rankStar) {
		List<Integer> list =new ArrayList<Integer>();
		for(String rank :rankStar){
			list.add(Integer.valueOf(rank));
		}
		return list;
	}

	private List<BigDecimal> initpointRateList(List<String> rankPoint) {
		List<BigDecimal> list =new ArrayList<BigDecimal>();
		for(String str : rankPoint){
			list.add(new BigDecimal(str));
		}
		return list;
	}
	private List<GamePuzzleAttendRecordDO> getAttendByAttendNum(String attendNum) {
		List<Byte> status = new ArrayList<Byte>();
		status.add(GameAttendRecordStatusEnum.INITSTATUS.getVal());
		status.add(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
		status.add(GameAttendRecordStatusEnum.GAMEPLAYSUCCESS.getVal());
		status.add(GameAttendRecordStatusEnum.GAMEPLAYFAIL.getVal());
		GamePuzzleAttendRecordDOExample example = new GamePuzzleAttendRecordDOExample();
		example.createCriteria().andAttendNumEqualTo(attendNum).andStatusIn(status);
		example.setOrderByClause("game_score desc");
		List<GamePuzzleAttendRecordDO> records = gamePuzzleAttendRecordDOMapper.selectByExample(example);
		return records;
	}

	@Override
	public PuzzleCheckIsOverBO checkPuzzleIsOver(String userNum, String attendNum) {
		PuzzleCheckIsOverBO isover = new PuzzleCheckIsOverBO();
		Result<List<String>> puzzleResult = gameConfigCacheService
				.getLikeRedisKeyGamePuzzleStartType(GameCacheKeyEnum.PUZZLE_ANSWER + attendNum);
		int i = 0;// 多少个人未拼完图
		for (String json : puzzleResult.getModel()) {
			GamePuzzleCacheAnswerDetailInfo listSucKey = JSON.parseObject(json, GamePuzzleCacheAnswerDetailInfo.class);
			if (listSucKey.getTmpAnswer().size() > 0) {
				i++;
			}
		}
		isover.setCount(i);
		if (i == 0) {
			isover.setIsOver(true);
		} else {
			isover.setIsOver(false);
		}
		return isover;
	}

	@Override
	public GameRoomDetailsDTO quitRoom(String userNum, String groupNum) {
		GameRoomDetailsDTO dto =new GameRoomDetailsDTO();
		dto.setIsDissolution(false);
		if (null != groupNum) {
			ExitGameRoomParam exitParam = new ExitGameRoomParam();
			exitParam.setUserNum(userNum);
			exitParam.setRoomNum(groupNum);
			exitParam.setGameType(GameTypeEnum.PUZZLE);
			try{
				dto = gameRoomService.exitGameRoom(exitParam);
			}catch (Exception e){

			}
			return dto;
		}
		return dto;
	}

	@Override
	public int getPuzzleAttendRecordByAttendNum(String attendNum) {
		GamePuzzleAttendRecordDOExample example = new GamePuzzleAttendRecordDOExample();
		example.createCriteria().andAttendNumEqualTo(attendNum);
		return gamePuzzleAttendRecordDOMapper.selectByExample(example).size();
	}

	@Override
	public List<GamePuzzleAttendRecordDO> getAttendRecordByAttendAndStatus(String attendNum, GameAttendRecordStatusEnum status) {
		GamePuzzleAttendRecordDOExample example = new GamePuzzleAttendRecordDOExample();
		example.createCriteria().andAttendNumEqualTo(attendNum).andStatusEqualTo(status.getVal());
		return gamePuzzleAttendRecordDOMapper.selectByExample(example);
	}

	@Override
	public String exceptionExitExecute(String userNum) {
		GamePuzzleAttendRecordDOExample example = new GamePuzzleAttendRecordDOExample();
		example.createCriteria().andUserNumEqualTo(userNum).andStatusEqualTo(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
		example.setOrderByClause("id desc");
		List<GamePuzzleAttendRecordDO> list = gamePuzzleAttendRecordDOMapper.selectByExample(example);
		if(list.size()==0){
			return null;
		}
		String attendNum=list.get(0).getAttendNum();
		Result<String> successKeyResult = gameConfigCacheService.getRedisKeyGamePuzzleStartType(GameCacheKeyEnum.PUZZLE_JSON + attendNum);
		List<GamePuzzleGetPicDTO> dtoCache = JSON.parseArray(successKeyResult.getModel(), GamePuzzleGetPicDTO.class);// 缓存数据
		//判断是否剩下一个人在答题，如果剩下一个人直接结算
		Result<String> result = gameConfigCacheService.getRedisKeyGamePuzzleStartType(GameCacheKeyEnum.PUZZLE_ANSWER+attendNum+userNum);//自己的缓存更新后续得分为0
		if(null == result.getModel()){
			return null;
		}
		GamePuzzleCacheAnswerDetailInfo listSucKey = JSON.parseObject(result.getModel(), GamePuzzleCacheAnswerDetailInfo.class);
		if(null == listSucKey){
			return null;
		}
		logger.info("用户退出清理缓存前是"+JSON.toJSONString(listSucKey));
		//没有拼完图的,或者随机拼完图退出的
		if((listSucKey.getTmpAnswer().size()<=3&&listSucKey.getTmpAnswer().size()>0)||(dtoCache.get(0).getAttendType().equals(AttendTypeEnum.RANDOM)&&listSucKey.getTmpAnswer().size()==0)){
			listSucKey.setTmpAnswer(new ArrayList<String>());
			listSucKey.setSingleUseTime(Arrays.asList("1","1","1"));
			listSucKey.setTotalScore(-1);
			listSucKey.setTotalUseTime(0);
		}
		gameConfigCacheService.setRedisKeyGamePuzzleStartType(
				GameCacheKeyEnum.PUZZLE_ANSWER + attendNum + userNum, JSON.toJSONString(listSucKey));//更新自己的缓存
		
		Result<List<String>> puzzleResult = gameConfigCacheService.getLikeRedisKeyGamePuzzleStartType(GameCacheKeyEnum.PUZZLE_ANSWER + attendNum);
		int i = 0;
		for (String json : puzzleResult.getModel()) {
			GamePuzzleCacheAnswerDetailInfo puzzle = JSON.parseObject(json, GamePuzzleCacheAnswerDetailInfo.class);
			logger.info("用户退出清理缓存后是"+JSON.toJSONString(puzzle));
			if (puzzle.getTmpAnswer().size() > 0) {
				i++;
			}
		}
		if(i==0){ //直接触发结算
			GamePuzzleLoadRankingParam lrp = new GamePuzzleLoadRankingParam();
			lrp.setAttendNum(attendNum);
			lrp.setUserNum(userNum);
			loadRankingInfo(lrp,listSucKey.getTotalScore(),true);//更新所有人
		}
		return String.valueOf(i);
	}

	@Override
	public GamePuzzleAttendRecordBO getPuzzleAttendRecordNearlyData(String userNum) {
		List<Byte> listStatus =new ArrayList<>();
		listStatus.add(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
		listStatus.add(GameAttendRecordStatusEnum.GAMEPLAYSUCCESS.getVal());
		listStatus.add(GameAttendRecordStatusEnum.GAMEPLAYFAIL.getVal());
		GamePuzzleAttendRecordDOExample example = new GamePuzzleAttendRecordDOExample();
		example.createCriteria().andUserNumEqualTo(userNum).andStatusIn(listStatus);
		example.setOrderByClause("id desc");
		List<GamePuzzleAttendRecordDO> list = gamePuzzleAttendRecordDOMapper.selectByExample(example);
		if(list.size()==0){
			return null;
		}
		GamePuzzleAttendRecordBO bo =GamePuzzleAttendRecordConverter.convertGamePuzzleAttendRecordBO(list.get(0));
		return bo;
	}

	@Override
	public List<String> getAttendRecordUserNums(String attendNum) {
		GamePuzzleAttendRecordDOExample example = new GamePuzzleAttendRecordDOExample();
		example.createCriteria().andAttendNumEqualTo(attendNum).andStatusEqualTo(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
		List<GamePuzzleAttendRecordDO> attendRecordDOS = gamePuzzleAttendRecordDOMapper.selectByExample(example);
		List<String> userNumList = new ArrayList<>();
		if (attendRecordDOS.isEmpty()) {
			return userNumList;
		}
		for (GamePuzzleAttendRecordDO attendRecordDO : attendRecordDOS) {
			userNumList.add(attendRecordDO.getUserNum());
		}
		return userNumList;
	}

}

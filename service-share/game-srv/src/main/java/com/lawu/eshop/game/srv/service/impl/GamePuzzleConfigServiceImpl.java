package com.lawu.eshop.game.srv.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.param.GameDifficultyParam;
import com.lawu.eshop.cache.param.GamePuzzleConfigParam;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.param.GamePointAllotParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.param.GameAccountAllotParam;
import com.lawu.eshop.game.param.GameAccountStarParam;
import com.lawu.eshop.game.param.GameAttendSaveParam;
import com.lawu.eshop.game.param.GamePuzzleGetPicSaveAttendParam;
import com.lawu.eshop.game.param.GamePuzzleRewardPointAndStarParam;
import com.lawu.eshop.game.srv.GameSrvConfig;
import com.lawu.eshop.game.srv.bo.GameAttendSaveReturnBO;
import com.lawu.eshop.game.srv.bo.GamePointAllotBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleAttendRecordBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleConfigBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleDifficultyBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleGetPicBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleMaxPointStartByDiffBO;
import com.lawu.eshop.game.srv.bo.GamePuzzlePicBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleResultBO;
import com.lawu.eshop.game.srv.converter.GamePuzzleConfigConverter;
import com.lawu.eshop.game.srv.converter.GamePuzzleRandomUtil;
import com.lawu.eshop.game.srv.domain.GamePointAllotDO;
import com.lawu.eshop.game.srv.domain.GamePointAllotDOExample;
import com.lawu.eshop.game.srv.domain.GamePuzzleAttendRecordDO;
import com.lawu.eshop.game.srv.domain.GamePuzzleAttendRecordDOExample;
import com.lawu.eshop.game.srv.domain.GamePuzzleConfigDO;
import com.lawu.eshop.game.srv.domain.GamePuzzleDifficultyDO;
import com.lawu.eshop.game.srv.domain.GamePuzzleDifficultyDOExample;
import com.lawu.eshop.game.srv.mapper.GamePointAllotDOMapper;
import com.lawu.eshop.game.srv.mapper.GamePuzzleAttendRecordDOMapper;
import com.lawu.eshop.game.srv.mapper.GamePuzzleConfigDOMapper;
import com.lawu.eshop.game.srv.mapper.GamePuzzleDifficultyDOMapper;
import com.lawu.eshop.game.srv.service.GameAccountService;
import com.lawu.eshop.game.srv.service.GamePuzzleAttendRecordService;
import com.lawu.eshop.game.srv.service.GamePuzzleConfigService;
import com.lawu.eshop.game.srv.service.GamePuzzleDifficultyService;
import com.lawu.eshop.game.srv.service.GamePuzzlePicService;
import com.lawu.eshop.game.srv.service.GameRobotAccountService;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.mq.dto.game.reply.PuzzleGameDeductionReply;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
@Service
public class GamePuzzleConfigServiceImpl implements GamePuzzleConfigService {

	@Autowired
	private GamePuzzleConfigDOMapper gamePuzzleConfigDOMapper;

	@Autowired
	private GamePointAllotDOMapper gamePointAllotDOMapper;

	@Autowired
	private GameSrvConfig gameSrvConfig;
	@Autowired
	private GamePuzzlePicService gamePuzzlePicService;

	@Autowired
	private GamePuzzleDifficultyService gamePuzzleDifficultyService;

	@Autowired
	private GamePuzzleDifficultyDOMapper gamePuzzleDifficultyDOMapper;

	@Autowired
	private GamePuzzleAttendRecordService gamePuzzleAttendRecordService;
	@Autowired
	private GameAccountService gameAccountService;
	@Autowired
	private GameRobotAccountService gameRobotAccountService;
	@Autowired
	private GamePuzzleAttendRecordDOMapper gamePuzzleAttendRecordDOMapper; 
	@Autowired
	@Qualifier("puzzleGameDeductionPointTransactionMainServiceImpl")
	private TransactionMainService<PuzzleGameDeductionReply> puzzleGameDeductionPointTransactionMainServiceImpl;
	
	@Autowired
	@Qualifier("puzzleGameIncreasePointTransactionMainServiceImpl")
	private TransactionMainService<PuzzleGameDeductionReply> puzzleGameIncreasePointTransactionMainServiceImpl;

	Logger logger = LoggerFactory.getLogger(GamePuzzleConfigServiceImpl.class);
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveGamePuzzleConfig(GamePuzzleConfigParam param) {
		GamePuzzleConfigDO record = new GamePuzzleConfigDO();
		record.setAttendPoint(param.getAttendPoint());
		record.setAttendMaxPoint(param.getAttendMaxPoint());
		record.setAwardPoint(param.getAwardPoint());
		record.setAwardStar(param.getAwardStar());
		record.setCountDown(param.getCountDown());
		record.setDeductStar(param.getDeductStar());
		record.setGmtCreate(new Date());
		record.setGmtModified(new Date());
		record.setRoomMaxNum(param.getRoomMaxNum());
		record.setShareAttendCount(param.getShareAttendCount());
		record.setStatus(GameConfigStatusEnum.ENABLE.getVal());
		record.setFreeCount(param.getFreeCount());
		record.setPicCount(param.getPicCount());
		record.setSuccessScore(param.getSuccessScore());
		record.setForbiddenRemark(param.getForbiddenRemark());
		record.setRobotStatus(param.getStatusEnum().getVal());
		record.setRobotEffectiveTime(param.getRobotEffectiveTime());
		gamePuzzleConfigDOMapper.insertSelective(record);

		// 设置胜利积分分配
		List<GamePointAllotParam> list = param.getAllotList();
		for (GamePointAllotParam gamePointAllotParam : list) {
			GamePointAllotDO allotRecoed = new GamePointAllotDO();
			allotRecoed.setAttendCount(gamePointAllotParam.getAttendCount());
			allotRecoed.setWinNum(gamePointAllotParam.getWinNum());
			allotRecoed.setStatus(StatusEnum.VALID.getValue());
			allotRecoed.setGameType(GameTypeEnum.PUZZLE.getVal());
			allotRecoed.setRankPoint(JSONObject.toJSONString(gamePointAllotParam.getRankPoint()));
			allotRecoed.setRankStar(JSONObject.toJSONString(gamePointAllotParam.getRankStar()));
			gamePointAllotDOMapper.insertSelective(allotRecoed);
		}

		// 难度设置
		List<GameDifficultyParam> difficultys = param.getDifficultys();
		for (GameDifficultyParam gameDifficultyParam : difficultys) {
			GamePuzzleDifficultyDO difficulty = new GamePuzzleDifficultyDO();
			difficulty.setCoefficient(gameDifficultyParam.getCoefficient());
			difficulty.setType(gameDifficultyParam.getLeverEnum().getVal());
			difficulty.setGmtCreate(new Date());
			difficulty.setGmtModified(new Date());
			difficulty.setChallengeTime(gameDifficultyParam.getChallengeTime());
			difficulty.setSecScore(JSONArray.toJSONString(gameDifficultyParam.getSecScore()));
			difficulty.setRobotMinSecond(gameDifficultyParam.getRobotMinSecond());
			difficulty.setRobotMaxSecond(gameDifficultyParam.getRobotMaxSecond());
			gamePuzzleDifficultyDOMapper.insertSelective(difficulty);
		}

	}

	/**
	 * 根据游戏难易程度出拼图图片
	 *
	 * @param param
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<GamePuzzleGetPicBO> getPicByHardLevel(GamePuzzleGetPicSaveAttendParam param) {
		List<GamePuzzleGetPicBO> listReturn =new ArrayList<GamePuzzleGetPicBO>();
		if(!param.getAttendType().equals(AttendTypeEnum.MANYPEOPLE)||(param.getAttendType().equals(AttendTypeEnum.MANYPEOPLE)&&param.getLevel().equals(GameHardLevelEnum.RANDOM))){ //单机、随机
			List<GameHardLevelEnum> list =Arrays.asList(GameHardLevelEnum.SIMPLE,GameHardLevelEnum.COMMONLY,GameHardLevelEnum.DIFFICULTY);
			List<GameHardLevelEnum> ltLevel =new ArrayList<GameHardLevelEnum>();
			for(int i=0;i<param.getPicCount();i++){
				//Collections.shuffle(list,new Random());
				int ramdom = GamePuzzleRandomUtil.percentageRandom(Double.valueOf(gameSrvConfig.getGamePuzzleSimple()),Double.valueOf(gameSrvConfig.getGamePuzzleCommonly()),Double.valueOf(gameSrvConfig.getGamePuzzleDifficulty()));
				ltLevel.add(list.get(ramdom));
			}
			listReturn=getPicInfoAndDifficuSing(param, ltLevel);
		}else{
			listReturn = getPicInfoAndDifficu(param,param.getLevel(),param.getPicCount());
		}
		return listReturn;
	}

	private List<GamePuzzleGetPicBO> getPicInfoAndDifficuSing(GamePuzzleGetPicSaveAttendParam param, List<GameHardLevelEnum> ltLevel) {
		List<GamePuzzleGetPicBO> listReturn= new ArrayList<GamePuzzleGetPicBO>();
		List lt =new ArrayList();
		List<GamePuzzlePicBO> listPicBO=new ArrayList<GamePuzzlePicBO>();
		for(GameHardLevelEnum level :ltLevel){
			GamePuzzlePicBO bop = gamePuzzlePicService.getRandomGamePuzzlePicSimg(level,lt);
			lt.add(bop.getId());
			listPicBO.add(bop);
			GamePuzzleDifficultyBO diffBo = gamePuzzleDifficultyService.getPuzzleDifficultyByHardLevel(level);
			if (null == diffBo) {
				return null;
			}
			GamePuzzleGetPicBO bo = GamePuzzleConfigConverter.convertGamePuzzleGetPicBO(bop, diffBo);
			bo.setLevel(diffBo.getLevel());
			bo.setCanUseTime(diffBo.getChallengeTime());
			bo.setAttendType(param.getAttendType());
			GamePuzzleMaxPointStartByDiffBO maxPoint = gamePuzzleDifficultyService.getPuzzleMaxPointLevel(diffBo.getLevel());
			bo.setScore(maxPoint.getScore());
			listReturn.add(bo);
		}
		return listReturn;
	}

	private List<GamePuzzleGetPicBO> getPicInfoAndDifficu(GamePuzzleGetPicSaveAttendParam param,GameHardLevelEnum level, int k) {
		List<GamePuzzleGetPicBO> listReturn= new ArrayList<GamePuzzleGetPicBO>();
		List<GamePuzzlePicBO> listPicBO = gamePuzzlePicService.getRandomGamePuzzlePic(level,k);
		if (null == listPicBO) {
			return null;
		}
		for(int i=0;i<listPicBO.size();i++){
			GamePuzzleDifficultyBO diffBo = gamePuzzleDifficultyService.getPuzzleDifficultyByHardLevel(level);
			if (null == diffBo) {
				return null;
			}
			GamePuzzleGetPicBO bo = GamePuzzleConfigConverter.convertGamePuzzleGetPicBO(listPicBO.get(i), diffBo);
			bo.setLevel(diffBo.getLevel());
			bo.setCanUseTime(diffBo.getChallengeTime());
			bo.setAttendType(param.getAttendType());
			GamePuzzleMaxPointStartByDiffBO maxPoint = gamePuzzleDifficultyService.getPuzzleMaxPointLevel(diffBo.getLevel());
			bo.setScore(maxPoint.getScore());
			listReturn.add(bo);
		}
		return listReturn;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void setEnable(GameConfigStatusEnum statusEnum) {
		GamePuzzleConfigDO record = new GamePuzzleConfigDO();
		record.setStatus(statusEnum.getVal());
		record.setGmtModified(new Date());
		List<GamePuzzleConfigDO> list = gamePuzzleConfigDOMapper.selectByExample(null);
		record.setId(list.get(0).getId());
		gamePuzzleConfigDOMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateGamePuzzleConfig(Long id, GamePuzzleConfigParam param) {
		GamePuzzleConfigDO record = new GamePuzzleConfigDO();
		record.setId(id);
		record.setAttendPoint(param.getAttendPoint());
		record.setAttendMaxPoint(param.getAttendMaxPoint());
		record.setAwardPoint(param.getAwardPoint());
		record.setAwardStar(param.getAwardStar());
		record.setCountDown(param.getCountDown());
		record.setDeductStar(param.getDeductStar());
		record.setGmtModified(new Date());
		record.setRoomMaxNum(param.getRoomMaxNum());
		record.setShareAttendCount(param.getShareAttendCount());
		record.setStatus(param.getStatusEnum().getVal());
		record.setFreeCount(param.getFreeCount());
		record.setSuccessScore(param.getSuccessScore());
		record.setStatus(param.getStatusEnum().getVal());
		record.setForbiddenRemark(param.getForbiddenRemark());
		record.setPicCount(param.getPicCount());
		record.setRobotStatus(param.getRobotStatus().getValue());
		record.setRobotEffectiveTime(param.getRobotEffectiveTime());
		gamePuzzleConfigDOMapper.updateByPrimaryKeySelective(record);

		// 设置胜利积分分配
		List<GamePointAllotParam> list = param.getAllotList();
		for (GamePointAllotParam gamePointAllotParam : list) {
			GamePointAllotDO allotRecoed = new GamePointAllotDO();
			allotRecoed.setId(gamePointAllotParam.getId());
			allotRecoed.setAttendCount(gamePointAllotParam.getAttendCount());
			allotRecoed.setWinNum(gamePointAllotParam.getWinNum());
			allotRecoed.setRankPoint(JSONObject.toJSONString(gamePointAllotParam.getRankPoint()));
			allotRecoed.setRankStar(JSONObject.toJSONString(gamePointAllotParam.getRankStar()));
			gamePointAllotDOMapper.updateByPrimaryKeySelective(allotRecoed);
		}

		// 难度设置
		List<GameDifficultyParam> difficultys = param.getDifficultys();
		for (GameDifficultyParam gameDifficultyParam : difficultys) {
			GamePuzzleDifficultyDOExample example = new GamePuzzleDifficultyDOExample();
			example.createCriteria().andTypeEqualTo(gameDifficultyParam.getLeverEnum().getVal());
			GamePuzzleDifficultyDO difficulty = new GamePuzzleDifficultyDO();
			difficulty.setCoefficient(gameDifficultyParam.getCoefficient());
			difficulty.setType(gameDifficultyParam.getLeverEnum().getVal());
			difficulty.setGmtModified(new Date());
			difficulty.setChallengeTime(gameDifficultyParam.getChallengeTime());
			difficulty.setSecScore(JSONArray.toJSONString(gameDifficultyParam.getSecScore()));
			difficulty.setRobotMinSecond(gameDifficultyParam.getRobotMinSecond());
			difficulty.setRobotMaxSecond(gameDifficultyParam.getRobotMaxSecond());
			gamePuzzleDifficultyDOMapper.updateByExampleSelective(difficulty, example);
		}

	}

	/**
	 * 
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public GameAttendSaveReturnBO saveRandomPuzzleGameAttendInfo(GameAttendSaveParam param) {
		String attendNum = param.getAttendNum();
		if(null==attendNum || attendNum.equals("")){
			attendNum = IdWorkerHelperImpl.generate(BizIdType.PUZZLE);
		}
		GamePuzzleAttendRecordDOExample example =new GamePuzzleAttendRecordDOExample();
		example.createCriteria().andUserNumEqualTo(param.getUserNum()).andAttendNumEqualTo(attendNum);
		List<GamePuzzleAttendRecordDO> list = gamePuzzleAttendRecordDOMapper.selectByExample(example);
		if(list.size()>0){
			GameAttendSaveReturnBO bo = new GameAttendSaveReturnBO();
			bo.setAttendNum(attendNum);
			bo.setFlag(true);
			return bo;
		}
		if(null != param.getUserNums()&&param.getUserNums().size()>0){
			for (int i = 0; i < param.getUserNums().size(); i++) {
				GamePuzzleAttendRecordBO attendBo =GamePuzzleConfigConverter.convertRandomGamePuzzleAttendRecordBO(param,param.getUserNums().get(i),attendNum);
				//单机不扣积分么有免费次数，只扣星星，随机有免费次数，扣免费次数，扣星星，多人没有免费次数
				if(param.getAttendType()==AttendTypeEnum.STANDALONE){//单人
					attendBo.setStatus(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
					gamePuzzleAttendRecordService.savePuzzleAttendRecord(attendBo);
					List<GameAccountAllotParam> listAllot =new ArrayList<>();
					GameAccountStarParam gameAccount =new GameAccountStarParam();
					gameAccount.setAttendNum(attendBo.getAttendNum());
					gameAccount.setGameType(GameTypeEnum.PUZZLE);
					gameAccount.setRecordEnum(GameAttendRecordStatusEnum.ATTENDSUCCESS);
					GameAccountAllotParam allotParam = new GameAccountAllotParam();
					allotParam.setStar(attendBo.getAttendStar());
					allotParam.setUserNum(attendBo.getUserNum());
					listAllot.add(allotParam);
					gameAccount.setList(listAllot);
					gameAccountService.dealStar(gameAccount);
				}else if(param.getAttendType()==AttendTypeEnum.RANDOM){ //随机
					boolean free = gameAccountService.getShareCount(attendBo.getUserNum(),GameTypeEnum.PUZZLE);
					boolean flag = gameRobotAccountService.checkMemberIsRobot(attendBo.getUserNum()); //判断是否是机器人
					if(free){
						attendBo.setStatus(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
						if(flag){
							attendBo.setStatus(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
							gamePuzzleAttendRecordService.savePuzzleAttendRecord(attendBo);//初始化记录
						}else{
							gamePuzzleAttendRecordService.savePuzzleAttendRecord(attendBo);//初始化记录
							gameAccountService.reduceFreeCount(attendBo.getUserNum(),GameTypeEnum.PUZZLE);//减免费次数
							List<GameAccountAllotParam> listAllot =new ArrayList<>();
							GameAccountStarParam gameAccount =new GameAccountStarParam();
							gameAccount.setAttendNum(attendBo.getAttendNum());
							gameAccount.setGameType(GameTypeEnum.PUZZLE);
							gameAccount.setRecordEnum(GameAttendRecordStatusEnum.ATTENDSUCCESS);
							GameAccountAllotParam allotParam = new GameAccountAllotParam();
							allotParam.setStar(attendBo.getAttendStar());
							allotParam.setUserNum(attendBo.getUserNum());
							listAllot.add(allotParam);
							gameAccount.setList(listAllot);
							logger.info("参与扣除用户"+attendBo.getUserNum()+"        "+attendBo.getAttendStar()+"   多少个星星");
							gameAccountService.dealStar(gameAccount);
						}
					}else{
						if(flag){
							attendBo.setStatus(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
							gamePuzzleAttendRecordService.savePuzzleAttendRecord(attendBo);//初始化记录
						}else{
							attendBo.setStatus(GameAttendRecordStatusEnum.INITSTATUS.getVal());
							Long id = gamePuzzleAttendRecordService.savePuzzleAttendRecord(attendBo);
							puzzleGameDeductionPointTransactionMainServiceImpl.sendNotice(id);
						}
					}
				}else if(param.getAttendType()==AttendTypeEnum.MANYPEOPLE){
					attendBo.setStatus(GameAttendRecordStatusEnum.INITSTATUS.getVal());
					Long id = gamePuzzleAttendRecordService.savePuzzleAttendRecord(attendBo);
					puzzleGameDeductionPointTransactionMainServiceImpl.sendNotice(id);
				}
			}
		}
		GameAttendSaveReturnBO bo = new GameAttendSaveReturnBO();
		bo.setAttendNum(attendNum);
		bo.setFlag(true);
		return bo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public GamePuzzleResultBO rewardPointAndStar(GamePuzzleRewardPointAndStarParam gameRewardParam) {
		GamePuzzleAttendRecordBO attbo = gamePuzzleAttendRecordService.updatePuzzleAttendRecord(gameRewardParam);
		puzzleGameIncreasePointTransactionMainServiceImpl.sendNotice(attbo.getId());
		GamePuzzleResultBO resultBO =new GamePuzzleResultBO();
		resultBO.setFlag(true);
		resultBO.setResultCode(ResultCode.SUCCESS);
		resultBO.setOver(true);
		resultBO.setGameRank(attbo.getGameRank());
		resultBO.setGameScore(attbo.getGameScore());
		resultBO.setGameStar(attbo.getRewardStar());
		resultBO.setGamePoint(attbo.getRewardPoint());
		return resultBO;
	}
	
	
	@Override
	public GamePuzzleConfigBO getGamePuzzleConfig() {
		List<GamePuzzleConfigDO> configDOS = gamePuzzleConfigDOMapper.selectByExample(null);
		GamePuzzleConfigBO config = GamePuzzleConfigConverter.converterBO(configDOS);
		List<GamePointAllotBO> allots = new ArrayList<GamePointAllotBO>();
		GamePointAllotDOExample example = new GamePointAllotDOExample();
		example.createCriteria().andGameTypeEqualTo(GameTypeEnum.PUZZLE.getVal()).andStatusEqualTo(StatusEnum.VALID.getValue());;
		List<GamePointAllotDO> list = gamePointAllotDOMapper.selectByExample(example);
		for (GamePointAllotDO gamePointAllotDO : list) {
			GamePointAllotBO allot = new GamePointAllotBO();
			allot.setAttendCount(gamePointAllotDO.getAttendCount());
			allot.setId(gamePointAllotDO.getId());
			allot.setRankPoint(gamePointAllotDO.getRankPoint());
			allot.setRankStar(gamePointAllotDO.getRankStar());
			allot.setWinNum(gamePointAllotDO.getWinNum());
			allots.add(allot);
		}
		config.setAllots(allots);
		List<GamePuzzleDifficultyBO> levers = new ArrayList<>();
		List<GamePuzzleDifficultyDO> difficultys =  gamePuzzleDifficultyDOMapper.selectByExample(null);
		for (GamePuzzleDifficultyDO gamePuzzleDifficultyDO : difficultys) {
			GamePuzzleDifficultyBO  difficulty = new GamePuzzleDifficultyBO();
			difficulty.setId(gamePuzzleDifficultyDO.getId());
			difficulty.setCoefficient(gamePuzzleDifficultyDO.getCoefficient());
			difficulty.setLevel(GameHardLevelEnum.getEnum(gamePuzzleDifficultyDO.getType()));
			difficulty.setChallengeTime(gamePuzzleDifficultyDO.getChallengeTime());
			difficulty.setSecScore(gamePuzzleDifficultyDO.getSecScore());
			difficulty.setRobotMinSecond(gamePuzzleDifficultyDO.getRobotMinSecond());
			difficulty.setRobotMaxSecond(gamePuzzleDifficultyDO.getRobotMaxSecond());
			levers.add(difficulty);
		}
		config.setDifficultys(levers);
		return config;
	}
	

}

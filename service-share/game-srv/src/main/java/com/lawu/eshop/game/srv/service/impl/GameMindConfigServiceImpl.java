package com.lawu.eshop.game.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.cache.param.GameMindConfigParam;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.param.GamePointAllotParam;
import com.lawu.eshop.game.srv.bo.GameMindConfigBO;
import com.lawu.eshop.game.srv.bo.GamePointAllotBO;
import com.lawu.eshop.game.srv.converter.GameMindConfigConverter;
import com.lawu.eshop.game.srv.domain.GameMindConfigDO;
import com.lawu.eshop.game.srv.domain.GamePointAllotDO;
import com.lawu.eshop.game.srv.domain.GamePointAllotDOExample;
import com.lawu.eshop.game.srv.mapper.GameMindConfigDOMapper;
import com.lawu.eshop.game.srv.mapper.GamePointAllotDOMapper;
import com.lawu.eshop.game.srv.service.GameConfigCacheService;
import com.lawu.eshop.game.srv.service.GameMindConfigService;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
@Service
public class GameMindConfigServiceImpl implements GameMindConfigService {

	@Autowired
	private GameMindConfigDOMapper gameMindConfigDOMapper;
	
	@Autowired
	private GamePointAllotDOMapper gamePointAllotDOMapper;
	
	@Autowired
	private GameConfigCacheService gameConfigCacheService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveGameMindConfig(GameMindConfigParam param) {
		
		GameMindConfigDO record = new GameMindConfigDO();
		record.setAttendPoint(param.getAttendPoint());
		record.setAttendMaxPoint(param.getAttendMaxPoint());
		record.setAwardPoint(param.getAwardPoint());
		record.setAwardStar(param.getAwardStar());
		record.setCountDown(param.getCountDown());
		record.setDeductStar(param.getDeductStar());
		record.setGmtCreate(new Date());
		record.setGmtModified(new Date());
		record.setLastScoreMultiple(param.getLastScoreMultiple());
		record.setQuestionCount(param.getQuestionCount());
		record.setRoomMaxNum(param.getRoomMaxNum());
		record.setSecScore(JSONArray.toJSONString(param.getSecScore()));
		record.setShareAttendCount(param.getShareAttendCount());
		record.setStatus(GameConfigStatusEnum.ENABLE.getVal());
		record.setFreeCount(param.getFreeCount());
		record.setSuccessScore(param.getSuccessScore());
		record.setForbiddenRemark(param.getForbiddenRemark());
		record.setQuestionSimpleCount(param.getQuestionSimpleCount());
		record.setRobotMinRightCount(param.getRobotMinRightCount());
		record.setRobotMaxRightCount(param.getRobotMaxRightCount());
		record.setRobotStatus(param.getRobotStatus().getValue());
		record.setRobotEffectiveTime(param.getRobotEffectiveTime());
		gameMindConfigDOMapper.insertSelective(record);
		
		//设置胜利积分分配
		List<GamePointAllotParam> list = param.getAllotList();
		for (GamePointAllotParam gamePointAllotParam : list) {
			GamePointAllotDO allotRecoed = new GamePointAllotDO();
			allotRecoed.setAttendCount(gamePointAllotParam.getAttendCount());
			allotRecoed.setWinNum(gamePointAllotParam.getWinNum());
			allotRecoed.setStatus(StatusEnum.VALID.getValue());
			allotRecoed.setGameType(GameTypeEnum.MIND.getVal());
			allotRecoed.setRankPoint(JSONObject.toJSONString(gamePointAllotParam.getRankPoint()));
			allotRecoed.setRankStar(JSONObject.toJSONString(gamePointAllotParam.getRankStar()));
			gamePointAllotDOMapper.insertSelective(allotRecoed);
		}
		
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void setEnable(GameConfigStatusEnum statusEnum) {
		GameMindConfigDO record =new GameMindConfigDO();
		record.setStatus(statusEnum.getVal());
		record.setGmtModified(new Date());
		List<GameMindConfigDO> list = gameMindConfigDOMapper.selectByExample(null);
		record.setId(list.get(0).getId());
		gameMindConfigDOMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateGameMindConfig(Long id, GameMindConfigParam param) {
		GameMindConfigDO record = new GameMindConfigDO();
		record.setId(id);
		record.setAttendPoint(param.getAttendPoint());
		record.setAttendMaxPoint(param.getAttendMaxPoint());
		record.setAwardPoint(param.getAwardPoint());
		record.setAwardStar(param.getAwardStar());
		record.setCountDown(param.getCountDown());
		record.setDeductStar(param.getDeductStar());
		record.setGmtModified(new Date());
		record.setLastScoreMultiple(param.getLastScoreMultiple());
		record.setQuestionCount(param.getQuestionCount());
		record.setRoomMaxNum(param.getRoomMaxNum());
		record.setSecScore(JSONArray.toJSONString(param.getSecScore()));
		record.setShareAttendCount(param.getShareAttendCount());
		record.setFreeCount(param.getFreeCount());
		record.setSuccessScore(param.getSuccessScore());
		record.setStatus(param.getStatusEnum().getVal());
		record.setForbiddenRemark(param.getForbiddenRemark());
		record.setQuestionSimpleCount(param.getQuestionSimpleCount());
		record.setRobotMinRightCount(param.getRobotMinRightCount());
		record.setRobotMaxRightCount(param.getRobotMaxRightCount());
		record.setRobotStatus(param.getRobotStatus().getValue());
		record.setRobotEffectiveTime(param.getRobotEffectiveTime());
		gameMindConfigDOMapper.updateByPrimaryKeySelective(record);
		
		//设置胜利积分分配
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
		
		
	}


	@Override
	public GameMindConfigBO getGameMindConfigById() {
		List<GameMindConfigDO> configDOS = gameMindConfigDOMapper.selectByExample(null);
		GameMindConfigBO config = GameMindConfigConverter.converterBO(configDOS);
		List<GamePointAllotBO> allots = new ArrayList<GamePointAllotBO>();
		GamePointAllotDOExample example = new GamePointAllotDOExample();
		example.createCriteria().andGameTypeEqualTo(GameTypeEnum.MIND.getVal()).andStatusEqualTo(StatusEnum.VALID.getValue());
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
		return config;
	}
	
	@Override
    public GameMindConfigDTO findGameMindConfigFormCache() {
        Result<GameMindConfigDTO> getGameMindConfigResult = gameConfigCacheService.getGameMindConfig();
        return getGameMindConfigResult.getModel();
    }

	@Override
	public void delAllot(Long id) {
		GamePointAllotDO allotRecoed = new GamePointAllotDO();
		allotRecoed.setId(id);
		allotRecoed.setStatus(StatusEnum.INVALID.getValue());
		gamePointAllotDOMapper.updateByPrimaryKeySelective(allotRecoed);
		
	}
	
	
}

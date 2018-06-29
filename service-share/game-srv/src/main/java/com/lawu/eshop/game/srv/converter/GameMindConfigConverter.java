package com.lawu.eshop.game.srv.converter;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.common.constants.EnableEnum;
import com.lawu.eshop.game.dto.GameMindConfigDTO;
import com.lawu.eshop.game.dto.SecScoreDTO;
import com.lawu.eshop.game.srv.bo.GameMindConfigBO;
import com.lawu.eshop.game.srv.domain.GameMindConfigDO;

/**
 * @author zhangyong
 * @date 2018/3/12.
 */
public class GameMindConfigConverter {
    public static GameMindConfigBO converterBO(List<GameMindConfigDO> configDOS) {
    	 GameMindConfigBO configBO = new GameMindConfigBO();
        if (configDOS.isEmpty()) {
            return configBO;
        }
        configBO.setId(configDOS.get(0).getId());
        configBO.setAttendMaxPoint(configDOS.get(0).getAttendMaxPoint());
        configBO.setAttendPoint(configDOS.get(0).getAttendPoint());
        configBO.setAwardPoint(configDOS.get(0).getAwardPoint());
        configBO.setAwardStar(configDOS.get(0).getAwardStar());
        configBO.setCountDown(configDOS.get(0).getCountDown());
        configBO.setDeductStar(configDOS.get(0).getDeductStar());
        configBO.setLastScoreMultiple(configDOS.get(0).getLastScoreMultiple());
        configBO.setQuestionCount(configDOS.get(0).getQuestionCount());
        configBO.setRoomMaxNum(configDOS.get(0).getRoomMaxNum());
        configBO.setSecScore(configDOS.get(0).getSecScore());
        configBO.setShareAttendCount(configDOS.get(0).getShareAttendCount());
        configBO.setStatusEnum(GameConfigStatusEnum.getEnum(configDOS.get(0).getStatus()));
        configBO.setSuccessScore(configDOS.get(0).getSuccessScore());
        configBO.setFreeCount(configDOS.get(0).getFreeCount());
        configBO.setQuestionSimpleCount(configDOS.get(0).getQuestionSimpleCount());
        configBO.setForbiddenRemark(configDOS.get(0).getForbiddenRemark());
        configBO.setRobotMinRightCount(configDOS.get(0).getRobotMinRightCount());
        configBO.setRobotMaxRightCount(configDOS.get(0).getRobotMaxRightCount());
        configBO.setRobotStatus(EnableEnum.getEnum(configDOS.get(0).getRobotStatus()));
        configBO.setRobotEffectiveTime(configDOS.get(0).getRobotEffectiveTime());
        return configBO;
    }
    
    
    
    public static GameMindConfigDTO converterDTO(GameMindConfigBO config) {
    	 GameMindConfigDTO configDTO = new GameMindConfigDTO();
        if (config==null) {
            return configDTO;
        }
        configDTO.setId(config.getId());
        configDTO.setAttendMaxPoint(config.getAttendMaxPoint());
        configDTO.setAttendPoint(config.getAttendPoint());
        configDTO.setAwardPoint(config.getAwardPoint());
        configDTO.setAwardStar(config.getAwardStar());
        configDTO.setCountDown(config.getCountDown());
        configDTO.setDeductStar(config.getDeductStar());
        configDTO.setLastScoreMultiple(config.getLastScoreMultiple());
        configDTO.setQuestionCount(config.getQuestionCount());
        configDTO.setRoomMaxNum(config.getRoomMaxNum());
        configDTO.setSecScores(JSON.parseArray(config.getSecScore(),SecScoreDTO.class));
        configDTO.setShareAttendCount(config.getShareAttendCount());
        configDTO.setStatusEnum(config.getStatusEnum());
        configDTO.setSuccessScore(config.getSuccessScore());
        configDTO.setFreeCount(config.getFreeCount());
        configDTO.setQuestionSimpleCount(config.getQuestionSimpleCount());
        configDTO.setForbiddenRemark(config.getForbiddenRemark());
        configDTO.setRobotMinRightCount(config.getRobotMinRightCount());
        configDTO.setRobotMaxRightCount(config.getRobotMaxRightCount());
        configDTO.setRobotStatus(config.getRobotStatus());
        configDTO.setRobotEffectiveTime(config.getRobotEffectiveTime());
        return configDTO;
    }
    
}

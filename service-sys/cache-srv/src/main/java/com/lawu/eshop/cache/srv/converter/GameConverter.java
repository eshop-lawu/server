package com.lawu.eshop.cache.srv.converter;

import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.cache.dto.GamePuzzleConfigCacheDTO;
import com.lawu.eshop.cache.param.GameMindConfigParam;
import com.lawu.eshop.cache.param.GamePuzzleConfigParam;

/**
 * @author zhangyong
 * @date 2018/3/16.
 */
public class GameConverter {
    public static GameMindConfigDTO converterMindConfig(GameMindConfigParam param) {
        if (param == null) {
            return null;
        }
        GameMindConfigDTO configDTO = new GameMindConfigDTO();
        configDTO.setAllotList(param.getAllotList());
        configDTO.setAttendMaxPoint(param.getAttendMaxPoint());
        configDTO.setAttendPoint(param.getAttendPoint());
        configDTO.setAwardPoint(param.getAwardPoint());
        configDTO.setCountDown(param.getCountDown());
        configDTO.setAwardStar(param.getAwardStar());
        configDTO.setDeductStar(param.getDeductStar());
        configDTO.setFreeCount(param.getFreeCount());
        configDTO.setLastScoreMultiple(param.getLastScoreMultiple());
        configDTO.setQuestionCount(param.getQuestionCount());
        configDTO.setRoomMaxNum(param.getRoomMaxNum());
        configDTO.setSecScore(param.getSecScore());
        configDTO.setShareAttendCount(param.getShareAttendCount());
        configDTO.setSuccessScore(param.getSuccessScore());
        configDTO.setStatusEnum(param.getStatusEnum());
        configDTO.setForbiddenRemark(param.getForbiddenRemark());
        configDTO.setQuestionSimpleCount(param.getQuestionSimpleCount());
        configDTO.setRobotMinRightCount(param.getRobotMinRightCount());
        configDTO.setRobotMaxRightCount(param.getRobotMaxRightCount());
        configDTO.setRobotStatus(param.getRobotStatus());
        configDTO.setRobotEffectiveTime(param.getRobotEffectiveTime());
        return configDTO;
    }

    public static GamePuzzleConfigCacheDTO converterPuzzleConfig(GamePuzzleConfigParam param) {
        if(param == null){
            return null;
        }
        GamePuzzleConfigCacheDTO cacheDTO = new GamePuzzleConfigCacheDTO();
        cacheDTO.setAllotList(param.getAllotList());
        cacheDTO.setAttendMaxPoint(param.getAttendMaxPoint());
        cacheDTO.setAttendPoint(param.getAttendPoint());
        cacheDTO.setAwardPoint(param.getAwardPoint());
        cacheDTO.setCountDown(param.getCountDown());
        cacheDTO.setAwardStar(param.getAwardStar());
        cacheDTO.setDeductStar(param.getDeductStar());
        cacheDTO.setFreeCount(param.getFreeCount());
        cacheDTO.setRoomMaxNum(param.getRoomMaxNum());
        //cacheDTO.setSecScore(param.getSecScore());
        cacheDTO.setShareAttendCount(param.getShareAttendCount());
        cacheDTO.setDifficultys(param.getDifficultys());
        cacheDTO.setPicCount(param.getPicCount());
        cacheDTO.setSuccessScore(param.getSuccessScore());
        cacheDTO.setStatusEnum(param.getStatusEnum());
        cacheDTO.setForbiddenRemark(param.getForbiddenRemark());
        cacheDTO.setRobotStatus(param.getRobotStatus());
        cacheDTO.setRobotEffectiveTime(param.getRobotEffectiveTime());
        return cacheDTO;
    }
}

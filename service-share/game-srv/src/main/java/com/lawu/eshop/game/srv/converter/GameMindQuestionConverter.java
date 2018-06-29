package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.game.constants.GameQuestionLevelEnum;
import com.lawu.eshop.game.dto.GameMindQuestionDTO;
import com.lawu.eshop.game.dto.GameMindQuestionDetailDTO;
import com.lawu.eshop.game.dto.GameRandomQuestionDTO;
import com.lawu.eshop.game.srv.bo.GameMindQuestionBO;
import com.lawu.eshop.game.srv.domain.GameMindQuestionDO;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
public class GameMindQuestionConverter {
	
	
	public static List<GameMindQuestionBO> convertGameMindQuestionBOS(List<GameMindQuestionDO> list) {
		List<GameMindQuestionBO> resouces = new ArrayList<>();
		if (list.isEmpty()) {
			return resouces;
		}
		for (GameMindQuestionDO record : list) {
			GameMindQuestionBO resouce = new GameMindQuestionBO();
			resouce.setAnswers(record.getAnswers());
			resouce.setCategoryName(record.getCategoryName());
			resouce.setId(record.getId());
			resouce.setRightAnswer(record.getRightAnswer());
			resouce.setStatus(GameConfigStatusEnum.getEnum(record.getStatus()));
			resouce.setTitle(record.getTitle());
			resouce.setLeverEnum(GameQuestionLevelEnum.getEnum(record.getDifficultyLevel()));
			resouces.add(resouce);
		}
		return resouces;
    }
	
	
	public static List<GameMindQuestionDTO> convertGameMindQuestionDTOS(List<GameMindQuestionBO> list) {
		List<GameMindQuestionDTO> resouces = new ArrayList<>();
		if (list.isEmpty()) {
			return resouces;
		}
		for (GameMindQuestionBO record : list) {
			GameMindQuestionDTO resouce = new GameMindQuestionDTO();
			resouce.setAnswers(record.getAnswers());
			resouce.setCategoryName(record.getCategoryName());
			resouce.setId(record.getId());
			resouce.setRightAnswer(record.getRightAnswer());
			resouce.setStatus(record.getStatus());
			resouce.setTitle(record.getTitle());
			resouce.setLeverEnum(record.getLeverEnum());
			resouces.add(resouce);
		}
		return resouces;
    }
    
    public static List<GameMindQuestionBO> converter(List<GameMindQuestionDO> questionDOS) {
        if (questionDOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<GameMindQuestionBO> questionBOS = new ArrayList<>();
        GameMindQuestionBO questionBO;
        for (GameMindQuestionDO questionDO : questionDOS) {
            questionBO = new GameMindQuestionBO();
            questionBO.setAnswers(questionDO.getAnswers());
            questionBO.setCategoryName(questionDO.getCategoryName());
            questionBO.setTitle(questionDO.getTitle());
            questionBO.setId(questionDO.getId());
            questionBO.setLeverEnum(GameQuestionLevelEnum.getEnum(questionDO.getDifficultyLevel()));
            questionBOS.add(questionBO);
        }
        return questionBOS;
    }

    public static List<GameRandomQuestionDTO> converterDTOS(List<GameMindQuestionBO> questionBOS) {
        if (questionBOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<GameRandomQuestionDTO> questionDTOS = new ArrayList<>();
        GameRandomQuestionDTO questionDTO;
        for (GameMindQuestionBO questionBO : questionBOS) {
            questionDTO = new GameRandomQuestionDTO();
			questionDTO.setAnswers(JSONArray.parseArray(questionBO.getAnswers(), String.class));
			questionDTO.setCategoryName(questionBO.getCategoryName());
            questionDTO.setTitle(questionBO.getTitle());
			questionDTO.setId(questionBO.getId());
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }
    
    
    public static GameMindQuestionBO convertGameMindQuestionBO(GameMindQuestionDO record) {
		
		GameMindQuestionBO resouce = new GameMindQuestionBO();
		resouce.setAnswers(record.getAnswers());
		resouce.setCategoryName(record.getCategoryName());
		resouce.setId(record.getId());
		resouce.setRightAnswer(record.getRightAnswer());
		resouce.setStatus(GameConfigStatusEnum.getEnum(record.getStatus()));
		resouce.setTitle(record.getTitle());
		resouce.setCategoryId(record.getCategoryId());
		resouce.setLeverEnum(GameQuestionLevelEnum.getEnum(record.getDifficultyLevel()));
		return resouce;
    }
    
    
    public static GameMindQuestionDetailDTO convertGameMindQuestionDTO(GameMindQuestionBO record) {
		
    	GameMindQuestionDetailDTO resouce = new GameMindQuestionDetailDTO();
    	resouce.setAnswers(JSONArray.parseArray(record.getAnswers(), String.class));
		resouce.setCategoryName(record.getCategoryName());
		resouce.setId(record.getId());
		resouce.setRightAnswer(record.getRightAnswer());
		resouce.setStatus(record.getStatus());
		resouce.setTitle(record.getTitle());
		resouce.setCategoryId(record.getCategoryId());
		resouce.setLeverEnum(record.getLeverEnum());
		return resouce;
    }

}

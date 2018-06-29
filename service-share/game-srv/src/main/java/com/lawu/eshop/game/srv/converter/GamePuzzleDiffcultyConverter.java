package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.game.dto.GamePuzzleDifficultyDTO;
import com.lawu.eshop.game.srv.bo.GamePuzzleDifficultyBO;
import com.lawu.eshop.game.srv.domain.GamePuzzleDifficultyDO;

/**
 * @author lihj <br>
 * @date 2018/3/10
 */
public class GamePuzzleDiffcultyConverter {
    public static GamePuzzleDifficultyBO convertBO(GamePuzzleDifficultyDO resouce) {
        if (null == resouce ) {
            return null;
        }
        GamePuzzleDifficultyBO bo = new GamePuzzleDifficultyBO();
        bo.setId(resouce.getId());
        bo.setCoefficient(resouce.getCoefficient());
        bo.setLevel(GameHardLevelEnum.getEnum(resouce.getType()));
        bo.setChallengeTime(resouce.getChallengeTime());
        bo.setGmtCreate(resouce.getGmtCreate());
        bo.setGmtModified(resouce.getGmtModified());
        return bo;
    }
    
    
    public static GamePuzzleDifficultyDTO convertDTO(GamePuzzleDifficultyBO resouce) {
        if (null == resouce ) {
            return null;
        }
        GamePuzzleDifficultyDTO dto = new GamePuzzleDifficultyDTO();
        dto.setId(resouce.getId());
        dto.setPoint(resouce.getPoint());
        dto.setLevel(resouce.getLevel());
        dto.setStar(resouce.getStar());
        return dto;
    }
    
    
    public static List<GamePuzzleDifficultyDTO> convertDTOS(List<GamePuzzleDifficultyBO> resouce) {
    	List<GamePuzzleDifficultyDTO> dtos = new ArrayList<>();
    	if(resouce.isEmpty()){
    		return dtos;
    	}
    	
        for (GamePuzzleDifficultyBO gamePuzzleDifficultyBO : resouce) {
        	GamePuzzleDifficultyDTO dto = convertDTO(gamePuzzleDifficultyBO);
        	dtos.add(dto);
		}
     
        return dtos;
    }
}

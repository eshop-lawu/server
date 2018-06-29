package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.game.constants.GameInformStatusEnum;
import com.lawu.eshop.game.dto.GameInformDTO;
import com.lawu.eshop.game.srv.bo.GameInformBO;
import com.lawu.eshop.game.srv.domain.GameInformDO;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月13日
 */
public class GameInformConverter {
	
	public static List<GameInformBO> convertGameInformBOS(List<GameInformDO> list) {
		List<GameInformBO> resouces = new ArrayList<>();
		if (list.isEmpty()) {
			return resouces;
		}
		for (GameInformDO record : list) {
			GameInformBO resouce = new GameInformBO();
			resouce.setAttendNum(record.getAttendNum());
			resouce.setAuditorId(record.getAuditorId());
			resouce.setAuditorName(record.getAuditorName());
			resouce.setAuditTime(record.getAuditTime());
			resouce.setCheat(record.getCheat());
			resouce.setGameType(GameTypeEnum.getEnum(record.getGameType()));
			resouce.setGmtCreate(record.getGmtCreate());
			resouce.setId(record.getId());
			resouce.setQuestionError(record.getQuestionError());
			resouce.setRemark(record.getRemark());
			resouce.setResultError(record.getResultError());
			resouce.setStatus(GameInformStatusEnum.getEnum(record.getStatus()));
			resouce.setUserNum(record.getUserNum());
			resouces.add(resouce);
		}
		return resouces;
    }
	
	
	public static List<GameInformDTO> convertGameInformDTOS(List<GameInformBO> list) {
		List<GameInformDTO> resouces = new ArrayList<>();
		if (list.isEmpty()) {
			return resouces;
		}
		for (GameInformBO record : list) {
			GameInformDTO resouce = new GameInformDTO();
			resouce.setAttendNum(record.getAttendNum());
			resouce.setAuditorId(record.getAuditorId());
			resouce.setAuditorName(record.getAuditorName());
			resouce.setAuditTime(record.getAuditTime());
			resouce.setCheat(record.getCheat());
			resouce.setGameType(record.getGameType());
			resouce.setGmtCreate(record.getGmtCreate());
			resouce.setId(record.getId());
			resouce.setQuestionError(record.getQuestionError());
			resouce.setRemark(record.getRemark());
			resouce.setResultError(record.getResultError());
			resouce.setStatus(record.getStatus());
			resouce.setUserNum(record.getUserNum());
			resouces.add(resouce);
		}
		return resouces;
    }

}

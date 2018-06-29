package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.game.constants.GameDialRecordStatusEnum;
import com.lawu.eshop.game.dto.GameDialRecordDTO;
import com.lawu.eshop.game.dto.GameDialRecordInfoDTO;
import com.lawu.eshop.game.srv.bo.GameDialRecordBO;
import com.lawu.eshop.game.srv.bo.GameDialRecordInfoBO;
import com.lawu.eshop.game.srv.domain.GameDialRecordDO;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public class GameDialRecordConverter {

    public static GameDialRecordBO converBO(GameDialRecordDO recordDO) {
        if (recordDO == null) {
            return null;
        }

        GameDialRecordBO recordBO = new GameDialRecordBO();
        recordBO.setId(recordDO.getId());
        recordBO.setUserId(recordDO.getUserId());
        recordBO.setUserNum(recordDO.getUserNum());
        recordBO.setUserAccount(recordDO.getUserAccount());
        recordBO.setGameDialId(recordDO.getGameDialId());
        recordBO.setGameDialPrizeId(recordDO.getGameDialPrizeId());
        recordBO.setPrizeName(recordDO.getPrizeName());
        recordBO.setStatus(recordDO.getStatus());
        recordBO.setPayPoint(recordDO.getPayPoint());
        recordBO.setConsigneeName(recordDO.getConsigneeName());
        recordBO.setConsigneeMobile(recordDO.getConsigneeMobile());
        recordBO.setConsigneeAddress(recordDO.getConsigneeAddress());
        recordBO.setGmtModified(recordDO.getGmtModified());
        recordBO.setGmtCreate(recordDO.getGmtCreate());
        return recordBO;
    }

    public static List<GameDialRecordBO> converBOS(List<GameDialRecordDO> recordDOS) {
        List<GameDialRecordBO> recordBOS = new ArrayList<>();
        if (recordDOS == null || recordDOS.isEmpty()) {
            return recordBOS;
        }

        for (GameDialRecordDO recordDO : recordDOS) {
            recordBOS.add(converBO(recordDO));
        }
        return recordBOS;
    }

    public static List<GameDialRecordInfoDTO> converInfoDTOS(List<GameDialRecordInfoBO> InfoBOS) {
        List<GameDialRecordInfoDTO> infoDTOS = new ArrayList<>();
        if (InfoBOS == null || InfoBOS.isEmpty()) {
            return infoDTOS;
        }

        for (GameDialRecordInfoBO infoBO : InfoBOS) {
            GameDialRecordInfoDTO infoDTO = new GameDialRecordInfoDTO();
            infoDTO.setName(infoBO.getName());
            infoDTO.setImgPath(infoBO.getImgPath());
            infoDTO.setId(infoBO.getId());
            infoDTO.setStatusEnum(GameDialRecordStatusEnum.getEnum(infoBO.getStatus()));
            infoDTO.setGmtCreate(infoBO.getGmtCreate());
            infoDTOS.add(infoDTO);
        }
        return infoDTOS;
    }
    
    public static GameDialRecordDTO converDTO(GameDialRecordBO recordBO) {
        if (recordBO == null) {
            return null;
        }

        GameDialRecordDTO recordDTO = new GameDialRecordDTO();
        recordDTO.setId(recordBO.getId());
        recordDTO.setUserId(recordBO.getUserId());
        recordDTO.setUserNum(recordBO.getUserNum());
        recordDTO.setUserAccount(recordBO.getUserAccount());
        recordDTO.setGameDialPrizeId(recordBO.getGameDialPrizeId());
        recordDTO.setStatus(GameDialRecordStatusEnum.getEnum(recordBO.getStatus()));
        recordDTO.setPayPoint(recordBO.getPayPoint());
        recordDTO.setConsigneeName(recordBO.getConsigneeName());
        recordDTO.setConsigneeMobile(recordBO.getConsigneeMobile());
        recordDTO.setConsigneeAddress(recordBO.getConsigneeAddress());
        recordDTO.setGmtCreate(recordBO.getGmtCreate());
        recordDTO.setPrizeName(recordBO.getPrizeName());
        recordDTO.setStatusDes(GameDialRecordStatusEnum.getEnum(recordBO.getStatus()).getName());
        
        return recordDTO;
    }


	public static List<GameDialRecordDTO> converDTOS(List<GameDialRecordBO> records) {
		List<GameDialRecordDTO> dtos = new ArrayList<>();
		if(records.isEmpty()){
			return dtos;
		}
		for (GameDialRecordBO gameDialRecordBO : records) {
			GameDialRecordDTO gameDialRecordDTO =converDTO(gameDialRecordBO);
			dtos.add(gameDialRecordDTO);
		}
		return dtos;
	}

}

package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.game.constants.GameDialStatusEnum;
import com.lawu.eshop.game.dto.GameDialDTO;
import com.lawu.eshop.game.srv.bo.GameDialBO;
import com.lawu.eshop.game.srv.domain.GameDialDO;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public class GameDialConverter {

    public static GameDialBO converBO(GameDialDO dialDO) {
        if (dialDO == null) {
            return null;
        }

        GameDialBO dialBO = new GameDialBO();
        dialBO.setId(dialDO.getId());
        dialBO.setName(dialDO.getName());
        dialBO.setImgPath(dialDO.getImgPath());
        dialBO.setPoint(dialDO.getPoint());
        dialBO.setFreeCount(dialDO.getFreeCount());
        dialBO.setStatus(dialDO.getStatus());
        dialBO.setGmtModified(dialDO.getGmtModified());
        dialBO.setGmtCreate(dialDO.getGmtCreate());
        dialBO.setShareAttendCount(dialDO.getShareAttendCount());
        return dialBO;
    }

    public static List<GameDialBO> converBOS(List<GameDialDO> dialDOS) {
        List<GameDialBO> dialBOS = new ArrayList<>();
        if (dialDOS == null || dialDOS.isEmpty()) {
            return dialBOS;
        }

        for (GameDialDO dialDO : dialDOS) {
            dialBOS.add(converBO(dialDO));
        }

        return dialBOS;
    }

	public static GameDialDTO converDTO(GameDialBO dialBO) {
		GameDialDTO dialDTO = new GameDialDTO();
		if (dialBO == null) {
			return dialDTO;
		}
		dialDTO.setId(dialBO.getId());
		dialDTO.setName(dialBO.getName());
		dialDTO.setImgPath(dialBO.getImgPath());
		dialDTO.setPoint(dialBO.getPoint());
		dialDTO.setFreeCount(dialBO.getFreeCount());
		dialDTO.setStatus(GameDialStatusEnum.getEnum(dialBO.getStatus()));
		dialDTO.setShareAttendCount(dialBO.getShareAttendCount());
		return dialDTO;
	}
	
	
	 public static List<GameDialDTO> converDTOS(List<GameDialBO> dialBOS) {
	        List<GameDialDTO> dialDTOS = new ArrayList<>();
	        if (dialBOS == null || dialBOS.isEmpty()) {
	            return dialDTOS;
	        }

	        for (GameDialBO dialBO : dialBOS) {
	        	dialDTOS.add(converDTO(dialBO));
	        }

	        return dialDTOS;
	    }

}

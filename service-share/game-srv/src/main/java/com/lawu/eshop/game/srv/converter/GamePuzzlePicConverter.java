package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.game.constants.GamePuzzlePicStatusEnum;
import com.lawu.eshop.game.dto.GamePuzzlePicOperatorDTO;
import com.lawu.eshop.game.srv.bo.GamePuzzlePicBO;
import com.lawu.eshop.game.srv.domain.GamePuzzlePicDO;

/**
 * @author meishuquan
 * @date 2018/3/10.
 */
public class GamePuzzlePicConverter {

    public static GamePuzzlePicBO converBO(GamePuzzlePicDO picDO) {
        if (picDO == null) {
            return null;
        }

        GamePuzzlePicBO picBO = new GamePuzzlePicBO();
        picBO.setId(picDO.getId());
        picBO.setUserNum(picDO.getUserNum());
        picBO.setUserNickname(picDO.getUserNickname());
        picBO.setImgPath(picDO.getImgPath());
        picBO.setType(picDO.getType());
        picBO.setIsSimple(picDO.getIsSimple());
        picBO.setIsCommon(picDO.getIsCommon());
        picBO.setIsHard(picDO.getIsHard());
        picBO.setStatus(picDO.getStatus());
        picBO.setGmtModified(picDO.getGmtModified());
        picBO.setGmtCreate(picDO.getGmtCreate());
        return picBO;
    }

    public static List<GamePuzzlePicBO> converBOS(List<GamePuzzlePicDO> picDOS) {
        List<GamePuzzlePicBO> picBOS = new ArrayList<>();
        if (picDOS == null || picDOS.isEmpty()) {
            return picBOS;
        }

        for (GamePuzzlePicDO picDO : picDOS) {
            picBOS.add(converBO(picDO));
        }
        return picBOS;
    }

    public static GamePuzzlePicOperatorDTO converOperatorDTO(GamePuzzlePicBO picBO) {
        if (picBO == null) {
            return null;
        }

        GamePuzzlePicOperatorDTO operatorDTO = new GamePuzzlePicOperatorDTO();
        operatorDTO.setId(picBO.getId());
        operatorDTO.setUserNickname(picBO.getUserNickname());
        operatorDTO.setImgPath(picBO.getImgPath());
        operatorDTO.setIsSimple(picBO.getIsSimple());
        operatorDTO.setIsCommon(picBO.getIsCommon());
        operatorDTO.setIsHard(picBO.getIsHard());
        operatorDTO.setStatusEnum(GamePuzzlePicStatusEnum.getEnum(picBO.getStatus()));
        operatorDTO.setStatusDes(GamePuzzlePicStatusEnum.getEnum(picBO.getStatus()).getName());
        operatorDTO.setGmtCreate(picBO.getGmtCreate());
        String hardLevel = "";
        if (picBO.getIsSimple()) {
            hardLevel = GameHardLevelEnum.SIMPLE.getName() + ",";
        }
        if (picBO.getIsCommon()) {
            hardLevel += GameHardLevelEnum.COMMONLY.getName() + ",";
        }
        if (picBO.getIsHard()) {
            hardLevel += GameHardLevelEnum.DIFFICULTY.getName() + ",";
        }
        if (StringUtils.isNotEmpty(hardLevel)) {
            hardLevel = hardLevel.substring(0, hardLevel.length() - 1);
        }
        operatorDTO.setHardLevel(hardLevel);
        return operatorDTO;
    }

    public static List<GamePuzzlePicOperatorDTO> converOperatorDTOS(List<GamePuzzlePicBO> picBOS) {
        List<GamePuzzlePicOperatorDTO> picDTOS = new ArrayList<>();
        if (picBOS == null || picBOS.isEmpty()) {
            return picDTOS;
        }

        for (GamePuzzlePicBO picBO : picBOS) {
            picDTOS.add(converOperatorDTO(picBO));
        }
        return picDTOS;
    }

}

package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.game.constants.GamePuzzleUserPicStatusEnum;
import com.lawu.eshop.game.dto.GamePuzzleUserPicDTO;
import com.lawu.eshop.game.dto.GamePuzzleUserPicOperatorDTO;
import com.lawu.eshop.game.srv.bo.GamePuzzleUserPicBO;
import com.lawu.eshop.game.srv.domain.GamePuzzleUserPicDO;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
public class GamePuzzleUserPicConverter {

    public static GamePuzzleUserPicBO converBO(GamePuzzleUserPicDO picDO) {
        if (picDO == null) {
            return null;
        }

        GamePuzzleUserPicBO picBO = new GamePuzzleUserPicBO();
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

    public static List<GamePuzzleUserPicBO> converBOS(List<GamePuzzleUserPicDO> picDOS) {
        List<GamePuzzleUserPicBO> picBOS = new ArrayList<>();
        if (picDOS == null || picDOS.isEmpty()) {
            return picBOS;
        }

        for (GamePuzzleUserPicDO picDO : picDOS) {
            picBOS.add(converBO(picDO));
        }
        return picBOS;
    }

    public static GamePuzzleUserPicDTO converDTO(GamePuzzleUserPicBO picBO) {
        if (picBO == null) {
            return null;
        }

        GamePuzzleUserPicDTO picDTO = new GamePuzzleUserPicDTO();
        picDTO.setImgPath(picBO.getImgPath());
        picDTO.setIsSimple(picBO.getIsSimple());
        picDTO.setIsCommon(picBO.getIsCommon());
        picDTO.setIsHard(picBO.getIsHard());
        picDTO.setStatusEnum(GamePuzzleUserPicStatusEnum.getEnum(picBO.getStatus()));
        picDTO.setGmtCreate(picBO.getGmtCreate());
        return picDTO;
    }

    public static List<GamePuzzleUserPicDTO> converDTOS(List<GamePuzzleUserPicBO> picBOS) {
        List<GamePuzzleUserPicDTO> picDTOS = new ArrayList<>();
        if (picBOS == null || picBOS.isEmpty()) {
            return picDTOS;
        }

        for (GamePuzzleUserPicBO picBO : picBOS) {
            picDTOS.add(converDTO(picBO));
        }
        return picDTOS;
    }

    public static List<GamePuzzleUserPicOperatorDTO> converOperatorDTOS(List<GamePuzzleUserPicBO> picBOS) {
        List<GamePuzzleUserPicOperatorDTO> operatorDTOS = new ArrayList<>();
        if (picBOS == null || picBOS.isEmpty()) {
            return operatorDTOS;
        }

        for (GamePuzzleUserPicBO picBO : picBOS) {
            GamePuzzleUserPicOperatorDTO operatorDTO = new GamePuzzleUserPicOperatorDTO();
            operatorDTO.setId(picBO.getId());
            operatorDTO.setUserNickname(picBO.getUserNickname());
            operatorDTO.setImgPath(picBO.getImgPath());
            operatorDTO.setIsSimple(picBO.getIsSimple());
            operatorDTO.setIsCommon(picBO.getIsCommon());
            operatorDTO.setIsHard(picBO.getIsHard());
            operatorDTO.setStatusEnum(GamePuzzleUserPicStatusEnum.getEnum(picBO.getStatus()));
            operatorDTO.setStatusDes(GamePuzzleUserPicStatusEnum.getEnum(picBO.getStatus()).getName());
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
            operatorDTOS.add(operatorDTO);
        }
        return operatorDTOS;
    }

}

package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameRoomPlayerInfoDTO;
import com.lawu.eshop.game.constants.GameRoomStatusEnum;
import com.lawu.eshop.game.dto.GameRoomDTO;
import com.lawu.eshop.game.srv.bo.GameRoomBO;
import com.lawu.eshop.game.srv.domain.GameRoomDO;

/**
 * @author meishuquan
 * @date 2018/3/13.
 */
public class GameRoomConverter {

    public static GameRoomBO converBO(GameRoomDO roomDO) {
        if (roomDO == null) {
            return null;
        }

        GameRoomBO roomBO = new GameRoomBO();
        roomBO.setId(roomDO.getId());
        roomBO.setUserNum(roomDO.getUserNum());
        roomBO.setAccount(roomDO.getAccount());
        roomBO.setRoomNum(roomDO.getRoomNum());
        roomBO.setPlayerInfo(roomDO.getPlayerInfo());
        roomBO.setPoint(roomDO.getPoint());
        roomBO.setPwd(roomDO.getPwd());
        roomBO.setType(roomDO.getType());
        roomBO.setStatus(roomDO.getStatus());
        roomBO.setHardLevel(roomDO.getHardLevel());
        roomBO.setGmtModified(roomDO.getGmtModified());
        roomBO.setGmtCreate(roomDO.getGmtCreate());
        return roomBO;
    }

    public static List<GameRoomBO> converBOS(List<GameRoomDO> roomDOS) {
        List<GameRoomBO> roomBOS = new ArrayList<>();
        if (roomDOS == null || roomDOS.isEmpty()) {
            return roomBOS;
        }

        for (GameRoomDO roomDO : roomDOS) {
            roomBOS.add(converBO(roomDO));
        }
        return roomBOS;
    }

    public static GameRoomDTO converDTO(GameRoomBO roomBO) {
        if (roomBO == null) {
            return null;
        }
        GameRoomDTO roomDTO = new GameRoomDTO();
        roomDTO.setId(roomBO.getId());
        roomDTO.setUserNum(roomBO.getUserNum());
        roomDTO.setAccount(roomBO.getAccount());
        roomDTO.setRoomNum(roomBO.getRoomNum());
        roomDTO.setPlayerInfoDTOS(new ArrayList<>());
        if (StringUtils.isNotEmpty(roomBO.getPlayerInfo())) {
            roomDTO.setPlayerInfoDTOS(JSON.parseArray(roomBO.getPlayerInfo(), GameRoomPlayerInfoDTO.class));
            // 根据加入时间排序
            roomDTO.getPlayerInfoDTOS().sort((o1, o2) -> {
                return o1.getJoinTime().compareTo(o2.getJoinTime());
            });
        }
        roomDTO.setPoint(roomBO.getPoint());
        roomDTO.setIsPwd(StringUtils.isNotEmpty(roomBO.getPwd()));
        roomDTO.setTypeEnum(GameTypeEnum.getEnum(roomBO.getType()));
        roomDTO.setStatusEnum(GameRoomStatusEnum.getEnum(roomBO.getStatus()));
        roomDTO.setPwd(roomBO.getPwd());
        if (roomDTO.getTypeEnum().equals(GameTypeEnum.PUZZLE)) {
            roomDTO.setHardLevelEnum(GameHardLevelEnum.getEnum(roomBO.getHardLevel()));
        }
        return roomDTO;
    }

    public static List<GameRoomDTO> converDTOS(List<GameRoomBO> roomBOS) {
        List<GameRoomDTO> roomDTOS = new ArrayList<>();
        if (roomBOS == null || roomBOS.isEmpty()) {
            return roomDTOS;
        }

        for (GameRoomBO roomBO : roomBOS) {
            roomDTOS.add(converDTO(roomBO));
        }
        return roomDTOS;
    }

}

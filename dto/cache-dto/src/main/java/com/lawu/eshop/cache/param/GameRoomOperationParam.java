package com.lawu.eshop.cache.param;

import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameRoomPlayerInfoDTO;

/**
 * 房间内操作参数
 * 
 * @author jiangxinjun
 * @createDate 2018年3月16日
 * @updateDate 2018年3月16日
 */
public class GameRoomOperationParam {
    
    /**
     * 当前用户编号
     */
    private String currentUserNum;
    
    /**
     * 游戏类型
     */
    private GameTypeEnum gameType;
    
    /**
     * 房间编号
     */
    private String roomNum;
    
    /**
     * 玩家信息
     */
    private GameRoomPlayerInfoDTO playerInfo;

    public String getCurrentUserNum() {
        return currentUserNum;
    }

    public void setCurrentUserNum(String currentUserNum) {
        this.currentUserNum = currentUserNum;
    }

    public GameTypeEnum getGameType() {
        return gameType;
    }

    public void setGameType(GameTypeEnum gameType) {
        this.gameType = gameType;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public GameRoomPlayerInfoDTO getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(GameRoomPlayerInfoDTO playerInfo) {
        this.playerInfo = playerInfo;
    }
    
}

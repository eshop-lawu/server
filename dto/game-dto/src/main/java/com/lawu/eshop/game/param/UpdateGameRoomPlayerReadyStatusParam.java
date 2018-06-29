package com.lawu.eshop.game.param;

import com.lawu.eshop.cache.constants.GameTypeEnum;

/**
 * 更新房间内玩家准备状态参数
 * 
 * @author jiangxinjun
 * @createDate 2018年3月17日
 * @updateDate 2018年3月17日
 */
public class UpdateGameRoomPlayerReadyStatusParam {
    
    /**
     * 房间编号
     */
    private String roomNum;
    
    /**
     * 游戏类型
     */
    private GameTypeEnum gameType;
    
    /**
     * 用户编号
     */
    private String userNum;
    
    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }
    
    public GameTypeEnum getGameType() {
        return gameType;
    }

    public void setGameType(GameTypeEnum gameType) {
        this.gameType = gameType;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

}

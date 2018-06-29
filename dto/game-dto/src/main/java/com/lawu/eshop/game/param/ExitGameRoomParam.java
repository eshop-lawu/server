package com.lawu.eshop.game.param;

import com.lawu.eshop.cache.constants.GameTypeEnum;

/**
 * 退出房间参数
 * 
 * @author jiangxinjun
 * @createDate 2018年3月17日
 * @updateDate 2018年3月17日
 */
public class ExitGameRoomParam {
    
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
     * 用户编号
     */
    private String userNum;
    
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

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    
}

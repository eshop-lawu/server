package com.lawu.eshop.game.robot.param;

import com.lawu.eshop.game.robot.constants.GameHardLevelEnum;

/**
 * 拼图开始游戏参数
 * 
 * @author jiangxinjun
 * @createDate 2018年5月10日
 * @updateDate 2018年5月10日
 */
public class PuzzleStartParam {

    /**
     * 房间编号
     */
    private String roomNum;

    /**
     * 需要的积分
     */
    private int costPoint;

    /**
     * 难度等级
     */
    private GameHardLevelEnum gameLevel;

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public int getCostPoint() {
        return costPoint;
    }

    public void setCostPoint(int costPoint) {
        this.costPoint = costPoint;
    }

    public GameHardLevelEnum getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(GameHardLevelEnum gameLevel) {
        this.gameLevel = gameLevel;
    }

}

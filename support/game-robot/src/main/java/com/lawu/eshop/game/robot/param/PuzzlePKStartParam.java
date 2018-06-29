package com.lawu.eshop.game.robot.param;

import com.lawu.eshop.game.robot.constants.AttendTypeEnum;
import com.lawu.eshop.game.robot.constants.GameHardLevelEnum;

public class PuzzlePKStartParam {

    /**
     * 参与类型
     */
    private AttendTypeEnum attendType;

    /**
     * 游戏难度
     */
    private GameHardLevelEnum gameLevel;

    /**
     * 需要的积分
     */
    private int costPoint;

    public AttendTypeEnum getAttendType() {
        return attendType;
    }

    public void setAttendType(AttendTypeEnum attendType) {
        this.attendType = attendType;
    }

    public GameHardLevelEnum getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(GameHardLevelEnum gameLevel) {
        this.gameLevel = gameLevel;
    }

    public int getCostPoint() {
        return costPoint;
    }

    public void setCostPoint(int costPoint) {
        this.costPoint = costPoint;
    }
}

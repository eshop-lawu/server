package com.lawu.eshop.game.robot.param;

import com.lawu.eshop.game.robot.constants.AttendTypeEnum;

/**
 * 拼图开始检查
 * 
 * @author jiangxinjun
 * @createDate 2018年5月10日
 * @updateDate 2018年5月10日
 */
public class GamePuzzleCheckParam {

    /**
     * 参与编号
     */
    private String attendNum;

    /**
     * 参与类型
     */
    private AttendTypeEnum attendType;

    public String getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(String attendNum) {
        this.attendNum = attendNum;
    }

    public AttendTypeEnum getAttendType() {
        return attendType;
    }

    public void setAttendType(AttendTypeEnum attendType) {
        this.attendType = attendType;
    }

}

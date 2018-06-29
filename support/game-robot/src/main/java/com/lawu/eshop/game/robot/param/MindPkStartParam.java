package com.lawu.eshop.game.robot.param;

import com.lawu.eshop.game.robot.constants.AttendTypeEnum;

/**
 * 头脑PK开始参数
 * 
 * @author jiangxinjun
 * @createDate 2018年3月15日
 * @updateDate 2018年3月15日
 */
public class MindPkStartParam {

    /**
     * 参与类型
     */
    private AttendTypeEnum attendType;

    public AttendTypeEnum getAttendType() {
        return attendType;
    }

    public void setAttendType(AttendTypeEnum attendType) {
        this.attendType = attendType;
    }
    
}

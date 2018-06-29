package com.lawu.eshop.game.param;

import com.lawu.eshop.game.constants.AttendTypeEnum;

/**
 * 参与头脑PK参数
 * 
 * @author jiangxinjun
 * @createDate 2018年3月13日
 * @updateDate 2018年3月13日
 */
public class ParticipateGameMindParam {

    /**
     * 参与类型
     */
    private AttendTypeEnum attendType;
    
    /**
     * 参与编号
     */
    private String groupNum;
    
    public AttendTypeEnum getAttendType() {
        return attendType;
    }

    public void setAttendType(AttendTypeEnum attendType) {
        this.attendType = attendType;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }
    
}

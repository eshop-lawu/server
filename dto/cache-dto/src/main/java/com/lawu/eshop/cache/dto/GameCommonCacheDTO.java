package com.lawu.eshop.cache.dto;

import java.io.Serializable;

public class GameCommonCacheDTO implements Serializable {

    private static final long serialVersionUID = 6466271786522714324L;

    private String masterUserNum;

    private String otherUserNum;

    private String attendNum;

    /**
     * 机器人用户编号
     */
    private String robotUserNum;

    public String getMasterUserNum() {
        return masterUserNum;
    }

    public void setMasterUserNum(String masterUserNum) {
        this.masterUserNum = masterUserNum;
    }

    public String getOtherUserNum() {
        return otherUserNum;
    }

    public void setOtherUserNum(String otherUserNum) {
        this.otherUserNum = otherUserNum;
    }

    public String getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(String attendNum) {
        this.attendNum = attendNum;
    }

    public String getRobotUserNum() {
        return robotUserNum;
    }

    public void setRobotUserNum(String robotUserNum) {
        this.robotUserNum = robotUserNum;
    }

}

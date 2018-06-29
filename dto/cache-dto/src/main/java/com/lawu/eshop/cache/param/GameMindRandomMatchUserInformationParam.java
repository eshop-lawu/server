package com.lawu.eshop.cache.param;

/**
 * 头脑PK对战用户信息
 * 
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月14日
 */
public class GameMindRandomMatchUserInformationParam {
    
    /**
     * 用户编号
     */
    private String userNum;
    
    /**
     * 参与编号
     */
    private String attendNum;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(String attendNum) {
        this.attendNum = attendNum;
    }
    
}

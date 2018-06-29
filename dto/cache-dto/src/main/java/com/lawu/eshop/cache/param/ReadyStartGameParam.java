package com.lawu.eshop.cache.param;

/**
 * 随机匹配上线准备开始游戏参数
 * @author jiangxinjun
 * @createDate 2018年3月24日
 * @updateDate 2018年3月24日
 */
public class ReadyStartGameParam {
    
    /**
     * 用户编号
     */
    private String userNum;
    
    /**
     * 是否是房主
     */
    private Boolean isHomeowner;
    
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

    public Boolean getIsHomeowner() {
        return isHomeowner;
    }

    public void setIsHomeowner(Boolean isHomeowner) {
        this.isHomeowner = isHomeowner;
    }

    public String getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(String attendNum) {
        this.attendNum = attendNum;
    }
    
}

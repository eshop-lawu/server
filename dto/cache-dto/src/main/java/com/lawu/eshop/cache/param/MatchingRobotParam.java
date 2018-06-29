package com.lawu.eshop.cache.param;

import java.util.List;

import com.lawu.eshop.cache.constants.CacheGameTypeEnum;

/**
 * 匹配机器人参数
 * @author jiangxinjun
 * @createDate 2018年5月11日
 * @updateDate 2018年5月11日
 */
public class MatchingRobotParam {
    
    /**
     * 用户与机器人的用户编号,用户在第一个
     */
    private List<String> userNums;
    
    /**
     * 参与编号
     */
    private String attendNum;
    
    /**
     * 游戏类型
     */
    private CacheGameTypeEnum type;

    public List<String> getUserNums() {
        return userNums;
    }

    public void setUserNums(List<String> userNums) {
        this.userNums = userNums;
    }

    public String getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(String attendNum) {
        this.attendNum = attendNum;
    }

    public CacheGameTypeEnum getType() {
        return type;
    }

    public void setType(CacheGameTypeEnum type) {
        this.type = type;
    }
    
}

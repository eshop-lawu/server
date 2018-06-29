package com.lawu.eshop.cache.dto;

import java.util.List;

/**
 * 匹配成功返回
 * 
 * @author lihj
 * @Date 2018年3月14日
 */
public class GameMatchResultDTO {

    /**
     * 匹配成功返回参与用户编号
     */
    private List<GameCommonNumDTO> commonInfo;

    private String attendNum;

    /**
     * 匹配开始时间
     */
    private Long matchStartTime;

    public List<GameCommonNumDTO> getCommonInfo() {
        return commonInfo;
    }

    public void setCommonInfo(List<GameCommonNumDTO> commonInfo) {
        this.commonInfo = commonInfo;
    }

    public String getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(String attendNum) {
        this.attendNum = attendNum;
    }

    public Long getMatchStartTime() {
        return matchStartTime;
    }

    public void setMatchStartTime(Long matchStartTime) {
        this.matchStartTime = matchStartTime;
    }

}

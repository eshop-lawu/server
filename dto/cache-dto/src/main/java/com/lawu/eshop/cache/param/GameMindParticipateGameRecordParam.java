package com.lawu.eshop.cache.param;

/**
 * 头脑PK参与记录参数
 * 
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月14日
 */
public class GameMindParticipateGameRecordParam {
    
    /**
     * 用户编号
     */
    private String userNum;
    
    /**
     * 参与记录id
     */
    private Long recordId;
    
    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }
    
}

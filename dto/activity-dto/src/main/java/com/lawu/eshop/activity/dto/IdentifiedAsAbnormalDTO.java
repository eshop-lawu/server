package com.lawu.eshop.activity.dto;

/**
 * 标识异常记录DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年3月1日
 * @updateDate 2018年3月1日
 */
public class IdentifiedAsAbnormalDTO {
    
    /**
     * 邀请用户编号
     */
    private String userNum;
    
    /**
     * 邀请用户账号
     */
    private String account;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    
}

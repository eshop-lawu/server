package com.lawu.eshop.mq.dto.user;

import com.lawu.compensating.transaction.Notification;

/**
 * 用户账号冻结消息
 * 
 * @author jiangxinjun
 * @createDate 2018年3月1日
 * @updateDate 2018年3月1日
 */
public class FreezeAccountNotification extends Notification {

    private static final long serialVersionUID = -1181247583733031808L;

    /**
     * 用户编号
     */
    private String userNum;
    
    /**
     * 是冻结还是解冻
     */
    private Boolean isFreeze;
    
    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Boolean getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(Boolean isFreeze) {
        this.isFreeze = isFreeze;
    }
    
}

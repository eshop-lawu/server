package com.lawu.eshop.mq.dto.user;

import com.lawu.compensating.transaction.Notification;

/**
 * @author yangqh
 * @date 2017/12/28
 */
public class RegHelpRedpacktNotification extends Notification {

    private static final long serialVersionUID = 7609511664323160631L;
    
    //助力来源-活动ID
    private String regOrigin;
    private String attendUserNum;
    private String helpUserNum;
    private String helpUserAccount;
    private String helpUserHeadimg;

    public String getRegOrigin() {
        return regOrigin;
    }

    public void setRegOrigin(String regOrigin) {
        this.regOrigin = regOrigin;
    }

    public String getAttendUserNum() {
        return attendUserNum;
    }

    public void setAttendUserNum(String attendUserNum) {
        this.attendUserNum = attendUserNum;
    }

    public String getHelpUserNum() {
        return helpUserNum;
    }

    public void setHelpUserNum(String helpUserNum) {
        this.helpUserNum = helpUserNum;
    }

    public String getHelpUserAccount() {
        return helpUserAccount;
    }

    public void setHelpUserAccount(String helpUserAccount) {
        this.helpUserAccount = helpUserAccount;
    }

    public String getHelpUserHeadimg() {
        return helpUserHeadimg;
    }

    public void setHelpUserHeadimg(String helpUserHeadimg) {
        this.helpUserHeadimg = helpUserHeadimg;
    }
}

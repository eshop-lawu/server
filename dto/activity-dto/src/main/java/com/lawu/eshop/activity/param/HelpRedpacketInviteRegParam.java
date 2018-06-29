package com.lawu.eshop.activity.param;

/**
 * 注册助力
 *
 * @author yangqh
 * @date 2017年12月28日
 */
public class HelpRedpacketInviteRegParam {

    //助力来源-活动ID
    private String regOrigin;

    private Long attendId;

    private String attendUserNum;

    private String helpUserNum;

    private String helpUserAccount;

    private String helpUserHeadimg;

    public Long getAttendId() {
        return attendId;
    }

    public void setAttendId(Long attendId) {
        this.attendId = attendId;
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

    public String getRegOrigin() {
        return regOrigin;
    }

    public void setRegOrigin(String regOrigin) {
        this.regOrigin = regOrigin;
    }
}

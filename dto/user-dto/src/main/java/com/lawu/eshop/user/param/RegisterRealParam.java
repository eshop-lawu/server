package com.lawu.eshop.user.param;

/**
 * @author meishuquan
 * @date 2017/3/31.
 */
public class RegisterRealParam extends RegisterParam {

    //邀请人ID
    private Long inviterId;

    //邀请人编号
    private String userNum;

    //是否是红包助力注册
    private boolean isHelp = false;

    /**
     * APP版本
     */
    private String regAppVer;

    /**
     * 注册系统版本
     */
    private String regPlatformVer;

    /**
     * 注册来源IP
     */
    private String regIp;

    //助力来源-活动ID
    private String regOrigin;

    public Long getInviterId() {
        return inviterId;
    }

    public void setInviterId(Long inviterId) {
        this.inviterId = inviterId;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public boolean isHelp() {
        return isHelp;
    }

    public void setHelp(boolean help) {
        isHelp = help;
    }

    public String getRegAppVer() {
        return regAppVer;
    }

    public void setRegAppVer(String regAppVer) {
        this.regAppVer = regAppVer;
    }

    public String getRegPlatformVer() {
        return regPlatformVer;
    }

    public void setRegPlatformVer(String regPlatformVer) {
        this.regPlatformVer = regPlatformVer;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public String getRegOrigin() {
        return regOrigin;
    }

    public void setRegOrigin(String regOrigin) {
        this.regOrigin = regOrigin;
    }
}

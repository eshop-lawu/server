package com.lawu.eshop.user.param;

/**
 * @author meishuquan
 * @date 2017/9/8.
 */
public class UserLoginLogParam {

    private String userNum;

    private String account;

    private Byte userType;

    private String imei;

    private Byte platform;

    private String platformVer;

    private String appVer;

    private Integer cityId;

    private String channel;

    private String ipAddr;

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

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Byte getPlatform() {
        return platform;
    }

    public void setPlatform(Byte platform) {
        this.platform = platform;
    }

    public String getPlatformVer() {
        return platformVer;
    }

    public void setPlatformVer(String platformVer) {
        this.platformVer = platformVer;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
}

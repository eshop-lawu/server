package com.lawu.eshop.merchant.api.event;

import com.lawu.eshop.user.param.UserLoginLogParam;
import com.lawu.framework.core.event.AsyncEvent;

/**
 * @author zhangyong
 * @date 2017/7/4.
 */
public class LoginEvent extends AsyncEvent {

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

    public LoginEvent(Object source, UserLoginLogParam loginLogParam) {
        super(source);
        this.userNum = loginLogParam.getUserNum();
        this.account = loginLogParam.getAccount();
        this.userType = loginLogParam.getUserType();
        this.imei = loginLogParam.getImei();
        this.platform = loginLogParam.getPlatform();
        this.platformVer = loginLogParam.getPlatformVer();
        this.appVer = loginLogParam.getAppVer();
        this.cityId = loginLogParam.getCityId();
        this.channel = loginLogParam.getChannel();
        this.ipAddr = loginLogParam.getIpAddr();
    }

    public String getUserNum() {
        return userNum;
    }

    public String getAccount() {
        return account;
    }

    public Byte getUserType() {
        return userType;
    }

    public String getImei() {
        return imei;
    }

    public Byte getPlatform() {
        return platform;
    }

    public String getPlatformVer() {
        return platformVer;
    }

    public String getAppVer() {
        return appVer;
    }

    public Integer getCityId() {
        return cityId;
    }

    public String getChannel() {
        return channel;
    }

    public String getIpAddr() {
        return ipAddr;
    }
}

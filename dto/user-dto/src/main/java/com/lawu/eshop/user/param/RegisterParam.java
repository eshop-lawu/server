package com.lawu.eshop.user.param;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/3/23
 */
public class RegisterParam {

    @ApiModelProperty(value = "注册账号", required = true)
    private String account;

    @ApiModelProperty(value = "密码", required = true)
    private String pwd;

    @ApiModelProperty(value = "手机验证码", required = true)
    private String smsCode;

    @ApiModelProperty(value = "邀请人账号")
    private String inviterAccount;

    @ApiModelProperty(value = "邀请类型")
    private UserTypeEnum userTypeEnum;

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getInviterAccount() {
        return inviterAccount;
    }

    public void setInviterAccount(String inviterAccount) {
        this.inviterAccount = inviterAccount;
    }

    public UserTypeEnum getUserTypeEnum() {
        return userTypeEnum;
    }

    public void setUserTypeEnum(UserTypeEnum userTypeEnum) {
        this.userTypeEnum = userTypeEnum;
    }
}

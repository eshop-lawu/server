package com.lawu.eshop.user.param;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

/**
 * @author yangqh
 * @date 2017/12/28
 */
public class RegisterHelpParam {

    @NotBlank(message = "账号不能为空")
    @ApiModelProperty(value = "注册账号", required = true)
    private String account;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String pwd;

    @NotBlank(message = "手机验证码不能为空")
    @ApiModelProperty(value = "手机验证码", required = true)
    private String smsCode;

    @NotBlank(message = "邀请人账号不能为空")
    @ApiModelProperty(value = "邀请人账号", required = true)
    private String inviterAccount;

    @NotNull(message = "邀请类型不能为空")
    @ApiModelProperty(value = "邀请类型", required = true)
    private UserTypeEnum userTypeEnum;

    @ApiModelProperty(value = "活动ID")
    private String regOrigin;

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

    public String getRegOrigin() {
        return regOrigin;
    }

    public void setRegOrigin(String regOrigin) {
        this.regOrigin = regOrigin;
    }
}

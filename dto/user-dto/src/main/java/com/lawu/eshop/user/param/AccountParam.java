package com.lawu.eshop.user.param;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/8/4.
 */
public class AccountParam extends AbstractPageParam {

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "用户类型")
    private UserTypeEnum userType;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }
}

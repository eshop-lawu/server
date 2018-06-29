package com.lawu.eshop.property.param;

import com.lawu.eshop.property.constants.UserTypeEnum;

import javax.validation.constraints.NotNull;

public class AlipayOauthDataParam {
    @NotNull(message = "userTypeEnum不能为空")
    private UserTypeEnum userTypeEnum;

    public UserTypeEnum getUserTypeEnum() {
        return userTypeEnum;
    }

    public void setUserTypeEnum(UserTypeEnum userTypeEnum) {
        this.userTypeEnum = userTypeEnum;
    }
}

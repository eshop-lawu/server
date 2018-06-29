package com.lawu.eshop.user.param;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

public class AliUserInfoDataParam {

    private String userNum;

    private UserTypeEnum userTypeEnum;

    private AliUserInfoParam aliUserInfoParam;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public UserTypeEnum getUserTypeEnum() {
        return userTypeEnum;
    }

    public void setUserTypeEnum(UserTypeEnum userTypeEnum) {
        this.userTypeEnum = userTypeEnum;
    }

    public AliUserInfoParam getAliUserInfoParam() {
        return aliUserInfoParam;
    }

    public void setAliUserInfoParam(AliUserInfoParam aliUserInfoParam) {
        this.aliUserInfoParam = aliUserInfoParam;
    }
}

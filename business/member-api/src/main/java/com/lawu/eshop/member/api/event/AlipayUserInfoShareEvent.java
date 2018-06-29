package com.lawu.eshop.member.api.event;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.user.param.AliUserInfoParam;
import com.lawu.framework.core.event.AsyncEvent;

public class AlipayUserInfoShareEvent extends AsyncEvent {

    private String userNum;

    private UserTypeEnum userTypeEnum;

    private AliUserInfoParam aliUserInfoParam;

    public AlipayUserInfoShareEvent(Object source) {
        super(source);
    }

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

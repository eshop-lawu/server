package com.lawu.eshop.user.param;

import com.lawu.eshop.user.dto.MerchantStatusEnum;

/**
 * @author zhangyong
 * @date 2017/4/15.
 */
public class HandleDepostMessage {
    private String userNum;

    private MerchantStatusEnum statusEnum;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public MerchantStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(MerchantStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}

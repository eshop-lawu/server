package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/12/19.
 */
public class MerchantFreezeInfoDTO {

    @ApiModelProperty(value = "商家账号")
    private String account;

    @ApiModelProperty(value = "商家编号")
    private String userNum;

    @ApiModelProperty(value = "冻结原因")
    private String freezeReason;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getFreezeReason() {
        return freezeReason;
    }

    public void setFreezeReason(String freezeReason) {
        this.freezeReason = freezeReason;
    }
}

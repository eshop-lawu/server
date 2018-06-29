package com.lawu.eshop.user.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/8/4.
 */
public class AccountFreezeParam {

    @ApiModelProperty(required = true, value = "用户编号")
    private String num;

    @ApiModelProperty(value = "是否冻结")
    private Boolean isFreeze;

    @ApiModelProperty(value = "用户账户")
    private String account;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "冻结原因")
    private String freezeReason;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Boolean getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(Boolean freeze) {
        isFreeze = freeze;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFreezeReason() {
        return freezeReason;
    }

    public void setFreezeReason(String freezeReason) {
        this.freezeReason = freezeReason;
    }
}

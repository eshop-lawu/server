package com.lawu.eshop.mall.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/11/24.
 */
public class LotteryInfoDTO {

    @ApiModelProperty(value = "奖品名称")
    private String prizeName;

    @ApiModelProperty(value = "获奖人账号")
    private String account;

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}

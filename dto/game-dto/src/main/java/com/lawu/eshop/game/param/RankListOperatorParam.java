package com.lawu.eshop.game.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/3/10.
 */
public class RankListOperatorParam extends AbstractPageParam{

    @ApiModelProperty(value = "月份")
    private  String month;

    @ApiModelProperty(value = "手机账号")
    private String account;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}

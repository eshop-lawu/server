package com.lawu.eshop.mall.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * 传值给mapper
 * Created by Administrator on 2017/3/30.
 */
public class MessageQueryParam extends AbstractPageParam {
    @ApiModelProperty(value = "用户编号")
    private String userNum;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
}

package com.lawu.eshop.property.param;

import io.swagger.annotations.ApiModelProperty;

/**
 *
 */
public class FreezeQueryParam extends FreezeParam{

    @ApiModelProperty(value = "编号")
    private String userNum;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

}

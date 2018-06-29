package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/14.
 */
public class RongYunRefreshDTO {
    // 返回码，200 为正常。
    @ApiModelProperty(value = "返回码，200 为正常")
    Integer code;
    // 错误信息。
    @ApiModelProperty(value = "错误信息")
    String errorMessage;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

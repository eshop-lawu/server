package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/14.
 */
public class RongYunTokenDTO {

    @ApiModelProperty(value = "融云用户Token")
    private String ryToken;

    @ApiModelProperty(value = "返回码，200 为正常")
    private Integer code;

    @ApiModelProperty(value = "融云用户ID,APP内用户编号")
    private String userId;

    // 错误信息。
    @ApiModelProperty(value = "错误信息")
    private String errorMessage;

    public String getRyToken() {
        return ryToken;
    }

    public void setRyToken(String ryToken) {
        this.ryToken = ryToken;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

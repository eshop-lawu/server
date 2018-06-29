package com.lawu.eshop.user.dto;

import com.lawu.eshop.user.constants.RongOnlineStatusEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/14.
 */
public class RongYunOnlineDTO {
    // 返回码，200 为正常。
    @ApiModelProperty(value = "返回码，200 为正常")
    private Integer code;
    // 在线状态，1为在线，0为不在线。
    @ApiModelProperty(value = "在线状态，STATUS_ONLINE为在线，STATUS_OFFLINE 为不在线")
    private RongOnlineStatusEnum status;
    // 错误信息。
    @ApiModelProperty(value = "错误信息")
    private String errorMessage;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public RongOnlineStatusEnum getStatus() {
        return status;
    }

    public void setStatus(RongOnlineStatusEnum status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/5/22.
 */
public class RongYunHistoryMessageDTO {
    // 返回码，200 为正常。
    @ApiModelProperty(value = "返回码，200 为正常")
    Integer code;
    // 历史消息下载地址。
    @ApiModelProperty(value = "历史消息下载地址")
    String url;
    // 历史记录时间。（yyyymmddhh）
    @ApiModelProperty(value = "历史记录时间。（yyyymmddhh）")
    String date;
    // 错误信息。
    @ApiModelProperty(value = "错误信息")
    String errorMessage;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

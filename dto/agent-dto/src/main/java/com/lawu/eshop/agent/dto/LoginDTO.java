package com.lawu.eshop.agent.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/8/9.
 */
public class LoginDTO {

    @ApiModelProperty(value = "用户编号", required = true)
    private String userNum;

    @ApiModelProperty(value = "用户token", required = true)
    private String token;

    @ApiModelProperty(value = "地区路径", required = true)
    private String regionPath;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }
}

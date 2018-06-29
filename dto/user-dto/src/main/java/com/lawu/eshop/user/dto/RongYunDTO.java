package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/5/19.
 */
public class RongYunDTO {

    @ApiModelProperty(value = "用户编号")
    private String userNum;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "头像")
    private String headImg;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}

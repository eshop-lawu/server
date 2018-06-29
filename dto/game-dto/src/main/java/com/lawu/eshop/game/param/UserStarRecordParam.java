package com.lawu.eshop.game.param;

import com.lawu.eshop.game.constants.StarRecordStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/3/9.
 */
public class UserStarRecordParam {

    @ApiModelProperty(value = "用户编号")
    private String userNum;

    @ApiModelProperty(value = "用户账号")
    private String account;

    @ApiModelProperty(value = "星星数量")
    private Integer starCount;

    @ApiModelProperty(value = "INCREASE：增加、REDUCE：减少")
    private StarRecordStatusEnum status;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getStarCount() {
        return starCount;
    }

    public void setStarCount(Integer starCount) {
        this.starCount = starCount;
    }

    public StarRecordStatusEnum getStatus() {
        return status;
    }

    public void setStatus(StarRecordStatusEnum status) {
        this.status = status;
    }
}

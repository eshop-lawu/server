package com.lawu.eshop.game.query;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/16.
 */
public class GameDialRecordUserQuery extends GameDialRecordQuery {

    @ApiModelProperty(value = "用户编号")
    private String userNum;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
}

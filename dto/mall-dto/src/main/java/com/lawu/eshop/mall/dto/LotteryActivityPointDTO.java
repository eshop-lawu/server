package com.lawu.eshop.mall.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/11/28.
 */
public class LotteryActivityPointDTO {

    @ApiModelProperty(value = "抽奖所需积分")
    private Integer point;

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

}

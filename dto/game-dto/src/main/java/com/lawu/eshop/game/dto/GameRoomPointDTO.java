package com.lawu.eshop.game.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/14.
 */
public class GameRoomPointDTO {

    @ApiModelProperty(value = "最少积分")
    private BigDecimal minPoint;

    @ApiModelProperty(value = "最多积分")
    private BigDecimal maxPoint;

    public BigDecimal getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(BigDecimal minPoint) {
        this.minPoint = minPoint;
    }

    public BigDecimal getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(BigDecimal maxPoint) {
        this.maxPoint = maxPoint;
    }
}

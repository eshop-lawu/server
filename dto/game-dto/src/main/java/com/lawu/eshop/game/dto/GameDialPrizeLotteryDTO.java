package com.lawu.eshop.game.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/16.
 */
public class GameDialPrizeLotteryDTO {

    @ApiModelProperty(value = "奖品id")
    private Long id;

    @ApiModelProperty(value = "奖品中奖概率")
    private BigDecimal rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}

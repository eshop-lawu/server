package com.lawu.eshop.game.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/16.
 */
public class TakePartLotteryInfoDTO {

    @ApiModelProperty(value = "抽奖记录id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

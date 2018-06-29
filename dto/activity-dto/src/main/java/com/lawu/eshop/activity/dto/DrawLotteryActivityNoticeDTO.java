package com.lawu.eshop.activity.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
public class DrawLotteryActivityNoticeDTO extends DrawLotteryActivityDTO {

    @ApiModelProperty(value = "中奖信息")
    private List<LotteryInfoDTO> lotteryInfoDTOS;

    public List<LotteryInfoDTO> getLotteryInfoDTOS() {
        return lotteryInfoDTOS;
    }

    public void setLotteryInfoDTOS(List<LotteryInfoDTO> lotteryInfoDTOS) {
        this.lotteryInfoDTOS = lotteryInfoDTOS;
    }
}

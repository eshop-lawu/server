package com.lawu.eshop.activity.dto;

import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/1/30.
 */
public class DrawLotteryActivityRecordDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "抽奖活动id")
    private Long drawLotteryActivityId;

    @ApiModelProperty(value = "奖品名称")
    private String prizeName;

    @ApiModelProperty(value = "抽奖记录状态")
    private DrawLotteryActivityRecordStatusEnum statusEnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDrawLotteryActivityId() {
        return drawLotteryActivityId;
    }

    public void setDrawLotteryActivityId(Long drawLotteryActivityId) {
        this.drawLotteryActivityId = drawLotteryActivityId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public DrawLotteryActivityRecordStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(DrawLotteryActivityRecordStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}

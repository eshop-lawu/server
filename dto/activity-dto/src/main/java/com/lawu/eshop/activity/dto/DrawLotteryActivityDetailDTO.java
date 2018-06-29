package com.lawu.eshop.activity.dto;

import java.util.List;

import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordChannelEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
public class DrawLotteryActivityDetailDTO extends DrawLotteryActivityDTO {

    @ApiModelProperty(value = "距离开始毫秒数")
    private Long startMillisecond;

    @ApiModelProperty(value = "距离结束毫秒数")
    private Long endMillisecond;

    @ApiModelProperty(value = "奖品数量")
    private Integer prizeNumber;

    @ApiModelProperty(value = "免费抽奖次数")
    private Integer freeLotteryCount;

    @ApiModelProperty(value = "兑换积分")
    private Integer point;

    @ApiModelProperty(value = "已参与未抽奖渠道：FREE_LOTTERY--免费，POINT_LOTTERY--积分兑换")
    private DrawLotteryActivityRecordChannelEnum channelEnum;

    @ApiModelProperty(value = "活动奖品")
    private List<DrawLotteryActivityPrizeDTO> activityPrizeDTOS;

    public Long getStartMillisecond() {
        return startMillisecond;
    }

    public void setStartMillisecond(Long startMillisecond) {
        this.startMillisecond = startMillisecond;
    }

    public Long getEndMillisecond() {
        return endMillisecond;
    }

    public void setEndMillisecond(Long endMillisecond) {
        this.endMillisecond = endMillisecond;
    }

    public Integer getPrizeNumber() {
        return prizeNumber;
    }

    public void setPrizeNumber(Integer prizeNumber) {
        this.prizeNumber = prizeNumber;
    }

    public Integer getFreeLotteryCount() {
        return freeLotteryCount;
    }

    public void setFreeLotteryCount(Integer freeLotteryCount) {
        this.freeLotteryCount = freeLotteryCount;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public DrawLotteryActivityRecordChannelEnum getChannelEnum() {
        return channelEnum;
    }

    public void setChannelEnum(DrawLotteryActivityRecordChannelEnum channelEnum) {
        this.channelEnum = channelEnum;
    }

    public List<DrawLotteryActivityPrizeDTO> getActivityPrizeDTOS() {
        return activityPrizeDTOS;
    }

    public void setActivityPrizeDTOS(List<DrawLotteryActivityPrizeDTO> activityPrizeDTOS) {
        this.activityPrizeDTOS = activityPrizeDTOS;
    }
}

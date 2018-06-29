package com.lawu.eshop.activity.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
public class PointLotteryDetailDTO extends PointLotteryInfoDTO {

    @ApiModelProperty(value = "活动介绍图片")
    private List<String> lotteryDetailImages;

    @ApiModelProperty(value = "中奖信息图片")
    private List<String> lotteryResultImages;

    @ApiModelProperty(value = "已参与次数")
    private Integer attentCount;

    public List<String> getLotteryDetailImages() {
        return lotteryDetailImages;
    }

    public void setLotteryDetailImages(List<String> lotteryDetailImages) {
        this.lotteryDetailImages = lotteryDetailImages;
    }

    public List<String> getLotteryResultImages() {
        return lotteryResultImages;
    }

    public void setLotteryResultImages(List<String> lotteryResultImages) {
        this.lotteryResultImages = lotteryResultImages;
    }

    public Integer getAttentCount() {
        return attentCount;
    }

    public void setAttentCount(Integer attentCount) {
        this.attentCount = attentCount;
    }
}

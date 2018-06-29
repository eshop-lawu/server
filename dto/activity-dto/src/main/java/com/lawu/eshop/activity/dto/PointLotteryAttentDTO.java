package com.lawu.eshop.activity.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.activity.constants.PointLotteryActivityOrderStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
public class PointLotteryAttentDTO {

    @ApiModelProperty(value = "抽奖订单id")
    private Long id;

    @ApiModelProperty(value = "抽奖号码")
    private String lotteryNum;

    @ApiModelProperty(value = "抽奖号码数量")
    private Integer lotteryCnt;

    @ApiModelProperty(value = "抽奖订单状态：PENDING--待处理，SUCCESS--成功，FAILURE--积分扣除失败")
    private PointLotteryActivityOrderStatusEnum statusEnum;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "开奖时间")
    private Date lotteryTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLotteryNum() {
        return lotteryNum;
    }

    public void setLotteryNum(String lotteryNum) {
        this.lotteryNum = lotteryNum;
    }

    public Integer getLotteryCnt() {
        return lotteryCnt;
    }

    public void setLotteryCnt(Integer lotteryCnt) {
        this.lotteryCnt = lotteryCnt;
    }

    public PointLotteryActivityOrderStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(PointLotteryActivityOrderStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public Date getLotteryTime() {
        return lotteryTime;
    }

    public void setLotteryTime(Date lotteryTime) {
        this.lotteryTime = lotteryTime;
    }
}

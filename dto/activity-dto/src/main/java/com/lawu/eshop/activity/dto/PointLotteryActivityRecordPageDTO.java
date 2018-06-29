package com.lawu.eshop.activity.dto;

import com.lawu.eshop.activity.constants.PointLotteryActivityRecordStatusEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 积分夺宝活动参与记录分页DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年2月1日
 * @updateDate 2018年2月1日
 */
@ApiModel
public class PointLotteryActivityRecordPageDTO {

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required = true)
    private String userNum;

    /**
     * 抽奖手机号
     */
    @ApiModelProperty(value = "抽奖手机号", required = true)
    private String mobile;

    /**
     * 抽奖号码
     */
    @ApiModelProperty(value = "抽奖号码", required = true)
    private Integer lotteryNum;

    /**
     * 奖品名称
     */
    @ApiModelProperty(value = "奖品名称", required = true)
    private String prizeName;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true)
    private PointLotteryActivityRecordStatusEnum status;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getLotteryNum() {
        return lotteryNum;
    }

    public void setLotteryNum(Integer lotteryNum) {
        this.lotteryNum = lotteryNum;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public PointLotteryActivityRecordStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PointLotteryActivityRecordStatusEnum status) {
        this.status = status;
    }

}
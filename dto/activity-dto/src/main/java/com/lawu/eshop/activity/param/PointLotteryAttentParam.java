package com.lawu.eshop.activity.param;

import java.math.BigDecimal;

import com.lawu.eshop.activity.constants.PointLotteryActivityOrderStatusEnum;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
public class PointLotteryAttentParam {

    private String userNum;

    private String mobile;

    private Long pointLotteryActivityId;

    private Integer attentCount;

    private BigDecimal payPoint;

    private PointLotteryActivityOrderStatusEnum statusEnum;

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

    public Long getPointLotteryActivityId() {
        return pointLotteryActivityId;
    }

    public void setPointLotteryActivityId(Long pointLotteryActivityId) {
        this.pointLotteryActivityId = pointLotteryActivityId;
    }

    public Integer getAttentCount() {
        return attentCount;
    }

    public void setAttentCount(Integer attentCount) {
        this.attentCount = attentCount;
    }

    public BigDecimal getPayPoint() {
        return payPoint;
    }

    public void setPayPoint(BigDecimal payPoint) {
        this.payPoint = payPoint;
    }

    public PointLotteryActivityOrderStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(PointLotteryActivityOrderStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}

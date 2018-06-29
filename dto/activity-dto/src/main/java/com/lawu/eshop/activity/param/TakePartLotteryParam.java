package com.lawu.eshop.activity.param;

import java.math.BigDecimal;

import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordChannelEnum;

/**
 * @author meishuquan
 * @date 2018/1/15.
 */
public class TakePartLotteryParam {

    private Long userId;

    private String userNum;

    private String userAccount;

    private Long drawLotteryActivityId;

    private BigDecimal payPoint;

    private DrawLotteryActivityRecordChannelEnum channelEnum;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Long getDrawLotteryActivityId() {
        return drawLotteryActivityId;
    }

    public void setDrawLotteryActivityId(Long drawLotteryActivityId) {
        this.drawLotteryActivityId = drawLotteryActivityId;
    }

    public BigDecimal getPayPoint() {
        return payPoint;
    }

    public void setPayPoint(BigDecimal payPoint) {
        this.payPoint = payPoint;
    }

    public DrawLotteryActivityRecordChannelEnum getChannelEnum() {
        return channelEnum;
    }

    public void setChannelEnum(DrawLotteryActivityRecordChannelEnum channelEnum) {
        this.channelEnum = channelEnum;
    }
}

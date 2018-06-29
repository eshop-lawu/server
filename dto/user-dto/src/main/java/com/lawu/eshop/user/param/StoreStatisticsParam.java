package com.lawu.eshop.user.param;

import java.math.BigDecimal;

/**
 * 更新门店统计数据，不对外
 *
 * @author meishuquan
 * @date 2017/4/25.
 */
public class StoreStatisticsParam {

    private BigDecimal averageConsumeAmount;

    private BigDecimal averageScore;

    private BigDecimal feedbackRate;

    public BigDecimal getAverageConsumeAmount() {
        return averageConsumeAmount;
    }

    public void setAverageConsumeAmount(BigDecimal averageConsumeAmount) {
        this.averageConsumeAmount = averageConsumeAmount;
    }

    public BigDecimal getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(BigDecimal averageScore) {
        this.averageScore = averageScore;
    }

    public BigDecimal getFeedbackRate() {
        return feedbackRate;
    }

    public void setFeedbackRate(BigDecimal feedbackRate) {
        this.feedbackRate = feedbackRate;
    }
}

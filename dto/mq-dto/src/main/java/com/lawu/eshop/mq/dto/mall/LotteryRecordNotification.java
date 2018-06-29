package com.lawu.eshop.mq.dto.mall;

import com.lawu.compensating.transaction.Notification;

/**
 * @author meishuquan
 * @date 2017/11/23.
 */
public class LotteryRecordNotification extends Notification {

    private static final long serialVersionUID = -6115603871421558312L;

    private Long lotteryActivityId;

    private String userNum;

    public Long getLotteryActivityId() {
        return lotteryActivityId;
    }

    public void setLotteryActivityId(Long lotteryActivityId) {
        this.lotteryActivityId = lotteryActivityId;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
}

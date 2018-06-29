package com.lawu.eshop.mall.param;

/**
 * @author meishuquan
 * @date 2017/11/23.
 */
public class LotteryRecordParam {

    private Long userId;

    private String userNum;

    private String account;

    private Long lotteryActivityId;

    private String prizeName;

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getLotteryActivityId() {
        return lotteryActivityId;
    }

    public void setLotteryActivityId(Long lotteryActivityId) {
        this.lotteryActivityId = lotteryActivityId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

}

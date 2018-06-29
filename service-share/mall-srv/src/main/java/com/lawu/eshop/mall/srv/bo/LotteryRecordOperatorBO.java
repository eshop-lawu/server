package com.lawu.eshop.mall.srv.bo;

/**
 * @author meishuquan
 * @date 2017/11/27.
 */
public class LotteryRecordOperatorBO {

    private Long id;

    private String account;

    private String prizeName;

    private Integer lotteryCount;

    private Boolean lotteryResult;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public Integer getLotteryCount() {
        return lotteryCount;
    }

    public void setLotteryCount(Integer lotteryCount) {
        this.lotteryCount = lotteryCount;
    }

    public Boolean getLotteryResult() {
        return lotteryResult;
    }

    public void setLotteryResult(Boolean lotteryResult) {
        this.lotteryResult = lotteryResult;
    }
}

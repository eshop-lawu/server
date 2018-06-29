package com.lawu.eshop.mall.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/11/27.
 */
public class LotteryRecordOperatorDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "姓名")
    private String nickName;

    @ApiModelProperty(value = "奖品名称")
    private String prizeName;

    @ApiModelProperty(value = "抽奖次数")
    private Integer lotteryCount;

    @ApiModelProperty(value = "抽奖结果")
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

package com.lawu.eshop.property.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author meishuquan
 * @date 2017/5/22.
 */
public class TransactionDetailBackageDTO {

    @ApiModelProperty(value = "账号", required = true)
    private String account;

    @ApiModelProperty(value = "账号类型", required = true)
    private String accountType;

    @ApiModelProperty(value = "交易标题", required = true)
    private String title;

    @ApiModelProperty(value = "支付方式", required = true)
    private String payType;

    @ApiModelProperty(value = "用户编号", required = true)
    private String userNum;

    @ApiModelProperty(value = "金额", required = true)
    private BigDecimal amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "交易时间", required = true)
    private Date transactionDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}

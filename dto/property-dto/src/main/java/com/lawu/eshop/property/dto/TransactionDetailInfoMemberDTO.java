package com.lawu.eshop.property.dto;

import com.lawu.eshop.property.constants.MemberTransactionCategoryEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class TransactionDetailInfoMemberDTO extends TransactionDetailBaseInfoDTO {

    @ApiModelProperty(value = "交易分类")
    private MemberTransactionCategoryEnum transactionCategory;

    @ApiModelProperty(value = "交易类型")
    private MemberTransactionTypeEnum memberTransactionTypeEnum;

    @ApiModelProperty(value = "业务ID")
    private String bizId;

    @ApiModelProperty(value = "订单总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "积分抵扣金额(为0时无积分抵扣)")
    private BigDecimal deductionPointsAmount;

    public MemberTransactionCategoryEnum getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(MemberTransactionCategoryEnum transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public MemberTransactionTypeEnum getMemberTransactionTypeEnum() {
        return memberTransactionTypeEnum;
    }

    public void setMemberTransactionTypeEnum(MemberTransactionTypeEnum memberTransactionTypeEnum) {
        this.memberTransactionTypeEnum = memberTransactionTypeEnum;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getDeductionPointsAmount() {
        return deductionPointsAmount;
    }

    public void setDeductionPointsAmount(BigDecimal deductionPointsAmount) {
        this.deductionPointsAmount = deductionPointsAmount;
    }
}

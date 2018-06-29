package com.lawu.eshop.property.param.foreign;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.property.constants.MemberTransactionCategoryEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 交易明细查询参数
 * 
 * @author jiangxinjun
 * @date 2017年10月20日
 */
@ApiModel
public class TransactionDetailQueryForMemberForeignParam extends AbstractPageParam {
	
    @NotNull(message="交易分类不能为空")
	@ApiModelProperty(value = "交易分类(ALL-全部分类|RED_SWEEP-红包|RECOMMEND_INCOME-推荐E友收益|PREFERRED_RED_SWEEP-优选红包|WITHDRAW-提现|PAY-买单|REFUND_MONEY-退款|RECHARGE-充值|SHOPPING-购物)", required=true)
	private MemberTransactionCategoryEnum transactionCategory;
    
    @JsonFormat(pattern="yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM")
    @ApiModelProperty(value = "交易时间")
    private Date date;
	
    public MemberTransactionCategoryEnum getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(MemberTransactionCategoryEnum transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
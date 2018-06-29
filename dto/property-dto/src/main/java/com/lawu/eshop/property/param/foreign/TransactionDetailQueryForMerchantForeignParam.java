package com.lawu.eshop.property.param.foreign;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.property.constants.MerchantTransactionCategoryEnum;
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
public class TransactionDetailQueryForMerchantForeignParam extends AbstractPageParam {
	
    @NotNull(message="交易分类不能为空")
	@ApiModelProperty(value = "交易分类(ALL-全部分类|RECHARGE-充值|RECOMMEND_INCOME-推荐E友收益|WITHDRAW-提现|PAY_INCOME-买单收入|PRODUCT_INCOME-商品收入|REFUND_MONEY-退款|DUE_IN-待收货款|RED_PACKET-红包)", required=true)
	private MerchantTransactionCategoryEnum transactionCategory;
    
    @JsonFormat(pattern="yyyy-MM")
    @DateTimeFormat(pattern="yyyy-MM")
    @ApiModelProperty(value = "交易时间")
    private Date date;
	
    public MerchantTransactionCategoryEnum getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(MerchantTransactionCategoryEnum transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
package com.lawu.eshop.property.param;

import java.io.Serializable;

import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 交易明细查询参数
 * 
 * @author Sunny
 * @date 2017/3/29
 */
@ApiModel
public class TransactionDetailQueryForMemberParam extends AbstractPageParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "交易类型")
	private MemberTransactionTypeEnum transactionType;

	public MemberTransactionTypeEnum getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(MemberTransactionTypeEnum transactionType) {
		this.transactionType = transactionType;
	}
}
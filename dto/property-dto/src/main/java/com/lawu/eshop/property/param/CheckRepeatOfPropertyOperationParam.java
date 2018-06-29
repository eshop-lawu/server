package com.lawu.eshop.property.param;

import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;

/**
 * 资产操作（余额或积分）判断是否存在重复操作
 */
public class CheckRepeatOfPropertyOperationParam extends BalancePayValidateParam{

	// 用户编号
	private String userNum;
	
	private MemberTransactionTypeEnum memberTransactionTypeEnum;
	private MerchantTransactionTypeEnum merchantTransactionTypeEnum;

	private String bizId;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public MemberTransactionTypeEnum getMemberTransactionTypeEnum() {
		return memberTransactionTypeEnum;
	}

	public void setMemberTransactionTypeEnum(MemberTransactionTypeEnum memberTransactionTypeEnum) {
		this.memberTransactionTypeEnum = memberTransactionTypeEnum;
	}

	public MerchantTransactionTypeEnum getMerchantTransactionTypeEnum() {
		return merchantTransactionTypeEnum;
	}

	public void setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum merchantTransactionTypeEnum) {
		this.merchantTransactionTypeEnum = merchantTransactionTypeEnum;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
}
package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import org.hibernate.validator.constraints.NotBlank;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;

public class NotifyCallBackParam {
	
	@NotBlank(message = "bizFlag不能为空")
	private String bizFlag;
	
	@NotBlank(message = "userNum不能为空")
	private String userNum;
	
	private String sideUserNum;
	
	@NotBlank(message = "body不能为空")
	private String body;
	
	@NotBlank(message = "bizIds不能为空")
	private String bizIds;
	
	//回调金额
	@NotBlank(message = "totalFee不能为空")
	@Pattern(regexp = "^\\d{0,8}\\.{0,1}(\\d{1,2})?$", message = "totalFee格式错误要求数字或小数位不超过2位")
	private String totalFee;
	
	//商户订单号
	@NotBlank(message = "outTradeNo不能为空")
	private String outTradeNo;
	
	//第三方订单号
	@NotBlank(message = "tradeNo不能为空")
	private String tradeNo;
	
	//买家支付宝账号
	@NotBlank(message = "buyerLogonId不能为空")
	private String buyerLogonId;
	
	@NotNull(message = "transactionPayTypeEnum不能为空")
	private TransactionPayTypeEnum transactionPayTypeEnum;
	
	//商家缴纳保证金时需要回调发送消息改门店状态（事务）
	private Long merchantId;

	//用于代理商系统预期统计买单
	private String regionPath;

	private String title;//交易明细后标题
	private String titleMerchant;

	private MemberTransactionTypeEnum memberTransactionTypeEnum;
	private MerchantTransactionTypeEnum merchantTransactionTypeEnum;
	
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	
	public String getBizFlag() {
		return bizFlag;
	}
	public void setBizFlag(String bizFlag) {
		this.bizFlag = bizFlag;
	}
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getBizIds() {
		return bizIds;
	}
	public void setBizIds(String bizIds) {
		this.bizIds = bizIds;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getBuyerLogonId() {
		return buyerLogonId;
	}
	public void setBuyerLogonId(String buyerLogonId) {
		this.buyerLogonId = buyerLogonId;
	}
	public TransactionPayTypeEnum getTransactionPayTypeEnum() {
		return transactionPayTypeEnum;
	}
	public void setTransactionPayTypeEnum(TransactionPayTypeEnum transactionPayTypeEnum) {
		this.transactionPayTypeEnum = transactionPayTypeEnum;
	}
	public String getSideUserNum() {
		return sideUserNum;
	}
	public void setSideUserNum(String sideUserNum) {
		this.sideUserNum = sideUserNum;
	}

	public String getRegionPath() {
		return regionPath;
	}

	public void setRegionPath(String regionPath) {
		this.regionPath = regionPath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleMerchant() {
		return titleMerchant;
	}

	public void setTitleMerchant(String titleMerchant) {
		this.titleMerchant = titleMerchant;
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
}

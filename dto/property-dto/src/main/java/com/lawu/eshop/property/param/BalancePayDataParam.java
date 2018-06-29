package com.lawu.eshop.property.param;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;

import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;

/**
 * 
 * <p>
 * Description: 余额支付参数对象
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月11日 下午5:35:25
 *
 */
public class BalancePayDataParam extends BalancePayParam{

	// 用户编号
	@NotBlank(message = "userNum不能为空")
	private String userNum;
	
	private String sideUserNum;

	// 用户账号
	@NotBlank(message = "account不能为空")
	private String account;
	
	@NotBlank(message = "totalAmount不能为空")
	@Pattern(regexp = "^\\d{1,9}(\\.\\d{1,2})?$", message = "金额错误(要求最大8位整数且保留2位小数)")
	@Max(value=10000000,message="金额错误(要求不能超过10000000元)")
	private String totalAmount;
	
	//订单号
	//@NotBlank(message = "orderNum不能为空")
	private String orderNum;
	
	private MemberTransactionTypeEnum memberTransactionTypeEnum;
	private MerchantTransactionTypeEnum merchantTransactionTypeEnum;
	private String title;
	private String titleMerchant;//买单时用于显示商家测交易明细

	//代理商区域统计，11/1101/110101
	private String regionPath;

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

	public String getSideUserNum() {
		return sideUserNum;
	}

	public void setSideUserNum(String sideUserNum) {
		this.sideUserNum = sideUserNum;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
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
}
package com.lawu.eshop.activity.param;

import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月3日
 */
public class RichPowerTaskRecordParam {
	
	/**
	 * 用户编号
	 */
	private String memberNum;
	
	/**
	 * 任务类型
	 */
	private PowerTaskTypeEnum type;
	
	/**
	 * 购物金额
	 */
	private int shoppingAmount;

	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public PowerTaskTypeEnum getType() {
		return type;
	}

	public void setType(PowerTaskTypeEnum type) {
		this.type = type;
	}

	public int getShoppingAmount() {
		return shoppingAmount;
	}

	public void setShoppingAmount(int shoppingAmount) {
		this.shoppingAmount = shoppingAmount;
	}
	
	
	

}

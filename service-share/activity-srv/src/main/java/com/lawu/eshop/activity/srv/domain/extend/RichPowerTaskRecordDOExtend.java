package com.lawu.eshop.activity.srv.domain.extend;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月3日
 */
public class RichPowerTaskRecordDOExtend {
	
	/**
	 * 用户编号
	 */
	private String memberNum;
	
	/**
	 * 任务类型
	 */
	private Byte type;
	
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


	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public int getShoppingAmount() {
		return shoppingAmount;
	}

	public void setShoppingAmount(int shoppingAmount) {
		this.shoppingAmount = shoppingAmount;
	}
	
	
	

}

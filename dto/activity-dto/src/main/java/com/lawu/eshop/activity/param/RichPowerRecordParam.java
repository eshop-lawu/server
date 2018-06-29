package com.lawu.eshop.activity.param;

import com.lawu.eshop.activity.constants.RichPowerRecordDirectionEnum;
import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

public class RichPowerRecordParam {
	
	/**
	 * 用户编号
	 */
	private String memberNum;
	
	/**
	 * 动力来源
	 */
	private PowerTaskTypeEnum typeEnum;
	
	/**
	 * 动力来源
	 */
	private MerchantPowerTaskTypeEnum merchantTypeEnum;
	
	/**
	 * 增减
	 */
	private RichPowerRecordDirectionEnum directionEnum;
	
	/**
	 * 动力值
	 */
	private int power;
	
	private UserTypeEnum userType;

	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public PowerTaskTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(PowerTaskTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public RichPowerRecordDirectionEnum getDirectionEnum() {
		return directionEnum;
	}

	public void setDirectionEnum(RichPowerRecordDirectionEnum directionEnum) {
		this.directionEnum = directionEnum;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public MerchantPowerTaskTypeEnum getMerchantTypeEnum() {
		return merchantTypeEnum;
	}

	public void setMerchantTypeEnum(MerchantPowerTaskTypeEnum merchantTypeEnum) {
		this.merchantTypeEnum = merchantTypeEnum;
	}

	public UserTypeEnum getUserType() {
		return userType;
	}

	public void setUserType(UserTypeEnum userType) {
		this.userType = userType;
	}
	
	

}

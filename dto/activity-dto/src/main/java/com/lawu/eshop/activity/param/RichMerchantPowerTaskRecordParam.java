package com.lawu.eshop.activity.param;

import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月3日
 */
public class RichMerchantPowerTaskRecordParam {
	
	/**
	 * 商家编号
	 */
	private String merchantNum;
	
	/**
	 * 任务类型
	 */
	private MerchantPowerTaskTypeEnum type;
	
	/**
	 * 充值积分
	 */
	private int point;
	
	/**
	 * 粉丝数量
	 */
	private int fensInviteCount;
	

	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}

	public MerchantPowerTaskTypeEnum getType() {
		return type;
	}

	public void setType(MerchantPowerTaskTypeEnum type) {
		this.type = type;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getFensInviteCount() {
		return fensInviteCount;
	}

	public void setFensInviteCount(int fensInviteCount) {
		this.fensInviteCount = fensInviteCount;
	}

	
	
	
	

}

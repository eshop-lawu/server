/**
 * 
 */
package com.lawu.eshop.ad.param;

/**
 * 红包第三方更新param
 * 
 * @author lihj
 * @date 2017年8月3日
 */
public class UserRedPacketUpdateParam {

	private Long redId;
	/**
	 * 第三方id
	 */
	private String thirdNum;
	
	/**
	 * 支付类型
	 */
	private Byte payType;

	public Long getRedId() {
		return redId;
	}

	public void setRedId(Long redId) {
		this.redId = redId;
	}

	public String getThirdNum() {
		return thirdNum;
	}

	public void setThirdNum(String thirdNum) {
		this.thirdNum = thirdNum;
	}

	public Byte getPayType() {
		return payType;
	}

	public void setPayType(Byte payType) {
		this.payType = payType;
	} 
	
	
}

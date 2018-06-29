package com.lawu.eshop.order.param;

/**
 * 
 * @author Sunny
 * @date 2017年5月4日
 */
public class ShoppingOrderReportDataParam {
	
	/**
	 * 订单完成时间
	 */
	private String gmtDone;
	
	/**
	 * 商家id
	 */
	private Long merchantId;
	
	/**
	 * 类型
	 */
	private Byte flag;

	public String getGmtDone() {
		return gmtDone;
	}

	public void setGmtDone(String gmtDone) {
		this.gmtDone = gmtDone;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Byte getFlag() {
		return flag;
	}

	public void setFlag(Byte flag) {
		this.flag = flag;
	}
	
}

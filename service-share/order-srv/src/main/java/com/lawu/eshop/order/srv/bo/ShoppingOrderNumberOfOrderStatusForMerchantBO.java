package com.lawu.eshop.order.srv.bo;

public class ShoppingOrderNumberOfOrderStatusForMerchantBO {
    
	/**
	 * 待付款数量
	 */
	private Long pendingPaymentCount;
	
	/**
	 * 待发货数量
	 */
	private Long beShippedCount;
	
	/**
	 * 已发货数量
	 */
	private Long toBeReceivedCount;
	
	/**
	 * 退货中数量
	 */
	private Long refundingCount;
	
	public Long getPendingPaymentCount() {
		return pendingPaymentCount;
	}

	public void setPendingPaymentCount(Long pendingPaymentCount) {
		this.pendingPaymentCount = pendingPaymentCount;
	}

	public Long getBeShippedCount() {
		return beShippedCount;
	}

	public void setBeShippedCount(Long beShippedCount) {
		this.beShippedCount = beShippedCount;
	}

	public Long getToBeReceivedCount() {
		return toBeReceivedCount;
	}

	public void setToBeReceivedCount(Long toBeReceivedCount) {
		this.toBeReceivedCount = toBeReceivedCount;
	}

	public Long getRefundingCount() {
		return refundingCount;
	}

	public void setRefundingCount(Long refundingCount) {
		this.refundingCount = refundingCount;
	}
}
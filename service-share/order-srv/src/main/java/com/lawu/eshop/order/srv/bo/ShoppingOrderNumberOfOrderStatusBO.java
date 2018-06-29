package com.lawu.eshop.order.srv.bo;

public class ShoppingOrderNumberOfOrderStatusBO {
    
	/**
	 * 待收货订单的数量
	 */
	private Long pendingPaymentCount;
	
	/**
	 * 待发货数量
	 */
	private Long beShippedCount;
	
	/**
	 * 待收货数量
	 */
	private Long toBeReceivedCount;
	
	/**
	 * 待评价的数量
	 */
	private Long evaluationCount;
	
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

	public Long getEvaluationCount() {
		return evaluationCount;
	}

	public void setEvaluationCount(Long evaluationCount) {
		this.evaluationCount = evaluationCount;
	}

	public Long getRefundingCount() {
		return refundingCount;
	}

	public void setRefundingCount(Long refundingCount) {
		this.refundingCount = refundingCount;
	}
	
}
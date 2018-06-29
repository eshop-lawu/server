package com.lawu.eshop.order.dto.foreign;

import io.swagger.annotations.ApiModelProperty;

public class ShoppingOrderNumberOfOrderStatusForMerchantForeignDTO {
    
	/**
	 * 待付款数量
	 */
	@ApiModelProperty(value = "待付款数量", required = true)
	private Long pendingPaymentCount;
	
	/**
	 * 待发货数量
	 */
	@ApiModelProperty(value = "待发货数量", required = true)
	private Long beShippedCount;
	
	/**
	 * 已发货数量
	 */
	@ApiModelProperty(value = "已发货数量", required = true)
	private Long toBeReceivedCount;
	
	/**
	 * 退货中数量
	 */
	@ApiModelProperty(value = "退货中数量", required = true)
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
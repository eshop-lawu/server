package com.lawu.eshop.order.dto.foreign;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Sunny
 * @date 2017年4月21日
 */
@ApiModel
public class ShoppingOrderNumberOfOrderStatusDTO {
    
	/**
	 * 待收货订单的数量
	 */
	@ApiModelProperty(value = "待支付数量", required = true)
	private Long pendingPaymentCount;
	
	/**
	 * 待发货数量
	 */
	@ApiModelProperty(value = "待发货数量", required = true)
	private Long beShippedCount;
	
	/**
	 * 待收货数量
	 */
	@ApiModelProperty(value = "待收货数量", required = true)
	private Long toBeReceivedCount;
	
	/**
	 * 待评价的数量
	 */
	@ApiModelProperty(value = "待评价的数量", required = true)
	private Long evaluationCount;
	
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
package com.lawu.eshop.order.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.order.constants.RefundStatusEnum;

import io.swagger.annotations.ApiModelProperty;

public class ShoppingRefundProcessDTO {
	
	/**
	 * 退款状态
	 */
	@ApiModelProperty(value = "退款状态|TO_BE_CONFIRMED 待商家确认|FILL_RETURN_ADDRESS 填写退货地址|TO_BE_RETURNED 待退货|TO_BE_REFUNDED 待退款|REFUND_SUCCESSFULLY 退款成功|REFUND_FAILED 退款失败|PLATFORM_INTERVENTION 平台介入")
	private RefundStatusEnum refundStatus;

    /**
     * 操作时间
     */
	@ApiModelProperty(value = "操作时间", required = true)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

	public RefundStatusEnum getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(RefundStatusEnum refundStatus) {
		this.refundStatus = refundStatus;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
}
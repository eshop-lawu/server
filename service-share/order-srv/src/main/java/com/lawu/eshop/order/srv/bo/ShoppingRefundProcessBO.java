package com.lawu.eshop.order.srv.bo;

import java.util.Date;

import com.lawu.eshop.order.constants.RefundStatusEnum;

public class ShoppingRefundProcessBO {
	
    /**
     * 主键
     */
    private Long id;

    /**
     * 退款详情id
     */
    private Long shoppingRefundDetailId;

	/**
	 * 退款状态
	 */
	private RefundStatusEnum refundStatus;

    /**
     * 创建时间
     */
    private Date gmtCreate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShoppingRefundDetailId() {
		return shoppingRefundDetailId;
	}

	public void setShoppingRefundDetailId(Long shoppingRefundDetailId) {
		this.shoppingRefundDetailId = shoppingRefundDetailId;
	}
	
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
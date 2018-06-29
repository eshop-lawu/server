package com.lawu.eshop.mq.dto.product;

import com.lawu.compensating.transaction.Notification;

public class DelProductCommentNotification extends Notification {

	private static final long serialVersionUID = 5828000640541268908L;
	
	private Long productModelId;

	public Long getProductModelId() {
		return productModelId;
	}

	public void setProductModelId(Long productModelId) {
		this.productModelId = productModelId;
	}

    
}

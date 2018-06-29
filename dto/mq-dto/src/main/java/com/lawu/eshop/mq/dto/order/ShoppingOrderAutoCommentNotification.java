package com.lawu.eshop.mq.dto.order;

import com.lawu.compensating.transaction.Notification;

/**
 * 
 * @author Sunny
 * @date 2017/04/06
 */
public class ShoppingOrderAutoCommentNotification extends Notification {

	private static final long serialVersionUID = -1949216168879419578L;

	/**
	 * 购物订单id
	 */
	private Long shoppingOrderItem;
	
    /**
    * 用户ID
    */
    private Long memberId;
    
    /**
     * 商家ID
     */
    private Long merchantId;
	
    /**
    * 商品id
    */
    private Long productId;
    
	/**
	 * 商品型号id
	 */
	private Long productModelId;

	public Long getShoppingOrderItem() {
		return shoppingOrderItem;
	}

	public void setShoppingOrderItem(Long shoppingOrderItem) {
		this.shoppingOrderItem = shoppingOrderItem;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getProductModelId() {
		return productModelId;
	}

	public void setProductModelId(Long productModelId) {
		this.productModelId = productModelId;
	}
	
}

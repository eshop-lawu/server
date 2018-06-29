package com.lawu.eshop.mq.dto.order;

import com.lawu.compensating.transaction.Notification;

/**
 * @author Sunny
 * @date 2017/04/18
 */
public class ShoppingRefundFillReturnAddressRemindNotification extends Notification {
	
	private static final long serialVersionUID = 6723899204000947644L;

	/**
	 * 购物订单项id
	 */
	private Long shoppingOrderItemId;
	
    /**
    * 用户编号
    */
    private String memberNum;
    
    /**
     * 订单图片
     */
    private String imgUrl;
    
	public Long getShoppingOrderItemId() {
		return shoppingOrderItemId;
	}

	public void setShoppingOrderItemId(Long shoppingOrderItemId) {
		this.shoppingOrderItemId = shoppingOrderItemId;
	}

	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	

}

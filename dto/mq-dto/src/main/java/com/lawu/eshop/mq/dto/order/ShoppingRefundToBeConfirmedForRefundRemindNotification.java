package com.lawu.eshop.mq.dto.order;

import java.io.Serializable;

/**
 * @author Sunny
 * @date 2017/04/18
 */
public class ShoppingRefundToBeConfirmedForRefundRemindNotification implements Serializable {

	private static final long serialVersionUID = -5785320348637257261L;

	/**
	 * 购物订单项id
	 */
	private Long shoppingOrderItemId;

	/**
	 * 商家编号
	 */
	private String merchantNum;

	/**
	 * 用户编号
	 */
	private String memberNum;

	/**
	 * 用户昵称
	 */
	private String memberNickname;
	
	/**
	 * 订单对应商品图片
	 */
	private String imgUrl;

	public Long getShoppingOrderItemId() {
		return shoppingOrderItemId;
	}

	public void setShoppingOrderItemId(Long shoppingOrderItemId) {
		this.shoppingOrderItemId = shoppingOrderItemId;
	}

	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}

	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
	
}

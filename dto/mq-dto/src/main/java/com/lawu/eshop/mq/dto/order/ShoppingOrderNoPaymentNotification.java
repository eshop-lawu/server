package com.lawu.eshop.mq.dto.order;

import java.io.Serializable;

/**
 * 
 * @author Sunny
 * @date 2017年5月19日
 */
public class ShoppingOrderNoPaymentNotification implements Serializable {

	private static final long serialVersionUID = -8086632713388881581L;

	/**
    * 订单id
    */
    private Long id;
	
    /**
    * 商家编号
    */
    private String memberNum;
    
    /**
    * 订单编号
    */
    private String orderNum;
    
    /**
     * 订单图片（商品特征图片）
     */
    private String imgUrl;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
}

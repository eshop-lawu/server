package com.lawu.eshop.mq.dto.order;

import java.io.Serializable;

/**
 * 
 * @author Sunny
 * @date 2017年5月19日
 */
public class ShoppingOrderpaymentSuccessfulNotification implements Serializable {

	private static final long serialVersionUID = -5535747216108586477L;

	/**
    * 订单id
    */
    private Long id;
	
    /**
    * 商家编号
    */
    private String merchantNum;
    
    /**
     * 用户编号
     */
     private String memberNum;
    
    /**
    * 订单编号
    */
    private String orderNum;
    
    /**
     * 商品名称
     */
    private String productName;
    
    /**
     * 商品图片
     */
    private String imgUrl;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}

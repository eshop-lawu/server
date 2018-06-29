package com.lawu.eshop.mq.dto.order;

import java.io.Serializable;

/**
 * 撤销退款推送MQ消息
 * 
 * @author jiangxinjun
 * @createDate 2017年12月18日
 * @updateDate 2017年12月18日
 */
public class RevokeRefundRequestNoticeNotification implements Serializable {
	
    private static final long serialVersionUID = 4168490250560818976L;

    /**
	 * 购物订单项id
	 */
	private Long shoppingOrderItemId;
	
    /**
    * 商家编号
    */
    private String merchantNum;
    
    /**
    * 会员昵称
    */
    private String memberNickname;
    
     /**
     * 订单编号
     */
    private String orderNum;
    
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

    public String getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
    
    
    
}

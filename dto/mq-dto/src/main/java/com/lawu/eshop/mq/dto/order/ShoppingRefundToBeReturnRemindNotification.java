package com.lawu.eshop.mq.dto.order;

import java.io.Serializable;

/**
 * 
 * @author jiangxinjun
 * @createDate 2017年4月18日
 * @updateDate 2018年4月26日
 */
public class ShoppingRefundToBeReturnRemindNotification implements Serializable {

    private static final long serialVersionUID = -210207235127834041L;

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
     * 订单编号
     */
    private String orderNum;

    /**
     * 运单编号
     */
    private String waybillNum;

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

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getWaybillNum() {
        return waybillNum;
    }

    public void setWaybillNum(String waybillNum) {
        this.waybillNum = waybillNum;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}

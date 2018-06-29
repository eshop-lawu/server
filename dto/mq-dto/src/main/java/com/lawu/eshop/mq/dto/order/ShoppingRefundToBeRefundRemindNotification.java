package com.lawu.eshop.mq.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author jiangxinjun
 * @createDate 2017年4月18日
 * @updateDate 2018年3月26日
 */
public class ShoppingRefundToBeRefundRemindNotification implements Serializable {

    private static final long serialVersionUID = 3009031345758449193L;

    /**
     * 购物订单项id
     */
    private Long shoppingOrderItemId;

    /**
     * 用户编号
     */
    private String memberNum;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 退还积分
     */
    private BigDecimal point;

    /**
     * 实际退款金额
     */
    private BigDecimal actualAmount;
    
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

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
    

}

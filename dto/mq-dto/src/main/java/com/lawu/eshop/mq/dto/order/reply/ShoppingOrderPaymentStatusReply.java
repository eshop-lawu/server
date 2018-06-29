package com.lawu.eshop.mq.dto.order.reply;

import com.lawu.compensating.transaction.Reply;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;

/**
 * 订单支付回复消息
 * 
 * @author jiangxinjun
 * @createDate 2018年1月12日
 * @updateDate 2018年1月12日
 */
public class ShoppingOrderPaymentStatusReply extends Reply {

    private static final long serialVersionUID = -5155394918256458235L;
    
    /**
     *  用户编号
     */
    private String memberNum;
    
    /**
     * 购物订单id,多个订单id用逗号分隔
     */
    private String shoppingOrderIds;
    
    /**
     * 支付方式
     */
    private TransactionPayTypeEnum paymentMethod;
    
    /**
     * 第三方支付交易号
     */
    private String thirdNumber;

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public String getShoppingOrderIds() {
        return shoppingOrderIds;
    }

    public void setShoppingOrderIds(String shoppingOrderIds) {
        this.shoppingOrderIds = shoppingOrderIds;
    }

    public TransactionPayTypeEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(TransactionPayTypeEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getThirdNumber() {
        return thirdNumber;
    }

    public void setThirdNumber(String thirdNumber) {
        this.thirdNumber = thirdNumber;
    }
    
}

package com.lawu.eshop.mq.dto.order;

import java.util.Date;

import com.lawu.compensating.transaction.Notification;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;

/**
 * @author Sunny
 * @date 2017/04/06
 */
public class ShoppingOrderPaymentsToMerchantNotification extends Notification {

    private static final long serialVersionUID = 4828613333667480720L;

    /**
     * 购物订单id
     */
    private Long shoppingOrderId;

    /**
     * 商家编号
     */
    private String merchantNum;

    /**
     * 用户编号-释放冻结资金时用于更新对应用户等级用
     */
    private String memberNum;

    /**
     * 商家门店区域（省市区id）
     */
    private String merchantStoreRegionPath;

    /**
     * 支付方式
     */
    private TransactionPayTypeEnum paymentMethod;

    private String orderItemProductName;

    //主事务执行时间
    private Date gmtExecute;

    public Long getShoppingOrderId() {
        return shoppingOrderId;
    }

    public void setShoppingOrderId(Long shoppingOrderId) {
        this.shoppingOrderId = shoppingOrderId;
    }

    public String getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum;
    }

    public String getMerchantStoreRegionPath() {
        return merchantStoreRegionPath;
    }

    public void setMerchantStoreRegionPath(String merchantStoreRegionPath) {
        this.merchantStoreRegionPath = merchantStoreRegionPath;
    }

    public TransactionPayTypeEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(TransactionPayTypeEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOrderItemProductName() {
        return orderItemProductName;
    }

    public void setOrderItemProductName(String orderItemProductName) {
        this.orderItemProductName = orderItemProductName;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public Date getGmtExecute() {
        return gmtExecute;
    }

    public void setGmtExecute(Date gmtExecute) {
        this.gmtExecute = gmtExecute;
    }
}

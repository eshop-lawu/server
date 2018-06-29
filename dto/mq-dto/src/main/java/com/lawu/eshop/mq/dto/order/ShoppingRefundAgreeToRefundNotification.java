package com.lawu.eshop.mq.dto.order;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.compensating.transaction.Notification;
import com.lawu.eshop.common.constants.OrderRefundStatusEnum;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;

/**
 * 
 * @author Sunny
 * @date 2017/04/06
 */
public class ShoppingRefundAgreeToRefundNotification extends Notification {

    private static final long serialVersionUID = 7550828875292411964L;

    /**
     * 购物订单id
     */
    private Long shoppingOrderId;

    /**
     * 购物订单项id
     */
    private Long shoppingOrderItemId;

    /**
     * 商家编号
     */
    private String merchantNum;

    /**
     * 会员编号
     */
    private String memberNum;

    /**
     * 用户退款金额
     */
    private BigDecimal actualAmount;
    
    /**
     * 商家冻结资金扣除金额
     */
    private BigDecimal amount;
    
    /**
     * 退款运费
     */
    private BigDecimal freightPrice;

    /**
     * 退还积分
     */
    private BigDecimal point;

    /**
     * 支付方式
     */
    private TransactionPayTypeEnum paymentMethod;

    /**
     * 是否是最后一个订单项
     */
    private Boolean isLast;

    /**
     * 第三方支付交易号
     */
    private String thirdNumber;

    /**
     * 订单是完成状态
     */
    private OrderRefundStatusEnum status;

    /**
     * 退款时，资产模块用于记录交易记录标题
     */
    private String orderItemProdcutName;

    // 主事务执行时间
    private Date gmtExecute;

    public Long getShoppingOrderId() {
        return shoppingOrderId;
    }

    public void setShoppingOrderId(Long shoppingOrderId) {
        this.shoppingOrderId = shoppingOrderId;
    }

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
    
    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public TransactionPayTypeEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(TransactionPayTypeEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public Boolean getIsLast() {
        return isLast;
    }

    public void setIsLast(Boolean isLast) {
        this.isLast = isLast;
    }

    public String getThirdNumber() {
        return thirdNumber;
    }

    public void setThirdNumber(String thirdNumber) {
        this.thirdNumber = thirdNumber;
    }

    public OrderRefundStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderRefundStatusEnum status) {
        this.status = status;
    }

    public String getOrderItemProdcutName() {
        return orderItemProdcutName;
    }

    public void setOrderItemProdcutName(String orderItemProdcutName) {
        this.orderItemProdcutName = orderItemProdcutName;
    }

    public Date getGmtExecute() {
        return gmtExecute;
    }

    public void setGmtExecute(Date gmtExecute) {
        this.gmtExecute = gmtExecute;
    }
}

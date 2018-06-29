package com.lawu.eshop.mq.dto.property;

import com.lawu.compensating.transaction.Notification;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;

/**
 * 资产模块支付购物订单成功，发送给订单模块的数据
 * 
 * @author Sunny
 * @date 2017年4月14日
 */
public class ShoppingOrderPaymentNotification extends Notification {

	private static final long serialVersionUID = 3823541741697498663L;
	

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

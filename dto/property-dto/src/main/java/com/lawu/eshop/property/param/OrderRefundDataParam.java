package com.lawu.eshop.property.param;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.lawu.eshop.common.constants.OrderRefundStatusEnum;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;

/**
 * 
 * <p>
 * Description: 商家同意退款请求srv参数对象
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月14日 下午1:02:20
 *
 */
public class OrderRefundDataParam extends OrderRefundParam {

	// 商家用户编号
	@NotBlank(message = "userNum不能为空")
	private String userNum;

	// 会员用户编号
	@NotBlank(message = "sideUserNum不能为空")
	private String sideUserNum;

	// 商品订单ID
	@NotBlank(message = "orderId不能为空")
	private String orderId;

	// 商家订单中需要退款的订单项ID
	@NotBlank(message = "orderItemIds不能为空")
	private String orderItemIds;

	// 退给用户
	// 退款总金额-如果是多个order_item退款时为item金额汇总
	@NotBlank(message = "refundMoney不能为空")
	@Pattern(regexp = "^\\d{0,8}\\.{0,1}(\\d{1,2})?$", message = "refundMoney格式错误要求数字或小数位不超过2位")
	private String refundMoney;

	//减商家冻结资金
	@Pattern(regexp = "^\\d{0,8}\\.{0,1}(\\d{1,2})?$", message = "refundExtraMoney格式错误要求数字或小数位不超过2位")
	private String refundExtraMoney;

	//订单运费
	@Pattern(regexp = "^\\d{0,8}\\.{0,1}(\\d{1,2})?$", message = "freightMoney格式错误要求数字或小数位不超过2位")
	private String freightMoney;

	// 是否是最后一个order_item退款
	@NotNull(message = "isLast不能为空")
	private boolean isLast;

	// 支付方式
	@NotNull(message = "transactionPayTypeEnum不能为空")
	private TransactionPayTypeEnum transactionPayTypeEnum;

	// 第三方平台订单号
	@NotBlank(message = "tradeNo不能为空")
	private String tradeNo;

	//用于区分确认收货后的退款还是在途中退款，确认收货后退款需要处理冻结资金
	@NotNull(message = "orderRefundStatusEnum不能为空")
	private OrderRefundStatusEnum orderRefundStatusEnum;

	/**
	 * 退款时，资产模块用于记录交易记录标题
	 */
	private String orderItemProdcutName;

	//主事务执行时间
	private Date gmtExecute;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderItemIds() {
		return orderItemIds;
	}

	public void setOrderItemIds(String orderItemIds) {
		this.orderItemIds = orderItemIds;
	}

	public String getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(String refundMoney) {
		this.refundMoney = refundMoney;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	public TransactionPayTypeEnum getTransactionPayTypeEnum() {
		return transactionPayTypeEnum;
	}

	public void setTransactionPayTypeEnum(TransactionPayTypeEnum transactionPayTypeEnum) {
		this.transactionPayTypeEnum = transactionPayTypeEnum;
	}

	public String getSideUserNum() {
		return sideUserNum;
	}

	public void setSideUserNum(String sideUserNum) {
		this.sideUserNum = sideUserNum;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public OrderRefundStatusEnum getOrderRefundStatusEnum() {
		return orderRefundStatusEnum;
	}

	public void setOrderRefundStatusEnum(OrderRefundStatusEnum orderRefundStatusEnum) {
		this.orderRefundStatusEnum = orderRefundStatusEnum;
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

	public String getRefundExtraMoney() {
		return refundExtraMoney;
	}

	public void setRefundExtraMoney(String refundExtraMoney) {
		this.refundExtraMoney = refundExtraMoney;
	}

	public String getFreightMoney() {
		return freightMoney;
	}

	public void setFreightMoney(String freightMoney) {
		this.freightMoney = freightMoney;
	}
}

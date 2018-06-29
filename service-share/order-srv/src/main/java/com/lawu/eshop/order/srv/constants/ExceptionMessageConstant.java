package com.lawu.eshop.order.srv.constants;

/**
 * 异常信息常量类
 * 
 * @author Sunny
 * @date 2017年7月7日
 */
public class ExceptionMessageConstant {
	
	/**  shopping cat **/
	public final static String SHOPPING_CART_DATA_NOT_EXIST = "购物车数据不存在";
	
	public final static String ILLEGAL_OPERATION_SHOPPING_CART = "非法操作购物车数据";
	
	/**  shopping order **/
	public final static String SHOPPING_ORDER_DATA_NOT_EXIST = "购物订单数据不存在";
	
	public final static String ILLEGAL_OPERATION_SHOPPING_ORDER = "非法操作购物订单数据";
	
	public final static String ORDER_IS_NOT_PENDING_PAYMENT_STATUS = "订单当前是不是待支付状态";
	
	public final static String ORDER_IS_NOT_OVER = "订单还未结束";
	
	public final static String ORDER_HAS_NOT_BEEN_COMPLETED = "订单还未完成";
	
	public final static String THE_ORDER_IS_BEING_PROCESSED = "订单正在处理";
	
	public final static String ORDER_CREATION_FAILED = "订单创建失败";
	
	public final static String DUPLICATE_SECKILL_ACTIVITY_ORDER = "您已经购买过这件商品了";
	
    public final static String REPEAT_PAYMENT_ORDER = "重复支付订单";
	
	/** shopping order item **/
	public final static String IN_THE_ORDER_HAS_BEEN_REFUNDED = "订单已经在退款中";
	
	public final static String THE_CURRENT_ORDER_STATUS_DOES_NOT_MATCH = "当前订单状态不符合";
	
	public final static String PRODUCT_DOES_NOT_SUPPORT_REFUNDS = "商品不支持退款";
	
	public final static String THE_ORDER_EXCEEDS_THE_REFUND_TIME = "订单超过退款时间";
	
	public final static String THE_ORDER_IS_NOT_IN_RECEIPT = "订单不是待收货状态";
	
	public final static String SHOPPING_ORDER_ITEM_DATA_DOES_NOT_EXIST = "购物订单项数据不存在";
	
	/** shopping order refund **/
	public final static String SHOPPING_ORDER_REFUND_DATA_DOES_NOT_EXIST = "购物订单退款数据不存在";
	
	public final static String ORDER_STATUS_IS_NOT_TO_BE_REFUND_STATUS = "订单状态不是退款中状态";
	
	public final static String REFUND_STATE_IS_NOT_A_STATE_TO_BE_RETURNED = "退款状态不是待退货状态";
	
	public final static String THE_REFUND_STATUS_IS_NOT_A_REFUND_FAILURE = "退款状态不是退款失败状态";
	
	public final static String ORDERS_NOT_TO_BE_SHIPPING_STATUS = "订单不是待发货状态";
	
	public final static String THE_STATUS_OF_THE_REFUND_IS_NOT_PENDING = "退款状态不是待确认状态";
	
	public final static String REFUND_STATUS_DOES_NOT_MATCH = "退款状态不符合";
	
	/** pay order **/
	public final static String PAY_ORDER_DATA_DOES_NOT_EXIST = "买单数据不存在";
	
	public final static String ILLEGAL_OPERATION_PAY_ORDER = "非法操作买单数据";
}

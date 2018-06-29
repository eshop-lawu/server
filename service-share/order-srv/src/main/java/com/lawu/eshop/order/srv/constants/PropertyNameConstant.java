package com.lawu.eshop.order.srv.constants;

/**
 * 数据库配置表name常量
 * 
 * @author Sunny
 * @date 2017年4月15日
 */
public class PropertyNameConstant {
	
	/**
	 * 退货申请时间
	 */
	public static final String REFUND_REQUEST_TIME = "refund_request_time";
	
	/**
	 * 自动评价时间
	 */
	public static final String AUTOMATIC_EVALUATION = "automatic_evaluation";
	
	/**
	 * 自动取消未付款订单时间
	 */
	public static final String AUTOMATIC_CANCEL_ORDER = "automatic_cancel_order";
	
	/**
	 * 自动提醒买家支付未付款订单时间
	 */
	public static final String AUTOMATIC_REMIND_NO_PAYMENT_ORDER = "automatic_remind_no_payment_order";

	/**
	 * 自动提醒卖家发货时间
	 */
	public static final String AUTOMATIC_REMIND_SHIPMENTS = "automatic_remind_shipments";
	
	/**
	 * 自动收货时间
	 */
	public static final String AUTOMATIC_RECEIPT = "automatic_receipt";
	
	/**
	 * 删除订单时间
	 */
	public static final String DELETE_ORDER = "delete_order";
	
	/**
	 * 买家申请退款，提醒商家处理时间
	 * 退款类型-退款
	 */
	public static final String TO_BE_CONFIRMED_FOR_REFUND_REMIND_TIME = "to_be_confirmed_for_refund_remind_time";
	
	/**
	 * 买家申请退款，商家未处理，平台自动退款时间
	 * 退款类型-退款
	 */
	public static final  String TO_BE_CONFIRMED_FOR_REFUND_REFUND_TIME = "to_be_confirmed_for_refund_refund_time";
	
	/**
	 * 买家申请退款，提醒商家处理时间
	 * 退款类型-退货退款
	 */
	public static final String TO_BE_CONFIRMED_FOR_RETURN_REFUND_REMIND_TIME = "to_be_confirmed_for_return_refund_remind_time";
	
	/**
	 * 买家申请退款，商家未处理，平台自动退款时间
	 * 退款类型-退货退款
	 */
	public static final String TO_BE_CONFIRMED_FOR_RETURN_REFUND_REFUND_TIME = "to_be_confirmed_for_return_refund_refund_time";
	
	/**
	 * 商家拒绝退款，提醒买家处理时间
	 */
	public static final String REFUND_FAILED_REMIND_TIME = "refund_failed_remind_time";
	
	/**
	 * 商家拒绝退款，买家未处理，平台自动撤销退款时间
	 */
	public static final String REFUND_FAILED_REVOKE_TIME = "refund_failed_revoke_refund_time";
	
	/**
	 * 商家填写退货地址,平台提醒商家操作时间
	 */
	public static final String FILL_RETURN_ADDRESS_REMIND_TIME = "fill_return_address_remind_time";
	
	/**
	 * 商家填写退货地址,商家未操作，平台自动退款时间
	 */
	public static final String FILL_RETURN_ADDRESS_REFUND_TIME = "fill_return_address_refund_time";
	
	/**
	 * 买家填写退货物流信息,平台提醒买家操作时间
	 */
	public static final String TO_BE_RETURNED_REMIND_TIME = "to_be_returned_remind_time";
	
	/**
	 * 买家填写退货物流信息,买家未操作，平台自动撤销退款申请时间
	 */
	public static final String TO_BE_RETURNED_REVOKE_TIME = "to_be_returned_revoke_time";
	
	/**
	 * 等待商家退款,平台第一次提醒商家操作时间
	 */
	public static final String TO_BE_REFUNDED_REMIND_FIRST_TIME = "to_be_refunded_remind_first_time";
	
	/**
	 * 等待商家退款,平台第二次提醒商家操作时间
	 */
	public static final String TO_BE_REFUNDED_REMIND_SECOND_TIME = "to_be_refunded_remind_second_time";
	
	/**
	 * 等待商家退款,商家未操作，平台自动退款时间
	 */
	public static final String TO_BE_REFUNDED_REFUND_TIME = "to_be_returned_refund_time";
	
	/**
	 * 买家申请平台介入，退款类型-退款
	 * 倒计时
	 */
	public static final String PLATFORM_INTERVENTION_REFUND_TIME = "platform_intervention_refund_time";
	
	/**
	 * 买家申请平台介入，退款类型-退货退款
	 * 倒计时
	 */
	public static final String PLATFORM_INTERVENTION_RETURN_REFUND_TIME = "platform_intervention_return_refund_time";
	
	/**
	 * 退款成功-到账提示时间
	 */
	public static final String REFUND_TO_THE_ACCOUNT_TIME = "refund_to_the_account_time";
	
	/**
	 * 用户添加购物车数量
	 */
	public static final String MAX_SHOPPING_CART_QUANTITY = "max_shopping_cart_quantity";
}

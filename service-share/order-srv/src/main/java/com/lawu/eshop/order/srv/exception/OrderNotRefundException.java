package com.lawu.eshop.order.srv.exception;

/**
 * 订单不能被退款
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class OrderNotRefundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public OrderNotRefundException(String message) {
        super(message);
    }
}

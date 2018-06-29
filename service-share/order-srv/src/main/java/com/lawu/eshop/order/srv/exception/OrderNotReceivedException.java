package com.lawu.eshop.order.srv.exception;

/**
 * 订单不能确认收货
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class OrderNotReceivedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public OrderNotReceivedException(String message) {
        super(message);
    }
}

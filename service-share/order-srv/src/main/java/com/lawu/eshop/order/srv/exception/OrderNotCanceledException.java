package com.lawu.eshop.order.srv.exception;

/**
 * 订单不能被取消
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class OrderNotCanceledException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public OrderNotCanceledException(String message) {
        super(message);
    }
}

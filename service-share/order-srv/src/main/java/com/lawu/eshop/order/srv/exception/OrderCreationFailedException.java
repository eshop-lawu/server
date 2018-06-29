package com.lawu.eshop.order.srv.exception;

/**
 * 订单正在处理中异常
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class OrderCreationFailedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public OrderCreationFailedException(String message) {
        super(message);
    }
}

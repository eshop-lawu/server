package com.lawu.eshop.order.srv.exception;

/**
 * 订单正在处理中异常
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class TheOrderIsBeingProcessedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TheOrderIsBeingProcessedException(String message) {
        super(message);
    }
}

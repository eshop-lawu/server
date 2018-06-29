package com.lawu.eshop.order.srv.exception;

/**
 * 订单不能被删除
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class OrderNotDeleteException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public OrderNotDeleteException(String message) {
        super(message);
    }
}

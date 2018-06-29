package com.lawu.eshop.order.srv.exception;

/**
 * 不能同意退款异常
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class CanNotAgreeToARefundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CanNotAgreeToARefundException(String message) {
        super(message);
    }
}

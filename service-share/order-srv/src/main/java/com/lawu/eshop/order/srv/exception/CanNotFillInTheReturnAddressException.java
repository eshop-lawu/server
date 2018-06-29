package com.lawu.eshop.order.srv.exception;

/**
 * 不能填写退货地址
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class CanNotFillInTheReturnAddressException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CanNotFillInTheReturnAddressException(String message) {
        super(message);
    }
}

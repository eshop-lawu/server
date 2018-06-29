package com.lawu.eshop.order.srv.exception;

/**
 * 不能撤销申请
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class CanNotCancelApplicationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CanNotCancelApplicationException(String message) {
        super(message);
    }
}

package com.lawu.eshop.order.srv.exception;

/**
 * 不能同意会员的退款申请
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class CanNotAgreeToApplyException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CanNotAgreeToApplyException(String message) {
        super(message);
    }
}

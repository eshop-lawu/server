package com.lawu.eshop.order.srv.exception;

/**
 * 不能填写退货物流
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class CanNotFillOutTheReturnLogisticsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CanNotFillOutTheReturnLogisticsException(String message) {
        super(message);
    }
}

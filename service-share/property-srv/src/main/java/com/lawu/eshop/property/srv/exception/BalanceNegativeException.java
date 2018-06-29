package com.lawu.eshop.property.srv.exception;

/**
 *  数值负数
 * @author yangqh
 * @date 2017年9月26日
 */
public class BalanceNegativeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BalanceNegativeException(String message) {
        super(message);
    }
}

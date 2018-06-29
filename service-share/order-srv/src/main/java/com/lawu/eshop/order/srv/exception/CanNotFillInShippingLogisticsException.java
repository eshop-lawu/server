package com.lawu.eshop.order.srv.exception;

/**
 * 不能申请平台介入
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class CanNotFillInShippingLogisticsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CanNotFillInShippingLogisticsException(String message) {
        super(message);
    }
}

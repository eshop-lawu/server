package com.lawu.eshop.mall.srv.exception;

/**
 * 非法操作异常<p>
 * 这条数据不属于当前用户的
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class IllegalOperationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public IllegalOperationException(String message) {
        super(message);
    }
}

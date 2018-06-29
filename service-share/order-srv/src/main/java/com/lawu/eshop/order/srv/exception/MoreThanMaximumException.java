package com.lawu.eshop.order.srv.exception;

/**
 * 超过允许最大值异常
 * 
 * @author Sunny
 * @date 2017年6月29日
 */
public class MoreThanMaximumException extends RuntimeException {
	
	private static final long serialVersionUID = 8578776731791598766L;
	
	public MoreThanMaximumException(String message) {
        super(message);
    }
	
}

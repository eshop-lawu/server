package com.lawu.eshop.mall.srv.exception;

/**
 * 数据不存在异常<p>
 * 通过id查找不到,或者数据当前是无效状态
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class DataNotExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DataNotExistException(String message) {
        super(message);
    }
}

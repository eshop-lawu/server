package com.lawu.eshop.common.exception;

import com.lawu.eshop.framework.web.impl.ResultCode;

/**
 * 非法操作异常<p>
 * 这条数据不属于当前用户的
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class IllegalOperationException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;
    
    private int ret = ResultCode.ILLEGAL_OPERATION;

    private String message = ResultCode.get(ret);
    
    public IllegalOperationException() {
        super();
    }
    
    public IllegalOperationException(String message) {
        super(message);
        this.message = message;
    }
    
    public IllegalOperationException(int ret, String message) {
        super(message);
        this.ret = ret;
        this.message = message;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}

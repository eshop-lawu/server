package com.lawu.eshop.common.exception;

import com.lawu.eshop.framework.web.impl.ResultCode;

/**
 * 错误操作异常
 * <p>
 * 用户的异常操作
 * 
 * @author jiangxinjun
 * @createDate 2017年11月27日
 * @updateDate 2017年11月27日
 */
public class WrongOperationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int ret = ResultCode.WRONG_OPERATION;

    private String message = ResultCode.get(ret);
    
    public WrongOperationException() {
        super();
    }
    
    public WrongOperationException(String message) {
        super(message);
        this.message = message;
    }
    
    public WrongOperationException(int ret) {
        super();
        this.ret = ret;
        this.message = ResultCode.get(ret);
    }
    
    public WrongOperationException(int ret, String message) {
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

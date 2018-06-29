package com.lawu.eshop.common.exception;

import com.lawu.eshop.framework.web.impl.ResultCode;

/**
 * 数据不存在异常
 * <p>
 * 通过id查找不到,或者数据当前是无效状态
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class DataNotExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int ret = ResultCode.NOT_FOUND_DATA;

    private String message = ResultCode.get(ret);

    public DataNotExistException() {
        super();
    }

    public DataNotExistException(String message) {
        super(message);
        this.message = message;
    }

    public DataNotExistException(int ret, String message) {
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

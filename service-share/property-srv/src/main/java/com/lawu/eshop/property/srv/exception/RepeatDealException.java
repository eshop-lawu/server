package com.lawu.eshop.property.srv.exception;

/**
 * 重复处理
 *
 * @author meishuquan
 * @date 2017/11/6
 */
public class RepeatDealException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RepeatDealException(String message) {
        super(message);
    }
}

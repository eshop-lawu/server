package com.lawu.eshop.property.srv.exception;

/**
 * 第三方转账失败异常类
 *
 * @author yangqh
 * @date 2018年1月17日
 */
public class ThirdCashTransferException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ThirdCashTransferException(String message) {
        super(message);
    }

    public ThirdCashTransferExceptionResult thirdCashTransferExceptionResult;

    public ThirdCashTransferExceptionResult getThirdCashTransferExceptionResult() {
        return thirdCashTransferExceptionResult;
    }

    public void setThirdCashTransferExceptionResult(ThirdCashTransferExceptionResult thirdCashTransferExceptionResult) {
        this.thirdCashTransferExceptionResult = thirdCashTransferExceptionResult;
    }
}

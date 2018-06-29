package com.lawu.eshop.product.srv.exception;

import com.lawu.eshop.framework.web.impl.ResultCode;

/**
 * 商品头部图片不能为空
 */
public class TitleImageNotEmptyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public int resultCode = ResultCode.PRODUCT_MODEL_SPEC_NOT_EMPTY;

    public TitleImageNotEmptyException(String message) {
        super(message);
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}

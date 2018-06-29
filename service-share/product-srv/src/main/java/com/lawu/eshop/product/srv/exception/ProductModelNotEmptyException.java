package com.lawu.eshop.product.srv.exception;

import com.lawu.eshop.framework.web.impl.ResultCode;

/**
 * 商品规格型号不能为空
 */
public class ProductModelNotEmptyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public int resultCode = ResultCode.PRODUCT_MODEL_SPEC_NOT_EMPTY;

    public ProductModelNotEmptyException(String message) {
        super(message);
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}

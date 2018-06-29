package com.lawu.eshop.product.srv.exception;

import com.lawu.eshop.framework.web.impl.ResultCode;

/**
 * 商品型号规格最多定义5个
 */
public class ProductSpecOutSizeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public int resultCode = ResultCode.PRODUCT_MODEL_SPEC_OUT_SIZE;

    public ProductSpecOutSizeException(String message) {
        super(message);
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}

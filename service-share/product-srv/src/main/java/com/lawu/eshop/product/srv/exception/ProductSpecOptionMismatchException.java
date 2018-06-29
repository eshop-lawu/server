package com.lawu.eshop.product.srv.exception;

import com.lawu.eshop.framework.web.impl.ResultCode;

/**
 * 客户端传入的规格数据和规格选项，长度不匹配
 */
public class ProductSpecOptionMismatchException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public int resultCode = ResultCode.PRODUCT_MODEL_SPEC_MISMATCH;

    public ProductSpecOptionMismatchException(String message) {
        super(message);
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}

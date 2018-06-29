package com.lawu.eshop.product.srv.exception;

import com.lawu.eshop.framework.web.impl.ResultCode;

/**
 * 商品价格和库存为空
 */
public class InvalidPriceAndStockException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public int resultCode = ResultCode.PRODUCT_MODEL_PRICE_STOCK_INVALID;

    public InvalidPriceAndStockException(String message) {
        super(message);
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}

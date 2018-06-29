package com.lawu.eshop.order.srv.domain.extend;

import java.math.BigDecimal;

/**
 * 更新商家实际收入金额
 * 
 * @author jiangxinjun
 * @createDate 2017年11月13日
 * @updateDate 2017年11月13日
 */
public class UpdateMerchantActualIncomeParam {
    
    /**
     * 订单id
     */
    private Long id;
    
    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }
    
}

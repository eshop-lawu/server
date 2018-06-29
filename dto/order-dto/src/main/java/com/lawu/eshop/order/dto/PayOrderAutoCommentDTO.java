package com.lawu.eshop.order.dto;

import java.math.BigDecimal;

/**
 * @author zhangyong
 * @date 2017/7/28.
 */
public class PayOrderAutoCommentDTO {

    private Long id;

    private Long merchantId;

    private Long memberId;

    private BigDecimal actualAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }
}

package com.lawu.eshop.order.dto;

/**
 *
 */
public class PayOrderBaseDTO {

    private Long merchantId;
    private Long memberId;

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
}

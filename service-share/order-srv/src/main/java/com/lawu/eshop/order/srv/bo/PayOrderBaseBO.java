package com.lawu.eshop.order.srv.bo;

/**
 *
 */
public class PayOrderBaseBO {

    /**
     *
     * 用户ID
     * pay_order.member_id
     *
     * @mbg.generated
     */
    private Long memberId;

    /**
     *
     * 商家ID
     * pay_order.merchant_id
     *
     * @mbg.generated
     */
    private Long merchantId;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}

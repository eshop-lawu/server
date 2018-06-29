package com.lawu.eshop.mall.param;

import java.math.BigDecimal;

/**
 * @author zhangyong
 * @date 2017/7/28.
 */
public class PayOrderAutoCommentParam {

    private Long payOrderId;

    private Long memberId;

    private Long merchantId;

    private BigDecimal avgSpend;

    public Long getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(Long payOrderId) {
        this.payOrderId = payOrderId;
    }

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

    public BigDecimal getAvgSpend() {
        return avgSpend;
    }

    public void setAvgSpend(BigDecimal avgSpend) {
        this.avgSpend = avgSpend;
    }
}

package com.lawu.eshop.mq.dto.user;

import java.io.Serializable;

/**
 * @author zhangyong
 * @date 2017/5/18.
 */
public class FansInfo implements Serializable {

    private static final long serialVersionUID = 1941585648096832084L;

    private Long memberId;

    private Long merchantId;
    
    private Integer payOrderCount;

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

    public Integer getPayOrderCount() {
        return payOrderCount;
    }

    public void setPayOrderCount(Integer payOrderCount) {
        this.payOrderCount = payOrderCount;
    }
    
}

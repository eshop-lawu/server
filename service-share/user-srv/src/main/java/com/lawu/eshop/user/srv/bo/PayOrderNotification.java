package com.lawu.eshop.user.srv.bo;

import com.lawu.compensating.transaction.Notification;

/**
 * @author zhangyong
 * @date 2017/4/11.
 */
public class PayOrderNotification extends Notification {
    private Long merchantId;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}

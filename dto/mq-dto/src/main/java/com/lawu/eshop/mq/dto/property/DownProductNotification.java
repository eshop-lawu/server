package com.lawu.eshop.mq.dto.property;

import com.lawu.compensating.transaction.Notification;

/**
 * @author yangqh
 * @date 2017/8/16.
 */
public class DownProductNotification extends Notification {
    private Long merchantId;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }


}

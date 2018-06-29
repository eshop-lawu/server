package com.lawu.eshop.mq.dto.property;

import com.lawu.compensating.transaction.Notification;

/**
 * @author zhangyong
 * @date 2017/6/7.
 */
public class StoreStatusNotification extends Notification {
    private Long merchantId;

    private Boolean isShow;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }
}

package com.lawu.eshop.property.srv.bo;

import com.lawu.compensating.transaction.Notification;

/**
 * @author zhangyong
 * @date 2017/4/13.
 */
@SuppressWarnings("serial")
public class PayOrderNotification extends Notification {

    private Long payOrderId;

    public Long getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(Long payOrderId) {
        this.payOrderId = payOrderId;
    }

}

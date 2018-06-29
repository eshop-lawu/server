package com.lawu.eshop.mq.dto.order;

import com.lawu.compensating.transaction.Notification;

/**
 * @author zhangyong
 * @date 2017/4/11.
 */
public class PayOrderNotification extends Notification {

    private static final long serialVersionUID = -3962777472789107260L;

    private Long payOrderId;
    private String thirdNum;
    private Byte payType;

    public Long getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(Long payOrderId) {
        this.payOrderId = payOrderId;
    }

    public String getThirdNum() {
        return thirdNum;
    }

    public void setThirdNum(String thirdNum) {
        this.thirdNum = thirdNum;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }
}

package com.lawu.eshop.mq.dto.property;

import java.io.Serializable;

/**
 * 商家申请退款保证金，运营平台处理成功或失败后发送消息给mall-srv推送通知商家
 */
public class RefundDepositDoSuccessOrFailureNotification implements Serializable {


    private static final long serialVersionUID = -3814129714648690085L;

    private Long depositId;

    private String failureReason;

    private String merchantNum;

    private Byte depositOperEnumVal;

    public Long getDepositId() {
        return depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public String getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum;
    }

    public Byte getDepositOperEnumVal() {
        return depositOperEnumVal;
    }

    public void setDepositOperEnumVal(Byte depositOperEnumVal) {
        this.depositOperEnumVal = depositOperEnumVal;
    }
}

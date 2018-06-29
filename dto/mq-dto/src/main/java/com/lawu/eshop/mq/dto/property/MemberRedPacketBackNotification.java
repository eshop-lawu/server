package com.lawu.eshop.mq.dto.property;

import java.util.Date;

import com.lawu.compensating.transaction.Notification;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;

/**
 * 用户发红包退款
 */
public class MemberRedPacketBackNotification extends Notification {

    private static final long serialVersionUID = -6139212807502373307L;

    private String userNum;

    private String redPacketId;

    private String refundMoney;

    private TransactionPayTypeEnum transactionPayTypeEnum;

    private String tradeNo;

    //主事务执行时间
    private Date gmtExecute;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(String redPacketId) {
        this.redPacketId = redPacketId;
    }

    public String getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(String refundMoney) {
        this.refundMoney = refundMoney;
    }

    public TransactionPayTypeEnum getTransactionPayTypeEnum() {
        return transactionPayTypeEnum;
    }

    public void setTransactionPayTypeEnum(TransactionPayTypeEnum transactionPayTypeEnum) {
        this.transactionPayTypeEnum = transactionPayTypeEnum;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Date getGmtExecute() {
        return gmtExecute;
    }

    public void setGmtExecute(Date gmtExecute) {
        this.gmtExecute = gmtExecute;
    }
}

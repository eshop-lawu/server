package com.lawu.eshop.activity.param;

import java.util.Date;

import com.lawu.eshop.activity.constants.RedpacketSendRecordStatusEnum;

/**
 * @author meishuquan
 * @date 2018/1/3.
 */
public class RedpacketSendRecordParam {

    private Long id;

    private Integer activityId;

    private Long attendDetailId;

    private String userNum;

    private String openid;

    private String returnCode;

    private String returnMsg;

    private String resultCode;

    private String errCode;

    private String errCodeDes;

    private String mchBillno;

    private String sendListId;

    private Integer totalAmount;

    private RedpacketSendRecordStatusEnum statusEnum;

    private Date rcvTime;

    private Date refundTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Long getAttendDetailId() {
        return attendDetailId;
    }

    public void setAttendDetailId(Long attendDetailId) {
        this.attendDetailId = attendDetailId;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    public String getMchBillno() {
        return mchBillno;
    }

    public void setMchBillno(String mchBillno) {
        this.mchBillno = mchBillno;
    }

    public String getSendListId() {
        return sendListId;
    }

    public void setSendListId(String sendListId) {
        this.sendListId = sendListId;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public RedpacketSendRecordStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(RedpacketSendRecordStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public Date getRcvTime() {
        return rcvTime;
    }

    public void setRcvTime(Date rcvTime) {
        this.rcvTime = rcvTime;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }
}

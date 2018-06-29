package com.lawu.eshop.property.param;

import java.util.Date;

import com.lawu.eshop.property.constants.PayTypeEnum;

/**
 * @author meishuquan
 * @date 2018/2/28.
 */
public class TakeLotteryPropertyInfoParam {

    private String userNum;

    private PayTypeEnum payTypeEnum;

    private String money;

    private String bizId;

    private Date gmtExecute;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public PayTypeEnum getPayTypeEnum() {
        return payTypeEnum;
    }

    public void setPayTypeEnum(PayTypeEnum payTypeEnum) {
        this.payTypeEnum = payTypeEnum;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public Date getGmtExecute() {
        return gmtExecute;
    }

    public void setGmtExecute(Date gmtExecute) {
        this.gmtExecute = gmtExecute;
    }
}

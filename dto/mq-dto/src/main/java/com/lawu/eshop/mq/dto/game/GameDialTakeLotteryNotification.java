package com.lawu.eshop.mq.dto.game;

import java.util.Date;

import com.lawu.compensating.transaction.Notification;
import com.lawu.eshop.common.constants.PayTypeEnum;

/**
 * @author meishuquan
 * @date 2018/3/16.
 */
public class GameDialTakeLotteryNotification extends Notification {

    private static final long serialVersionUID = -7674777916467948221L;

    private Long id;

    private String userNum;

    private String money;

    private PayTypeEnum payTypeEnum;

    //主事务执行时间
    private Date gmtExecute;

    private boolean result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public PayTypeEnum getPayTypeEnum() {
        return payTypeEnum;
    }

    public void setPayTypeEnum(PayTypeEnum payTypeEnum) {
        this.payTypeEnum = payTypeEnum;
    }

    public Date getGmtExecute() {
        return gmtExecute;
    }

    public void setGmtExecute(Date gmtExecute) {
        this.gmtExecute = gmtExecute;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}

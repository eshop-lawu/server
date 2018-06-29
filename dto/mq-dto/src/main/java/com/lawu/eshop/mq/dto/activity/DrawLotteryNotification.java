package com.lawu.eshop.mq.dto.activity;

import java.util.Date;

import com.lawu.compensating.transaction.Notification;

/**
 * @author meishuquan
 * @date 2018/1/15.
 */
public class DrawLotteryNotification extends Notification {

    private static final long serialVersionUID = -1837059845922803464L;

    private Long id;

    private String userNum;

    private String point;

    //主事务执行时间
    private Date gmtExecute;

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

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public Date getGmtExecute() {
        return gmtExecute;
    }

    public void setGmtExecute(Date gmtExecute) {
        this.gmtExecute = gmtExecute;
    }
}

package com.lawu.eshop.mq.dto.activity;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.compensating.transaction.Notification;

/**
 * 发送红包消息
 * @author jiangxinjun
 * @createDate 2018年1月16日
 * @updateDate 2018年1月16日
 */
public class RedpacketSendNotification extends Notification {

    private static final long serialVersionUID = 2569814098213619773L;
    
    /**
     * 关注详情id
     */
    private Long attendDetailId;
    
    /**
     * 用户编号
     */
    private String userNum;
    
    /**
     * 红包金额
     */
    private BigDecimal redpacketAmount;

    //主事务执行时间
    private Date gmtExecute;

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

    public BigDecimal getRedpacketAmount() {
        return redpacketAmount;
    }

    public void setRedpacketAmount(BigDecimal redpacketAmount) {
        this.redpacketAmount = redpacketAmount;
    }

    public Date getGmtExecute() {
        return gmtExecute;
    }

    public void setGmtExecute(Date gmtExecute) {
        this.gmtExecute = gmtExecute;
    }
}

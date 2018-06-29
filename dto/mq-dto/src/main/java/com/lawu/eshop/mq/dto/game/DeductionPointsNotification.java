package com.lawu.eshop.mq.dto.game;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.compensating.transaction.Notification;

/**
 * 游戏参与积分扣除积分通知
 * 
 * @author jiangxinjun
 * @createDate 2018年3月12日
 * @updateDate 2018年3月12日
 */
public class DeductionPointsNotification extends Notification {
    
    private static final long serialVersionUID = 4198685254235835566L;

    /**
     * 业务id
     */
    private Long bizId;
    
    /**
     * 会员编号
     */
    private String memberNum;
    
    /**
     * 积分
     */
    private BigDecimal point;
    
    /**
     * 事务执行时间
     */
    private Date gmtExecute;
    
    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public Date getGmtExecute() {
        return gmtExecute;
    }

    public void setGmtExecute(Date gmtExecute) {
        this.gmtExecute = gmtExecute;
    }
    
}

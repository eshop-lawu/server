package com.lawu.eshop.mq.dto.order;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.compensating.transaction.Notification;

/**
 * 创建订单扣除积分通知
 * 
 * @author jiangxinjun
 * @createDate 2018年3月12日
 * @updateDate 2018年3月12日
 */
public class CreateOrderDeductionPointsNotification extends Notification {

    private static final long serialVersionUID = -9019048507487187064L;

    /**
     * 购物订单id
     */
    private Long shoppingOrderId;
    
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
    
    public Long getShoppingOrderId() {
        return shoppingOrderId;
    }

    public void setShoppingOrderId(Long shoppingOrderId) {
        this.shoppingOrderId = shoppingOrderId;
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

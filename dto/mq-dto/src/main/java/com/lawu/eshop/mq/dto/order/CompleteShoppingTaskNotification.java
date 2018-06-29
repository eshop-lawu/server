package com.lawu.eshop.mq.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 完成购物任务消息
 * @author jiangxinjun
 * @createDate 2018年5月4日
 * @updateDate 2018年5月4日
 */
public class CompleteShoppingTaskNotification implements Serializable {
    
    private static final long serialVersionUID = -4429013391997586359L;

    /**
     * 用户编号
     */
     private String memberNum;
     
     /**
      * 订单金额(商品价格+运费)
      */
     private BigDecimal orderAmount;

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }
     
}

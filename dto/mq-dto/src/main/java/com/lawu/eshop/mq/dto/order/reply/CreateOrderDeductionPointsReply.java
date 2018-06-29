package com.lawu.eshop.mq.dto.order.reply;

import com.lawu.compensating.transaction.Reply;

/**
 * 创建订单扣除订单扣除积分通知
 * 
 * @author jiangxinjun
 * @createDate 2018年3月12日
 * @updateDate 2018年3月12日
 */
public class CreateOrderDeductionPointsReply extends Reply {

    private static final long serialVersionUID = 7503235905774283188L;

    /**
     *  积分是否扣除成功
     */
    private Boolean isSuccess;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
    
}

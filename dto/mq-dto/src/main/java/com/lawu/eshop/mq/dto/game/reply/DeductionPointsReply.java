package com.lawu.eshop.mq.dto.game.reply;

import com.lawu.compensating.transaction.Reply;

/**
 * 参与游戏扣除积分回复通知
 * 
 * @author jiangxinjun
 * @createDate 2018年3月13日
 * @updateDate 2018年3月13日
 */
public class DeductionPointsReply extends Reply {
    
    private static final long serialVersionUID = -4531134678643694065L;
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

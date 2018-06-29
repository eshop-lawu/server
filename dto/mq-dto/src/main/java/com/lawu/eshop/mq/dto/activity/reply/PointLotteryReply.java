package com.lawu.eshop.mq.dto.activity.reply;

import com.lawu.compensating.transaction.Reply;

/**
 * @author meishuquan
 * @date 2018/2/2.
 */
public class PointLotteryReply extends Reply {

    private static final long serialVersionUID = -923131674702489998L;

    private boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}

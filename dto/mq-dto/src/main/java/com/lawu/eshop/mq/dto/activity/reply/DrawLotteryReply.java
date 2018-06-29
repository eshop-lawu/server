package com.lawu.eshop.mq.dto.activity.reply;

import com.lawu.compensating.transaction.Reply;

/**
 * @author meishuquan
 * @date 2018/1/31.
 */
public class DrawLotteryReply extends Reply {

    private static final long serialVersionUID = -923131674702489998L;

    private boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}

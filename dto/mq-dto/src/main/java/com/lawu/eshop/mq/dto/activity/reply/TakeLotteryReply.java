package com.lawu.eshop.mq.dto.activity.reply;

import com.lawu.compensating.transaction.Reply;

/**
 * @author meishuquan
 * @date 2018/2/28.
 */
public class TakeLotteryReply extends Reply {

    private static final long serialVersionUID = 3547316661047233272L;

    private boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}

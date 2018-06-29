package com.lawu.eshop.mq.dto.game.reply;

import com.lawu.compensating.transaction.Reply;

/**
 * @author meishuquan
 * @date 2018/3/16.
 */
public class GameDialTakeLotteryReply extends Reply {

    private static final long serialVersionUID = -474422673878905870L;

    private boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}

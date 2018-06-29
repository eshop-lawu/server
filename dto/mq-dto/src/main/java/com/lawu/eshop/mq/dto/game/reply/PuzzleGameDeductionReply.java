package com.lawu.eshop.mq.dto.game.reply;

import com.lawu.compensating.transaction.Reply;

/**
 * @author lihj <br>
 * @date 2018/3/13
 */
public class PuzzleGameDeductionReply extends Reply {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4652506442559500618L;

	private boolean result;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}

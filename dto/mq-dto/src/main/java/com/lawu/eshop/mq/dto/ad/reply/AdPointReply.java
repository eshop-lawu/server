package com.lawu.eshop.mq.dto.ad.reply;

import com.lawu.compensating.transaction.Reply;
import com.lawu.eshop.mq.dto.product.CheckLessInventoryResultEnum;

/**
 * 创建广告是否扣积分成功
 * 
 * @author zhangrc
 * @date 2017年9月26日
 */
public class AdPointReply extends Reply {

	private static final long serialVersionUID = -5142282166586603538L;
	
	/**
	 * 是否成功
	 */
	private boolean flag;
	

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	
	
}

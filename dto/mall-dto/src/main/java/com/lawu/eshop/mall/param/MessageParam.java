package com.lawu.eshop.mall.param;

import com.lawu.eshop.mall.constants.MessageQueryTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * 站内信息参数设置
 * Created by Administrator on 2017/3/29.
 */
public class MessageParam extends AbstractPageParam {
	
	
	@ApiModelProperty(name = "typeEnum", required = false, value = "NOTICE 通知  BUSINESS 交易物流 ")
	private MessageQueryTypeEnum typeEnum;

	public MessageQueryTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(MessageQueryTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	
	
}

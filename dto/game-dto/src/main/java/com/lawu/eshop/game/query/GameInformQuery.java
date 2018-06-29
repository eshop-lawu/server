package com.lawu.eshop.game.query;

import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.game.constants.GameInformStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

public class GameInformQuery extends AbstractPageParam{
	
	@ApiModelProperty(value = "处理状态")
	private GameInformStatusEnum statusEnum;
	
	@ApiModelProperty(value = "游戏类型")
	private GameTypeEnum typeEnum;

	@ApiModelProperty(value = "排序类型")
	private String sortOrder;

	public GameInformStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(GameInformStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public GameTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(GameTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}
	
	
}

package com.lawu.eshop.game.query;

import com.lawu.eshop.game.constants.GameDialPrizeStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月16日
 */
public class GameDailPrizeQuery extends AbstractPageParam{
	
	private String name;
	
	private GameDialPrizeStatusEnum statusEnum;
	
	private String sortOrder;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public GameDialPrizeStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(GameDialPrizeStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}
	
	

}

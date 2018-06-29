package com.lawu.eshop.game.query;

import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author meishuquan
 * @date 2018/3/16.
 */
public class GameDialQuery extends AbstractPageParam {

	private String sortOrder;

    public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	
}

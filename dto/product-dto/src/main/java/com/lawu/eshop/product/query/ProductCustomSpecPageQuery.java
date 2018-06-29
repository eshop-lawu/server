package com.lawu.eshop.product.query;

import com.lawu.eshop.product.constant.CustomSpecStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月16日
 */
public class ProductCustomSpecPageQuery extends AbstractPageParam{
	
	
	private String relateName;
	
	private CustomSpecStatusEnum statusEnum;
	
	private String sortOrder;

	public String getRelateName() {
		return relateName;
	}

	public void setRelateName(String relateName) {
		this.relateName = relateName;
	}

	public CustomSpecStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(CustomSpecStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	
	

}

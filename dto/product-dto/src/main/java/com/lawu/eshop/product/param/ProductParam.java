package com.lawu.eshop.product.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

public class ProductParam extends AbstractPageParam{

	@ApiParam(value = "商品名称")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

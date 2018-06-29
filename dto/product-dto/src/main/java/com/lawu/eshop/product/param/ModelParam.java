package com.lawu.eshop.product.param;

import io.swagger.annotations.ApiParam;

public class ModelParam {
	
	@ApiParam (name="modelId",required = true, value = "商品型号id")
	private Long modelId;
	
	@ApiParam (name="count",required = true, value = "商品型号数量")
	private int count;

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	

}

package com.lawu.eshop.order.dto.foreign;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MemberShoppingCartGroupDTO {
	
    /**
     * 购物车分组子项
     */
	@ApiModelProperty(value = "购物车分组子项", required = true)
    private List<MemberShoppingCartDTO> item;

	public List<MemberShoppingCartDTO> getItem() {
		return item;
	}

	public void setItem(List<MemberShoppingCartDTO> item) {
		this.item = item;
	}
    
}
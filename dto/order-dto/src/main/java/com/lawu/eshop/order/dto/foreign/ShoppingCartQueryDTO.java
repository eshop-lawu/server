package com.lawu.eshop.order.dto.foreign;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShoppingCartQueryDTO {
	
	/**
	 * 购物车分组列表
	 */
	@ApiModelProperty(value = "购物车分组列表", required = true)
	private List<MemberShoppingCartGroupDTO> shoppingCartGroupList;
	
	/**
	 * 购物车无效列表
	 */
	@ApiModelProperty(value = "购物车无效列表", required = true)
	private List<MemberShoppingCartDTO> shoppingCartInvalidList;

	public List<MemberShoppingCartGroupDTO> getShoppingCartGroupList() {
		return shoppingCartGroupList;
	}

	public void setShoppingCartGroupList(List<MemberShoppingCartGroupDTO> shoppingCartGroupList) {
		this.shoppingCartGroupList = shoppingCartGroupList;
	}

	public List<MemberShoppingCartDTO> getShoppingCartInvalidList() {
		return shoppingCartInvalidList;
	}

	public void setShoppingCartInvalidList(List<MemberShoppingCartDTO> shoppingCartInvalidList) {
		this.shoppingCartInvalidList = shoppingCartInvalidList;
	}
	
}
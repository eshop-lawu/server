package com.lawu.eshop.mall.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 快递公司分组DTO
 * 
 * @author Sunny
 * @date 2017年7月7日
 */
public class ExpressCompanyGroupDTO {
	
	/**
	 * name首字母
	 */
	@ApiModelProperty(notes = "name首字母", required = true)
	private String key;
	
	/**
	 * 快递公司分组列表
	 */
	@ApiModelProperty(notes = "快递公司分组列表", required = true)
	private List<ExpressCompanyDTO> items;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<ExpressCompanyDTO> getItems() {
		return items;
	}

	public void setItems(List<ExpressCompanyDTO> items) {
		this.items = items;
	}
	
}

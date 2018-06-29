package com.lawu.eshop.mall.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 快递公司查询DTO
 * 
 * @author Sunny
 * @date 2017年7月7日
 */
@ApiModel
public class ExpressCompanyQueryDTO {
	
	/**
	 * 快递公司列表
	 */
	@ApiModelProperty(notes = "快递公司列表", required = true)
	private List<ExpressCompanyGroupDTO> list;

	public List<ExpressCompanyGroupDTO> getList() {
		return list;
	}

	public void setList(List<ExpressCompanyGroupDTO> list) {
		this.list = list;
	}
	
}

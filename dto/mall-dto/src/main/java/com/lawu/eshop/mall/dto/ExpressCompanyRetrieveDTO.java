package com.lawu.eshop.mall.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 检索快递公司DTO
 *
 * @author Sunny
 * @date 2017/3/24
 */
@ApiModel
public class ExpressCompanyRetrieveDTO {
	
	/**
	 * 快递公司列表
	 */
	@ApiModelProperty(name = "快递公司列表", required = true)
	private List<ExpressCompanyDTO> list;

	public List<ExpressCompanyDTO> getList() {
		return list;
	}

	public void setList(List<ExpressCompanyDTO> list) {
		this.list = list;
	}
	
}

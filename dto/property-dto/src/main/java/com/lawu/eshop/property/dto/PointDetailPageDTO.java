package com.lawu.eshop.property.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.json.KeepDecimalJsonSerializer;

import io.swagger.annotations.ApiModelProperty;

public class PointDetailPageDTO {
	
	/**
	 * 用户积分
	 */
    @JsonSerialize(using = KeepDecimalJsonSerializer.class)
	@ApiModelProperty(value = "用户积分", required = true)
	private BigDecimal point;
	
	/**
	 * 积分明细分页数据
	 */
	@ApiModelProperty(value = "积分明细分页数据", required = true)
	private Page<PointDetailDTO> page;

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	public Page<PointDetailDTO> getPage() {
		return page;
	}

	public void setPage(Page<PointDetailDTO> page) {
		this.page = page;
	}
	
}
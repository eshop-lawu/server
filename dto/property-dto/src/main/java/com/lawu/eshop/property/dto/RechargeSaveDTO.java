package com.lawu.eshop.property.dto;

import io.swagger.annotations.ApiModelProperty;

public class RechargeSaveDTO {
	
	@ApiModelProperty(value = "主键", required = true)
	private Long id;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

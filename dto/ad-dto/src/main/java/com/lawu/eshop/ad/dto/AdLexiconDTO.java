package com.lawu.eshop.ad.dto;

import io.swagger.annotations.ApiModelProperty;

public class AdLexiconDTO {
	
	@ApiModelProperty(value = "标题")
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}

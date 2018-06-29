package com.lawu.eshop.cache.dto;

import io.swagger.annotations.ApiModelProperty;

/** 
 * 系统维护配置dto
 * @author lihj
 * @date 2018年5月10日
 */
public class SysMaintainConfigDTO {

	@ApiModelProperty("配置内容")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}

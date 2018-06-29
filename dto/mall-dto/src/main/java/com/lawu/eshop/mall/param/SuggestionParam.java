package com.lawu.eshop.mall.param;

import com.lawu.eshop.mall.constants.SuggestionClientType;

import io.swagger.annotations.ApiParam;

public class SuggestionParam {

	/**
	 * 建议内容
	 */
	@ApiParam(name = "content", required = true, value = "建议内容")
	private String content;
	
	/**
	 * 客户端类型
	 */
	@ApiParam(name = "clientType", required = true, value = "客户端类型")
	private SuggestionClientType clientType;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SuggestionClientType getClientType() {
		return clientType;
	}

	public void setClientType(SuggestionClientType clientType) {
		this.clientType = clientType;
	}

}

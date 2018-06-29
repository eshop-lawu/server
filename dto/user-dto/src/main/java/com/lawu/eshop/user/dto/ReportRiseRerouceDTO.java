package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * <p>
 * Description: 粉丝增长来源、消费转化
 * </p>
 * @author Yangqh
 * @date 2017年5月2日 下午7:30:57
 *
 */
public class ReportRiseRerouceDTO {

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "value")
    private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

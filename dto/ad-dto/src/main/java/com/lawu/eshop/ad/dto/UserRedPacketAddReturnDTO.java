/**
 * 
 */
package com.lawu.eshop.ad.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author lihj
 * @date 2017年8月4日
 */
public class UserRedPacketAddReturnDTO {

	@ApiModelProperty(value = "红包id")
	private Long id;

	@ApiModelProperty(value = "订单编号")
	private String orderNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

}

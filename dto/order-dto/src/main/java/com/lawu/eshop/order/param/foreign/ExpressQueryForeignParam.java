package com.lawu.eshop.order.param.foreign;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查询物流轨迹srv暴露给api参数
 * 
 * @author jiangxinjun
 * @date 2017年9月5日
 */
public class ExpressQueryForeignParam {

	/**
	 * 快递单号
	 */
	@NotBlank(message = "快递单号不能为空")
	@ApiModelProperty(value = "快递单号", required = true)
	private String expNo;
	
	/**
	 * 快递公司id
	 */
	@ApiModelProperty(value = "快递公司id")
	private Integer expId;

	public String getExpNo() {
		return expNo;
	}

	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}

	public Integer getExpId() {
		return expId;
	}

	public void setExpId(Integer expId) {
		this.expId = expId;
	}
	
}

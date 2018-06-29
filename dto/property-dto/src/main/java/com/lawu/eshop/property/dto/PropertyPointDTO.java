package com.lawu.eshop.property.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Sunny
 * @date 2017/3/31
 */
public class PropertyPointDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
    * 积分
    */
	@ApiModelProperty(name = "point", value= "积分", required = true)
   private BigDecimal point;

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}
   
}

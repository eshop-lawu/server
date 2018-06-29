package com.lawu.eshop.property.srv.bo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Sunny
 * @date 2017/3/31
 */
public class PropertyPointBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	/**
    * 积分
    */
   private BigDecimal point;
   
   

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}
   
}

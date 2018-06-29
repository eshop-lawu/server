package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年6月15日 下午12:06:43
 *
 */
public class PointDetailQueryData1Param {

	@NotBlank(message = "userNum不能为空")
	private String userNum;
	
	@NotNull(message = "pointType不能为空")
	private Byte pointType;
	
	@NotBlank(message = "bizId不能为空")
	private String bizId;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public Byte getPointType() {
		return pointType;
	}

	public void setPointType(Byte pointType) {
		this.pointType = pointType;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	
}
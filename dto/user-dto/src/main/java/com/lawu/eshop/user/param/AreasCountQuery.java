package com.lawu.eshop.user.param;

import com.lawu.eshop.common.constants.UserSexEnum;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月11日
 */
public class AreasCountQuery {
	
	private String areas;
	
	private UserSexEnum sexEnum;
	
	private Integer minAge;
	
	private Integer maxAge;

	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}

	public UserSexEnum getSexEnum() {
		return sexEnum;
	}

	public void setSexEnum(UserSexEnum sexEnum) {
		this.sexEnum = sexEnum;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}
	
	

}

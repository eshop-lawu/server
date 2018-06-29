package com.lawu.eshop.user.srv.domain.extend;

import java.util.List;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月11日
 */
public class AreasCountView {
	
	private List<String> areas;
	
	private Byte sex;
	
	private Integer minAge;
	
	private Integer maxAge;
	

	public List<String> getAreas() {
		return areas;
	}

	public void setAreas(List<String> areas) {
		this.areas = areas;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
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

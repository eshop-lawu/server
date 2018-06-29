package com.lawu.eshop.mall.dto;

import java.util.List;

/**
 * 
 * @author jiangxinjun
 * @date 2017年9月13日
 */
public class RegionProvinceDTO extends RegionPathDTO {

	/**
	 * 城市列表
	 */
	private List<RegionCityDTO> city;

	public List<RegionCityDTO> getCity() {
		return city;
	}

	public void setCity(List<RegionCityDTO> city) {
		this.city = city;
	}
	
}

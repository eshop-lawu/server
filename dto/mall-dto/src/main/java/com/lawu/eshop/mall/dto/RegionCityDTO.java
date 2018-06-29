package com.lawu.eshop.mall.dto;

import java.util.List;

/**
 * 
 * @author jiangxinjun
 * @date 2017年9月13日
 */
public class RegionCityDTO extends RegionPathDTO {

	/**
	 * 区域列表
	 */
	private List<RegionPathDTO> area;

	public List<RegionPathDTO> getArea() {
		return area;
	}

	public void setArea(List<RegionPathDTO> area) {
		this.area = area;
	}
	
}

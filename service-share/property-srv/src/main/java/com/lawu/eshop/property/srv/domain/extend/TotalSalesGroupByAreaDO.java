package com.lawu.eshop.property.srv.domain.extend;

@SuppressWarnings("serial")
public class TotalSalesGroupByAreaDO extends TotalSalesDO{

	private Integer provinceId;
	
	private Integer cityId;
	
	private Integer areaId;
	
	private String type;

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}

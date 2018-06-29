package com.lawu.eshop.user.dto;

/**
 * 
 * <p>
 * Description: 提现时查询冗余提现表中的用户、商家信息DTO
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月10日 下午7:19:58
 *
 */
public class CashUserInfoDTO {

	private String account;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 账号注册省
	 */
	private Integer provinceId;

	/**
	 * 账号注册市
	 */
	private Integer cityId;

	/**
	 * 账号注册区
	 */
	private Integer areaId;
	
	private String regionFullName;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public String getRegionFullName() {
		return regionFullName;
	}

	public void setRegionFullName(String regionFullName) {
		this.regionFullName = regionFullName;
	}

}

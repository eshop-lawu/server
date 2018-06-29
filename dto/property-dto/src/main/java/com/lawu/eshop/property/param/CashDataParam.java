package com.lawu.eshop.property.param;

import java.util.Date;

public class CashDataParam extends CashParam{

	/**
	 * 用户编号
	 */
	private String userNum;

	/**
	 * 提现类型(1-用户提现2-商家提现)
	 */
	private Byte userType;

	/**
	 * 交易类型
	 */
	private Byte transactionType;
	
	/**
	 * 交易账户
	 */
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
	
	public String getRegionFullName() {
		return regionFullName;
	}

	public void setRegionFullName(String regionFullName) {
		this.regionFullName = regionFullName;
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Byte getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Byte transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * withdraw_cash.gmt_create
	 */
	private Date gmtCreate;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public Byte getUserType() {
		return userType;
	}

	public void setUserType(Byte userType) {
		this.userType = userType;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

}

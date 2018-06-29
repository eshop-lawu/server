package com.lawu.eshop.product.param;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class EditProductUpgradeDataParam extends EditProductUpgradeParam{

	@NotNull(message="merchantId不能为空")
	private Long merchantId;
	
	@NotBlank(message="merchantNum不能为空")
	private String merchantNum;

	private String provinceName;

	private String cityName;

	@NotNull(message = "categoryId不能为空")
	private Integer categoryId;

	//商品一级类型名称
	private String categoryName;

	//商品二级类型ID
	private Long categorySubitemId;

	//商品二级类型名称
	private String categorySubitemName;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getCategorySubitemId() {
		return categorySubitemId;
	}

	public void setCategorySubitemId(Long categorySubitemId) {
		this.categorySubitemId = categorySubitemId;
	}

	public String getCategorySubitemName() {
		return categorySubitemName;
	}

	public void setCategorySubitemName(String categorySubitemName) {
		this.categorySubitemName = categorySubitemName;
	}
}

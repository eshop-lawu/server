package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 地址DTO转化
 * @author zhangrc
 * @date 2017/3/22
 *
 */
public class AddressDTO {
	
	@ApiModelProperty(value = "主键", required = true)
	private Long id;

	@ApiModelProperty(value = "是否为默认(false为不默认,true为默认)", required = true)
	private Boolean isDefault;

	@ApiModelProperty(value = "收货人姓名", required = true)
	private String name;

	@ApiModelProperty(value = "电话", required = true)
	private String mobile;

	@ApiModelProperty(value = "区域", required = true)
	private String regionPath;
	
	@ApiModelProperty(value = "区域名称", required = true)
	private String regionName;

	@ApiModelProperty(value = "详细地址", required = true)
	private String addr;

	@ApiModelProperty(value = "邮编")
	private String postcode;


	public Long getId() {
		return id;
	}
		
		
	public void setId(Long id) {
		this.id = id;
	}
		
		
	public Boolean getIsDefault() {
		return isDefault;
	}
		
		
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
		
		
	public String getName() {
		return name;
	}
		
		
	public void setName(String name) {
		this.name = name;
	}
		
		
	public String getMobile() {
		return mobile;
	}
		
		
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
		
		
	public String getRegionPath() {
		return regionPath;
	}
		
		
	public void setRegionPath(String regionPath) {
		this.regionPath = regionPath;
	}
		
		
	public String getAddr() {
		return addr;
	}
		
		
	public void setAddr(String addr) {
		this.addr = addr;
	}
		
		
	public String getPostcode() {
		return postcode;
	}
		
		
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	public String getRegionName() {
		return regionName;
	}


	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
		  
	

}

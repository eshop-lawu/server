package com.lawu.eshop.user.param;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * api 收货地址操作实体
 * 
 * @author zhangrc
 * @date 2017/03/23
 *
 */
@ApiModel
public class AddressMerchantParam implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "name", required = true, value = "收货人")
	@NotBlank(message = "收货人不能为空")
	private String name;

	@ApiModelProperty(name = "mobile", required = true, value = "电话")
	@NotBlank(message = "电话不能为空")
	@Pattern(regexp = "^\\d(\\d|\\/)*\\d$", message = "电话格式不正确")
	private String mobile;

	@ApiModelProperty(name = "regionPath", required = true, value = "地址 格式: 省id/市id/区id")
	@NotBlank(message = "地址id不能为空")
	private String regionPath;
	
	@ApiModelProperty(name = "regionName", required = true, value = "地址名称")
	@NotBlank(message = "地址名称不能为空")
	private String regionName;

	@ApiModelProperty(name = "addr", required = true, value = "详细地址")
	@NotBlank(message = "详细地址不能为空")
	private String addr;


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

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	
}

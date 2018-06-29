package com.lawu.eshop.activity.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月4日
 */
public class IdentityInfoDTO {
	
	@ApiModelProperty(value = "姓名")
	private String  name;
	
	@ApiModelProperty(value = "身份证号码")
	private String idCardNum;
	
	@ApiModelProperty(value = "昵称")
	private String nickName;
	
	@ApiModelProperty(value = "图像")
	private String headImg;
	
	@ApiModelProperty(value = "账号")
	private String account;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	
	

}

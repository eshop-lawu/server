package com.lawu.eshop.user.param;

import io.swagger.annotations.ApiModelProperty;

public class QqLoginMemberParam {
	
	@ApiModelProperty(value = "openId", required = true)
	private String openId;
	
	@ApiModelProperty(value = "昵称", required = true)
	private String nickname;
	
	@ApiModelProperty(value = "性别 男|女", required = true)
	private String gender;
	
	@ApiModelProperty(value = "图像", required = true)
	private String figureurl;
	
	@ApiModelProperty(value = "错误信息")
	private String msg;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFigureurl() {
		return figureurl;
	}

	public void setFigureurl(String figureurl) {
		this.figureurl = figureurl;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	

}

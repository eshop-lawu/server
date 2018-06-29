package com.lawu.eshop.user.dto;

/**
 * 用户报名助力红包获取相关信息
 * @Description
 * @author zhangrc
 * @date 2017年12月28日
 */
public class WeixinUserInfoDTO {
	
	
	private String account;

	
	private String wxOpenid;

	
	private String nickname;

	
	private String headimg;


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getWxOpenid() {
		return wxOpenid;
	}


	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getHeadimg() {
		return headimg;
	}


	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	
	
	

}

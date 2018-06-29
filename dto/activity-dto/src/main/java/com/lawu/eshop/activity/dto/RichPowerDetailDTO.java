package com.lawu.eshop.activity.dto;

import io.swagger.annotations.ApiModelProperty;

/** 
 * 动力
 * @author lihj
 * @date 2018年5月3日
 */
public class RichPowerDetailDTO {
	
	@ApiModelProperty(value="动力值")
	private int totalPower;
	@ApiModelProperty(value="用户编号")
	private String memberNum;
	@ApiModelProperty(value="昵称")
	private String nickName;
	public int getTotalPower() {
		return totalPower;
	}
	public void setTotalPower(int totalPower) {
		this.totalPower = totalPower;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}
	
	
}

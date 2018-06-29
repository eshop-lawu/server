/**
 * 
 */
package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lihj
 * @date 2017年8月4日
 */
public class UserRedPacketReturnDTO {

	@ApiModelProperty(value = "用户昵称")
	private String nickName;

	@ApiModelProperty(value = "用户头像")
	private String headUrl;
	
	@ApiModelProperty(value = "总金额")
	private BigDecimal money;
	
	@ApiModelProperty(value = "邀请人账号")
	private String inviterAccount;
	
	@ApiModelProperty(value = "是否领完")
	private Boolean isGetAll;
	

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getInviterAccount() {
		return inviterAccount;
	}

	public void setInviterAccount(String inviterAccount) {
		this.inviterAccount = inviterAccount;
	}

	public Boolean getIsGetAll() {
		return isGetAll;
	}

	public void setIsGetAll(Boolean isGetAll) {
		this.isGetAll = isGetAll;
	}

	

}

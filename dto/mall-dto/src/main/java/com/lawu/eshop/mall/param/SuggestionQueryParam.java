package com.lawu.eshop.mall.param;

import java.io.Serializable;
import java.util.Date;

import com.lawu.framework.core.page.PageParam;

import io.swagger.annotations.ApiParam;

public class SuggestionQueryParam extends PageParam implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户编号
	 */
	@ApiParam(name = "userNum", value = "用户编号")
	private String userNum;
	
	/**
	 * 建议内容
	 */
	@ApiParam(name = "content", value = "建议内容")
	private String content;
	
	/**
	 * 用户类型，1是商家，2是会员
	 */
	@ApiParam(name = "userType", value = "用户类型，1是商家，2是会员")
	private Byte userType;
	
	/**
	 * 客户端类型，1是android，2是ios
	 */
	@ApiParam(name = "clientType", value = "客户端类型，1是android，2是ios")
	private Byte clientType;
	
	/**
	 * 修改时间
	 */
	@ApiParam(name = "gmtModified", value = "修改时间")
	private Date gmtModified;
	
	/**
	 * 创建时间
	 */
	@ApiParam(name = "gmtCreate", value = "创建时间")
	private Date gmtCreate;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Byte getUserType() {
		return userType;
	}

	public void setUserType(Byte userType) {
		this.userType = userType;
	}

	public Byte getClientType() {
		return clientType;
	}

	public void setClientType(Byte clientType) {
		this.clientType = clientType;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
}

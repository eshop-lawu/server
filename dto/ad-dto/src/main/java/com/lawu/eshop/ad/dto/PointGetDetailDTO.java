package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lawu.framework.web.json.KeepDecimalJsonSerializer;

import io.swagger.annotations.ApiModelProperty;

public class PointGetDetailDTO {

	@ApiModelProperty(value = "用户ID")
	private Long memberId;
	
	@ApiModelProperty(value = "账号")
	private String account;
	
	@ApiModelProperty(value = "名称")
	private String name;
	
	@ApiModelProperty(value = "图像路径")
	private String url;
	
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date gmtCreate;
	
	@JsonSerialize(using=KeepDecimalJsonSerializer.class)
	@ApiModelProperty(value = "获取积分(金额)")
	private BigDecimal point;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}
	
	
}

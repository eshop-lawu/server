package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lawu.eshop.ad.constants.FileTypeEnum;
import com.lawu.framework.web.json.KeepDecimalJsonSerializer;

import io.swagger.annotations.ApiModelProperty;

public class RedPacketInfoDTO {
	
	@ApiModelProperty(value = "积分(金额)")
	@JsonSerialize(using=KeepDecimalJsonSerializer.class)
	private BigDecimal point;
	
	@ApiModelProperty(value = "商家logo")
	private String logoUrl;
	
	@ApiModelProperty(value = "商家名称")
	private String name;
	
	@ApiModelProperty(value = "商家账号")
	private String inviterAccount;

	@ApiModelProperty(value = "广告附件路径")
	private String mediaUrl;

	@ApiModelProperty(value = "文件类型")
	private FileTypeEnum fileType;
	
	@ApiModelProperty(value = "视频时间")
	private String videoTime;

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInviterAccount() {
		return inviterAccount;
	}

	public void setInviterAccount(String inviterAccount) {
		this.inviterAccount = inviterAccount;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public FileTypeEnum getFileType() {
		return fileType;
	}

	public void setFileType(FileTypeEnum fileType) {
		this.fileType = fileType;
	}

	public String getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}
	
	

}

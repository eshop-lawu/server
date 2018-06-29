package com.lawu.eshop.ad.srv.bo;

import java.math.BigDecimal;

import com.lawu.eshop.ad.constants.FileTypeEnum;

public class RedPacketInfoBO {
	
	private BigDecimal point;
	
	private String logoUrl;
	
	private String name;

	private String mediaUrl;

	private FileTypeEnum fileType;

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

	

}

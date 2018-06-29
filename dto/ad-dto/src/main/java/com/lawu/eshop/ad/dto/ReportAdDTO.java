package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.common.constants.AdTypeEnum;

public class ReportAdDTO {

	private Long id;

	private String title;

	private Long merchantId;
	
	private String merchantNum;

	private AdTypeEnum typeEnum;

	private BigDecimal totalPoint;

	private AdStatusEnum statusEnum;

	private Date gmtCreate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}

	public AdTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(AdTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public BigDecimal getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(BigDecimal totalPoint) {
		this.totalPoint = totalPoint;
	}

	public AdStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(AdStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

}

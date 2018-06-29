package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ReportAdEarningsPointDetailDTO {

	private Long id;

	private Date getPointTime;
	
	private String merchantName;
	
	private String account;

	private String title;

	private BigDecimal point;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getGetPointTime() {
		return getPointTime;
	}

	public void setGetPointTime(Date getPointTime) {
		this.getPointTime = getPointTime;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	

}

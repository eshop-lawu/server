package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ReportAdEarningsDetailDTO {

	private Long id;

	private Date getPointTime;

	private Long merchantId;

	private Long memberId;

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

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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

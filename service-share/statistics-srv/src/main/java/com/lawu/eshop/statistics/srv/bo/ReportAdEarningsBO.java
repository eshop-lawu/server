package com.lawu.eshop.statistics.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.statistics.constants.AdStatusEnum;
import com.lawu.eshop.statistics.constants.ReportAdEarningsStatusEnum;

public class ReportAdEarningsBO {

	private Long id;

	private Long adId;

	private String merchantName;

	private String adTitle;

	private AdTypeEnum adTypeEnum;

	private Date adCreateTime;

	private AdStatusEnum adStatusEnum;

	private BigDecimal adTotalPoint;

	private BigDecimal userTotalPoint;

	private BigDecimal loveTotalPoint;

	private ReportAdEarningsStatusEnum reportAdEarningsStatusEnum;

	private Boolean isProcessed;

	private Long auditorId;

	private String auditorAccount;

	private String remark;

	private Date auditTime;

	private Date gmtCreate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getAdTitle() {
		return adTitle;
	}

	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}

	public AdTypeEnum getAdTypeEnum() {
		return adTypeEnum;
	}

	public void setAdTypeEnum(AdTypeEnum adTypeEnum) {
		this.adTypeEnum = adTypeEnum;
	}

	public Date getAdCreateTime() {
		return adCreateTime;
	}

	public void setAdCreateTime(Date adCreateTime) {
		this.adCreateTime = adCreateTime;
	}

	public AdStatusEnum getAdStatusEnum() {
		return adStatusEnum;
	}

	public void setAdStatusEnum(AdStatusEnum adStatusEnum) {
		this.adStatusEnum = adStatusEnum;
	}

	public BigDecimal getAdTotalPoint() {
		return adTotalPoint;
	}

	public void setAdTotalPoint(BigDecimal adTotalPoint) {
		this.adTotalPoint = adTotalPoint;
	}

	public BigDecimal getUserTotalPoint() {
		return userTotalPoint;
	}

	public void setUserTotalPoint(BigDecimal userTotalPoint) {
		this.userTotalPoint = userTotalPoint;
	}

	public BigDecimal getLoveTotalPoint() {
		return loveTotalPoint;
	}

	public void setLoveTotalPoint(BigDecimal loveTotalPoint) {
		this.loveTotalPoint = loveTotalPoint;
	}

	public ReportAdEarningsStatusEnum getReportAdEarningsStatusEnum() {
		return reportAdEarningsStatusEnum;
	}

	public void setReportAdEarningsStatusEnum(ReportAdEarningsStatusEnum reportAdEarningsStatusEnum) {
		this.reportAdEarningsStatusEnum = reportAdEarningsStatusEnum;
	}

	public Boolean getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(Boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	public Long getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Long auditorId) {
		this.auditorId = auditorId;
	}

	public String getAuditorAccount() {
		return auditorAccount;
	}

	public void setAuditorAccount(String auditorAccount) {
		this.auditorAccount = auditorAccount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

}

package com.lawu.eshop.statistics.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.statistics.constants.AdStatusEnum;
import com.lawu.eshop.statistics.constants.ReportAdEarningsStatusEnum;

import io.swagger.annotations.ApiModelProperty;


public class ReportAdEarningsDTO {
	
	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "广告id")
	private Long adId;

	@ApiModelProperty(value = "商家名称")
	private String merchantName;

	@ApiModelProperty(value = "广告标题")
	private String adTitle;

	@ApiModelProperty(value = "广告类型(AD_TYPE_FLAT 平面 AD_TYPE_VIDEO 视频 AD_TYPE_PRAISE E赞 AD_TYPE_PACKET 红包)")
	private AdTypeEnum adTypeEnum;

	@ApiModelProperty(value = "广告创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date adCreateTime;

	@ApiModelProperty(value = " AD_STATUS_ADD 上架 AD_STATUS_PUTING 投放中  AD_STATUS_PUTED 投放结束")
	private AdStatusEnum adStatusEnum;

	@ApiModelProperty(value = "广告投放总积分")
	private BigDecimal adTotalPoint;

	@ApiModelProperty(value = "用户获取的收益")
	private BigDecimal userTotalPoint;

	@ApiModelProperty(value = "爱心账户")
	private BigDecimal loveTotalPoint;

	@ApiModelProperty(value = " ANOMALY 异常 NORMAL 正常")
	private ReportAdEarningsStatusEnum reportAdEarningsStatusEnum;

	@ApiModelProperty(value = "true 已处理  false 未处理")
	private Boolean isProcessed;

	@ApiModelProperty(value = "操作人id")
	private Long auditorId;

	@ApiModelProperty(value = "操作人名称")
	private String auditorAccount;

	@ApiModelProperty(value = "操作备注")
	private String remark;

	@ApiModelProperty(value = "操作时间")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date auditTime;

	@ApiModelProperty(value = "统计时间")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
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

package com.lawu.eshop.statistics.param;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.statistics.constants.AdStatusEnum;
import com.lawu.eshop.statistics.constants.ReportAdEarningsStatusEnum;

import io.swagger.annotations.ApiParam;

public class ReportAdEarningsParam {
	
	@ApiParam (name="adId", value = "广告id")
	private Long adId;
	
	@ApiParam (name="merchantNum", value = "商家编号")
	private String merchantNum;

	@ApiParam (name="merchantName", value = "商家名称")
	private String merchantName;

	@ApiParam (name="adTitle", value = "广告名称")
	private String adTitle;

	@ApiParam (name="adTypeEnum",required = true, value = "AD_TYPE_FLAT 平面 AD_TYPE_VIDEO 视频 AD_TYPE_PRAISE E赞 AD_TYPE_PACKET红包")
	private AdTypeEnum adTypeEnum;

	@ApiParam (name="adCreateTime", value = "广告创建时间")
	private Date adCreateTime;

	@ApiParam(value = " AD_STATUS_ADD 上架 AD_STATUS_PUTING 投放中  AD_STATUS_PUTED 投放结束")
	private AdStatusEnum adStatusEnum;

	@ApiParam (name="adTotalPoint", value = "广告投放总积分")
	private BigDecimal adTotalPoint;

	@ApiParam (name="userTotalPoint", value = "用户受益总积分")
	private BigDecimal userTotalPoint;

	@ApiParam (name="loveTotalPoint", value = "爱心账户总积分")
	private BigDecimal loveTotalPoint;

	@ApiParam(value = " ANOMALY 异常 NORMAL 正常")
	private ReportAdEarningsStatusEnum reportAdEarningsStatusEnum;

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	
	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
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

	
}

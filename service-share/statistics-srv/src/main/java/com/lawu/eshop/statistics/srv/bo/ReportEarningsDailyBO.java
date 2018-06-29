package com.lawu.eshop.statistics.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

public class ReportEarningsDailyBO {

	/**
	 *
	 * 主键 report_earnings_daily.id
	 *
	 * @mbg.generated
	 */
	private Long id;

	/**
	 *
	 * 广告总投放积分 report_earnings_daily.ad_point
	 *
	 * @mbg.generated
	 */
	private BigDecimal adPoint;

	/**
	 *
	 * 用户总收益 report_earnings_daily.user_point
	 *
	 * @mbg.generated
	 */
	private BigDecimal userPoint;

	/**
	 *
	 * 爱心账户总收益 report_earnings_daily.love_point
	 *
	 * @mbg.generated
	 */
	private BigDecimal lovePoint;

	/**
	 *
	 * 平台总收益 report_earnings_daily.platform_point
	 *
	 * @mbg.generated
	 */
	private BigDecimal platformPoint;

	/**
	 *
	 * 统计数据所属日期 report_earnings_daily.gmt_report
	 *
	 * @mbg.generated
	 */
	private Date gmtReport;

	/**
	 *
	 * 统计时间 report_earnings_daily.gmt_create
	 *
	 * @mbg.generated
	 */
	private Date gmtCreate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAdPoint() {
		return adPoint;
	}

	public void setAdPoint(BigDecimal adPoint) {
		this.adPoint = adPoint;
	}

	public BigDecimal getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(BigDecimal userPoint) {
		this.userPoint = userPoint;
	}

	public BigDecimal getLovePoint() {
		return lovePoint;
	}

	public void setLovePoint(BigDecimal lovePoint) {
		this.lovePoint = lovePoint;
	}

	public BigDecimal getPlatformPoint() {
		return platformPoint;
	}

	public void setPlatformPoint(BigDecimal platformPoint) {
		this.platformPoint = platformPoint;
	}

	public Date getGmtReport() {
		return gmtReport;
	}

	public void setGmtReport(Date gmtReport) {
		this.gmtReport = gmtReport;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

}

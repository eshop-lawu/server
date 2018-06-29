package com.lawu.eshop.statistics.param;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.statistics.constants.ReportTypeEnum;

/**
 * 平台总销量保存参数
 * 
 * @author Sunny
 * @date 2017年6月30日
 */
public class PlatformTotalSalesSaveParam {
	
	/**
	 * 报表类型
	 */
	private ReportTypeEnum type;
	
	/**
	 * 买单金额
	 */
	private BigDecimal payOrderAmount;
	
	/**
	 * 订单金额
	 */
	private BigDecimal shoppingOrderAmount;

	/**
	 * 统计时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date gmtReport;
	
	public ReportTypeEnum getType() {
		return type;
	}

	public void setType(ReportTypeEnum type) {
		this.type = type;
	}

	public BigDecimal getPayOrderAmount() {
		return payOrderAmount;
	}

	public void setPayOrderAmount(BigDecimal payOrderAmount) {
		this.payOrderAmount = payOrderAmount;
	}

	public BigDecimal getShoppingOrderAmount() {
		return shoppingOrderAmount;
	}

	public void setShoppingOrderAmount(BigDecimal shoppingOrderAmount) {
		this.shoppingOrderAmount = shoppingOrderAmount;
	}

	public Date getGmtReport() {
		return gmtReport;
	}

	public void setGmtReport(Date gmtReport) {
		this.gmtReport = gmtReport;
	}
	
}

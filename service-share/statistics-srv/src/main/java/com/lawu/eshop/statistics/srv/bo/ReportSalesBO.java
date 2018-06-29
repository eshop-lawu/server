package com.lawu.eshop.statistics.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 平台总销量BO
 * 
 * @author Sunny
 * @date 2017年6月30日
 */
public class ReportSalesBO {
	
    /**
     * 主键
     */
    private Long id;

    /**
     * 买单金额
     */
    private BigDecimal payOrderAmount;

    /**
     * 购物订单金额
     */
    private BigDecimal shoppingOrderAmount;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 统计时间
     */
    private Date gmtReport;

    /**
     * 创建时间
     */
    private Date gmtCreate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
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
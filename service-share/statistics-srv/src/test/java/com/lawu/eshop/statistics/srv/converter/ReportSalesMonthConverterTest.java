package com.lawu.eshop.statistics.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.statistics.srv.domain.ReportSalesMonthDO;

public class ReportSalesMonthConverterTest {
	
	@Test
	public void convertDOtoBo() {
		try {
			ReportSalesMonthDO reportSalesMonthDO = new ReportSalesMonthDO();
			reportSalesMonthDO.setGmtCreate(new Date());
			reportSalesMonthDO.setGmtReport(new Date());
			reportSalesMonthDO.setPayOrderAmount(new BigDecimal(10));
			reportSalesMonthDO.setShoppingOrderAmount(new BigDecimal(20));
			reportSalesMonthDO.setTotalAmount(new BigDecimal(30));
			ReportSalesMonthConverter.convert(reportSalesMonthDO);
			reportSalesMonthDO = null;
			ReportSalesMonthConverter.convert(reportSalesMonthDO);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	
	@Test
	public void convertReportSalesBOList() {
		try {
			List<ReportSalesMonthDO> reportSalesMonthDOList = new ArrayList<ReportSalesMonthDO>();
			ReportSalesMonthConverter.convertReportSalesBOList(reportSalesMonthDOList);
			ReportSalesMonthDO reportSalesDailyDO = new ReportSalesMonthDO();
			reportSalesMonthDOList.add(reportSalesDailyDO);
			ReportSalesMonthConverter.convertReportSalesBOList(reportSalesMonthDOList);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
}

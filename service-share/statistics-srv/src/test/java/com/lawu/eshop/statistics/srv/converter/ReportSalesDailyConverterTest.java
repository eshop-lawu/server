package com.lawu.eshop.statistics.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.lawu.eshop.statistics.srv.domain.ReportSalesDailyDO;

public class ReportSalesDailyConverterTest {

	@Test
	public void convert1() {
		ReportSalesDailyDO reportSalesDailyDO = new ReportSalesDailyDO();
		reportSalesDailyDO.setPayOrderAmount(new BigDecimal(10));
		reportSalesDailyDO.setShoppingOrderAmount(new BigDecimal(20));
		reportSalesDailyDO.setTotalAmount(new BigDecimal(30));
		reportSalesDailyDO.setGmtCreate(new Date());
		reportSalesDailyDO.setGmtReport(new Date());
		ReportSalesDailyConverter.convert(reportSalesDailyDO);
		reportSalesDailyDO = null;
		ReportSalesDailyConverter.convert(reportSalesDailyDO);
	}
	
	@Test
	public void convertReportSalesBOList() {
		List<ReportSalesDailyDO> reportSalesDailyDOList = new ArrayList<ReportSalesDailyDO>();
		ReportSalesDailyConverter.convertReportSalesBOList(reportSalesDailyDOList);
		for(int i = 0; i < 10; i++) {
			ReportSalesDailyDO reportSalesDailyDO = new ReportSalesDailyDO();
			reportSalesDailyDO.setPayOrderAmount(new BigDecimal(10));
			reportSalesDailyDO.setShoppingOrderAmount(new BigDecimal(20));
			reportSalesDailyDO.setTotalAmount(new BigDecimal(30));
			reportSalesDailyDO.setGmtCreate(new Date());
			reportSalesDailyDO.setGmtReport(new Date());
			reportSalesDailyDOList.add(reportSalesDailyDO);
		}
		ReportSalesDailyConverter.convertReportSalesBOList(reportSalesDailyDOList);
	}
}

package com.lawu.eshop.statistics.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.lawu.eshop.statistics.constants.ReportTypeEnum;
import com.lawu.eshop.statistics.param.PlatformTotalSalesQueryParam;
import com.lawu.eshop.statistics.srv.bo.ReportSalesBO;
import com.lawu.utils.DateUtil;

public class ReportSalesConverterTest {

	
	@Test
	public void convert() {
		
		ReportSalesBO reportSalesBO = new ReportSalesBO();
		reportSalesBO.setPayOrderAmount(new BigDecimal(10));
		reportSalesBO.setShoppingOrderAmount(new BigDecimal(20));
		ReportSalesConverter.convert(reportSalesBO);
	}
	
	@Test
	public void convert1() {
		List<ReportSalesBO> list = new ArrayList<ReportSalesBO>();
		for(int i = 0; i < 10; i++) {
			ReportSalesBO reportSalesBO = new ReportSalesBO();
			reportSalesBO.setPayOrderAmount(new BigDecimal(10));
			reportSalesBO.setShoppingOrderAmount(new BigDecimal(20));
			reportSalesBO.setGmtReport(new Date());
			list.add(reportSalesBO);
		}
		PlatformTotalSalesQueryParam platformTotalSalesQueryParam = new PlatformTotalSalesQueryParam();
		platformTotalSalesQueryParam.setEnd(new Date());
		platformTotalSalesQueryParam.setStart(DateUtil.getDayBefore(new Date()));
		platformTotalSalesQueryParam.setType(ReportTypeEnum.DAILY);
		ReportSalesConverter.convert(list, platformTotalSalesQueryParam);
		platformTotalSalesQueryParam.setType(ReportTypeEnum.MONTH);
		ReportSalesConverter.convert(list, platformTotalSalesQueryParam);
	}
}

package com.lawu.eshop.statistics.srv.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.statistics.constants.ReportTypeEnum;
import com.lawu.eshop.statistics.param.PlatformTotalSalesQueryParam;
import com.lawu.eshop.statistics.param.PlatformTotalSalesSaveParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.bo.ReportSalesBO;
import com.lawu.eshop.statistics.srv.mapper.ReportSalesDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportSalesMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportSalesService;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class ReportSalesServiceImplTest {

	
	@Autowired
	private ReportSalesService reportSalesService;
	
	@Autowired
	private ReportSalesDailyDOMapper reportSalesDailyDOMapper;

	@Autowired
	private ReportSalesMonthDOMapper reportSalesMonthDOMapper;
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void save() {
		PlatformTotalSalesSaveParam param = new PlatformTotalSalesSaveParam();
		param.setType(ReportTypeEnum.DAILY);
		param.setPayOrderAmount(new BigDecimal(10));
		param.setShoppingOrderAmount(new BigDecimal(100));
		param.setGmtReport(new Date());
		reportSalesService.save(param);
		param.setType(ReportTypeEnum.MONTH);
		reportSalesService.save(param);
		long i = reportSalesDailyDOMapper.countByExample(null);
		long j = reportSalesMonthDOMapper.countByExample(null);
		Assert.assertEquals(1L, i);
		Assert.assertEquals(1L, j);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void list() {
		save();
		PlatformTotalSalesQueryParam param = new PlatformTotalSalesQueryParam();
		param.setType(ReportTypeEnum.MONTH);
		param.setStart(DateUtil.getFirstDayOfMonth());
		param.setEnd(DateUtil.getLastDayOfMonth());
		List<ReportSalesBO> list = reportSalesService.list(param);
		Assert.assertEquals(1, list.size());
		param.setType(ReportTypeEnum.MONTH);
		list = reportSalesService.list(param);
		Assert.assertEquals(1, list.size());
	}
	
}

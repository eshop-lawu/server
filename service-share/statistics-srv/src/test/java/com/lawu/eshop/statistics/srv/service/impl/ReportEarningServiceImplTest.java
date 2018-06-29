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

import com.lawu.eshop.statistics.param.ReportEarningParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.bo.ReportCommonEarningsBO;
import com.lawu.eshop.statistics.srv.bo.ReportEarningsDailyBO;
import com.lawu.eshop.statistics.srv.mapper.ReportEarningsDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportEarningsMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportEarningService;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class ReportEarningServiceImplTest {
	
	@Autowired
	private ReportEarningService reportEarningService;
	
	@Autowired
	private ReportEarningsDailyDOMapper reportEarningsDailyDOMapper;
	
	@Autowired
	private ReportEarningsMonthDOMapper reportEarningsMonthDOMapper;

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveDaily() {
		ReportEarningParam reportEarningParam = new ReportEarningParam();
		reportEarningParam.setAdPoint(new BigDecimal(10));
		reportEarningParam.setLovePoint(new BigDecimal(10));
		reportEarningParam.setPlatformPoint(new BigDecimal(10));
		reportEarningParam.setUserPoint(new BigDecimal(10));
		reportEarningParam.setGmtReport(new Date());
		reportEarningService.saveDaily(reportEarningParam);
		long i = reportEarningsDailyDOMapper.countByExample(null);
		Assert.assertEquals(1L, i);
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveMonth() {
		ReportEarningParam reportEarningParam = new ReportEarningParam();
		reportEarningParam.setAdPoint(new BigDecimal(10));
		reportEarningParam.setLovePoint(new BigDecimal(10));
		reportEarningParam.setPlatformPoint(new BigDecimal(10));
		reportEarningParam.setUserPoint(new BigDecimal(10));
		reportEarningParam.setGmtReport(DateUtil.getFirstDayOfMonth());
		reportEarningService.saveMonth(reportEarningParam);
		long i = reportEarningsMonthDOMapper.countByExample(null);
		Assert.assertEquals(1L, i);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getDailyList() {
		
		ReportEarningParam reportEarningParam = new ReportEarningParam();
		reportEarningParam.setAdPoint(new BigDecimal(10));
		reportEarningParam.setLovePoint(new BigDecimal(10));
		reportEarningParam.setPlatformPoint(new BigDecimal(10));
		reportEarningParam.setUserPoint(new BigDecimal(10));
		reportEarningParam.setGmtReport(new Date());
		
		reportEarningService.saveDaily(reportEarningParam);
		reportEarningService.saveDaily(reportEarningParam);
		reportEarningService.saveDaily(reportEarningParam);
		
		List<ReportEarningsDailyBO> list = reportEarningService.getDailyList(DateUtil.getDateFormat(new Date(), "yyyy-MM"));
		Assert.assertEquals(3, list.size());
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectReport() {
		
		ReportEarningParam reportEarningParam = new ReportEarningParam();
		reportEarningParam.setAdPoint(new BigDecimal(10));
		reportEarningParam.setLovePoint(new BigDecimal(10));
		reportEarningParam.setPlatformPoint(new BigDecimal(10));
		reportEarningParam.setUserPoint(new BigDecimal(10));
		reportEarningParam.setGmtReport(new Date());
		
		reportEarningService.saveDaily(reportEarningParam);
		reportEarningService.saveDaily(reportEarningParam);
		reportEarningService.saveDaily(reportEarningParam);
		saveMonth();
		String bdate = "";
		String edate = "";
		ReportCommonEarningsBO rCeb1 = reportEarningService.selectReport(bdate, edate);
		
		bdate = "2017-7-1";
		edate = "2017-7-31";
		ReportCommonEarningsBO rCeb2 = reportEarningService.selectReport(bdate, edate);
		
		bdate = "2017-7";
		edate = "2017-7";
		ReportCommonEarningsBO rCeb3 = reportEarningService.selectReport(bdate, edate);
	}
}

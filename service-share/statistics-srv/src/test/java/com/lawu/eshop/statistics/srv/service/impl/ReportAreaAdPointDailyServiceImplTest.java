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

import com.lawu.eshop.statistics.param.AgentSelectAreaAdPointParam;
import com.lawu.eshop.statistics.param.ReportAreaAdPointDailyParams;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.bo.ReportAreaAdPointDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaAdPointMonthBO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaAdPointDailyDO;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaAdPointDailyDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportAreaAdPointDailyService;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class ReportAreaAdPointDailyServiceImplTest {

	@Autowired
	private ReportAreaAdPointDailyService reportAreaAdPointDailyService;
	
	@Autowired
	private ReportAreaAdPointDailyDOMapper reportAreaAdPointDailyDOMapper;
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void insertReportAreaAdPointDaily() {
		ReportAreaAdPointDailyParams param = new ReportAreaAdPointDailyParams();
		param.setAreaId(1);
		param.setCityId(1);
		param.setProvinceId(1);
		param.setGmtCreate(new Date());
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		param.setProvinceId(1);
		param.setReportTotalPoint(new BigDecimal(1));
		reportAreaAdPointDailyService.insertReportAreaAdPointDaily(param);
		List<ReportAreaAdPointDailyDO> list = reportAreaAdPointDailyDOMapper.selectByExample(null);
		Assert.assertEquals(1, list.size());
	}
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void deleteReportAreaAdPointDaily() {
		insertReportAreaAdPointDaily();
		List<ReportAreaAdPointDailyDO> list = reportAreaAdPointDailyDOMapper.selectByExample(null);
		Assert.assertEquals(1, list.size());
		reportAreaAdPointDailyService.deleteReportAreaAdPointDaily(list.get(0).getId());
		list = reportAreaAdPointDailyDOMapper.selectByExample(null);
		Assert.assertEquals(0, list.size());
		
	}
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void selectReportAreaAdPointDaily() {
		insertReportAreaAdPointDaily();
		AgentSelectAreaAdPointParam param = new AgentSelectAreaAdPointParam();
		param.setCityId(1);
		param.setBdate(DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		param.setEdate(DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		List<ReportAreaAdPointDailyBO> list = reportAreaAdPointDailyService.selectReportAreaAdPointDaily(param);
		Assert.assertEquals(1, list.size());
	}
	
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void selectReportAreaAdPointDaily2() {
		insertReportAreaAdPointDaily();
		List<ReportAreaAdPointDailyBO> list = reportAreaAdPointDailyService.selectReportAreaAdPointDaily(1, DateUtil.getDateFormat(DateUtil.getMonthBefore(new Date())));
		Assert.assertEquals(1, list.size());
	}
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void selectReportAreaAdPointDailyInMonth() {
		insertReportAreaAdPointDaily();
		List<ReportAreaAdPointMonthBO> list = reportAreaAdPointDailyService.selectReportAreaAdPointDailyInMonth(DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()))), DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		Assert.assertEquals(1, list.size());
	}
}

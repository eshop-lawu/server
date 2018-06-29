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

import com.lawu.eshop.statistics.param.ReportAreaVolumnDailyParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.bo.ReportAreaVolumeDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaVolumnDailyInMonthBO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaVolumeDailyDO;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaVolumeDailyDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportAreaVolumnDailyService;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class ReportAreaVolumnDailyServiceImplTest {

	
	@Autowired
	private ReportAreaVolumnDailyService reportAreaVolumnDailyService;
	
	@Autowired
	private ReportAreaVolumeDailyDOMapper reportAreaVolumeDailyDOMapper;
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void insertReportAreaVolumnDaily() {
		ReportAreaVolumnDailyParam param = new ReportAreaVolumnDailyParam();
		param.setAreaId(1);
		param.setCityId(1);
		param.setProvinceId(1);
		param.setReportTotalMoney(new BigDecimal(1));
		param.setType((byte)1);
		param.setGmtCreate(new Date());
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		reportAreaVolumnDailyService.insertReportAreaVolumnDaily(param);
		List<ReportAreaVolumeDailyDO> list = reportAreaVolumeDailyDOMapper.selectByExample(null);
		Assert.assertEquals(1, list.size());
	}
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void selectReportAreaVolumeDailyInMonth() {
		insertReportAreaVolumnDaily();
		List<ReportAreaVolumnDailyInMonthBO> list = reportAreaVolumnDailyService.selectReportAreaVolumeDailyInMonth(DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()))), DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		Assert.assertEquals(1, list.size());
	}
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void selectReportAreaVolumnDaily() {
		insertReportAreaVolumnDaily();
		List<ReportAreaVolumeDailyBO> list = reportAreaVolumnDailyService.selectReportAreaVolumnDaily(1, DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()))), DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		Assert.assertEquals(1, list.size());
	}
}

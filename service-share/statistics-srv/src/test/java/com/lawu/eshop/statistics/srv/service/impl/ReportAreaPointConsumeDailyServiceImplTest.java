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

import com.lawu.eshop.statistics.param.ReportAreaPointConsumeDailyParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.bo.ReportAreaPointConsumeDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaPointConsumeDailyInMonthBO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaPointConsumeDailyDO;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaPointConsumeDailyDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportAreaPointConsumeDailyService;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class ReportAreaPointConsumeDailyServiceImplTest {

	@Autowired
	private ReportAreaPointConsumeDailyService reportAreaPointConsumeDailyService;
	
	@Autowired
	private ReportAreaPointConsumeDailyDOMapper  reportAreaPointConsumeDailyDOMapper;
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void insertReportAreaAdPointDaily() {
		ReportAreaPointConsumeDailyParam param = new ReportAreaPointConsumeDailyParam();
		param.setAreaId(1);
		param.setCityId(1);
		param.setProvinceId(1);
		param.setGmtCreate(new Date());
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		param.setMemberPoint(new BigDecimal(1));
		param.setMemberRechargePoint(new BigDecimal(1));
		param.setMerchantPoint(new BigDecimal(1));
		param.setMerchantRechargePoint(new BigDecimal(1));
		param.setTotalPoint(new BigDecimal(2));
		param.setTotalRechargePoint(new BigDecimal(2));
		reportAreaPointConsumeDailyService.insertReportAreaPointConsumeDaily(param);
		List<ReportAreaPointConsumeDailyDO> list = reportAreaPointConsumeDailyDOMapper.selectByExample(null);
		Assert.assertEquals(1, list.size());
	}
	
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void selectReportAreaPointConsumeDailyInMonth() {
		insertReportAreaAdPointDaily();
		List<ReportAreaPointConsumeDailyInMonthBO> list = reportAreaPointConsumeDailyService.selectReportAreaPointConsumeDailyInMonth(DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()))), DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		Assert.assertEquals(1, list.size());
	}
	
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void getReportAreaPointConsumeDaily() {
		insertReportAreaAdPointDaily();
		List<ReportAreaPointConsumeDailyBO> list = reportAreaPointConsumeDailyService.getReportAreaPointConsumeDaily(1, DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()))), DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		Assert.assertEquals(1, list.size());
	}
}

package com.lawu.eshop.statistics.srv.service.impl;

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

import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.bo.ReportAreaUserRegDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaUserRegMonthBO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaUserRegDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaUserRegMonthDO;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaUserRegDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaUserRegMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportAreaUserRegService;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class ReportAreaUserRegServiceImplTest {

	@Autowired
	private ReportAreaUserRegService reportAreaUserRegService;
	
	@Autowired
    private ReportAreaUserRegDailyDOMapper reportAreaUserRegDailyDOMapper;

    @Autowired
    private ReportAreaUserRegMonthDOMapper reportAreaUserRegMonthDOMapper;
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void getUserRegListDaily() {
		ReportAreaUserRegDailyDO DO = new ReportAreaUserRegDailyDO();
		DO.setCityId(1);
		DO.setCityName("深圳市");
		DO.setGmtCreate(new Date());
		DO.setGmtReport(DateUtil.getMonthBefore(new Date()));
		DO.setMemberCount(1);
		DO.setMerchantCount(1);
		DO.setMerchantEntityCount(1);
		DO.setMerchantNormalCount(1);
		reportAreaUserRegDailyDOMapper.insertSelective(DO);
		AgentReportParam param = new AgentReportParam();
		param.setBeginTime(DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		param.setEndTime(DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		param.setRegionPath("1/1/1");
		List<ReportAreaUserRegDailyBO> list = reportAreaUserRegService.getUserRegListDaily(param);
		Assert.assertEquals(1, list.size());
	}
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void getUserRegListMonth() {
		ReportAreaUserRegMonthDO DO = new ReportAreaUserRegMonthDO();
		DO.setCityId(1);
		DO.setCityName("深圳市");
		DO.setGmtCreate(new Date());
		DO.setGmtReport(DateUtil.getMonthBefore(new Date()));
		DO.setMemberCount(1);
		DO.setMerchantCount(1);
		DO.setMerchantEntityCount(1);
		DO.setMerchantNormalCount(1);
		reportAreaUserRegMonthDOMapper.insertSelective(DO);
		AgentReportParam param = new AgentReportParam();
		param.setBeginTime(DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		param.setEndTime(DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		param.setRegionPath("1/1/1");
		List<ReportAreaUserRegMonthBO> list = reportAreaUserRegService.getUserRegListMonth(param);
		Assert.assertEquals(1, list.size());
	}
	
}

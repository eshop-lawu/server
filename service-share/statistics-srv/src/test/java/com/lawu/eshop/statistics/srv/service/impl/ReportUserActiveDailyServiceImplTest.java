package com.lawu.eshop.statistics.srv.service.impl;

import java.util.ArrayList;
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

import com.lawu.eshop.statistics.dto.UserActiveDTO;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveAreaDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveAreaMonthBO;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveBO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveDailyDO;
import com.lawu.eshop.statistics.srv.mapper.ReportUserActiveDailyDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportUserActiveAreaDailyService;
import com.lawu.eshop.statistics.srv.service.ReportUserActiveAreaMonthService;
import com.lawu.eshop.statistics.srv.service.ReportUserActiveDailyService;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class ReportUserActiveDailyServiceImplTest {

	@Autowired
	private ReportUserActiveDailyService reportUserActiveDailyService;

	@Autowired
	private ReportUserActiveDailyDOMapper reportUserActiveDailyDOMapper;
	
	@Autowired
	private ReportUserActiveAreaDailyService reportUserActiveAreaDailyService;

	@Autowired
	private ReportUserActiveAreaMonthService reportUserActiveAreaMonthService;
	
//	@Transactional(rollbackFor = Exception.class)
//	@Rollback
//	@Test
//	public void saveUserActiveDaily() {
//		Integer memberCount = 10;
//		Integer merchantCount = 20;
//		reportUserActiveDailyService.saveUserActiveDaily(memberCount, merchantCount);
//		int i = reportUserActiveDailyDOMapper.countByExample(null);
//		Assert.assertEquals(1, i);
//	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getUserActiveListDaily() {
		ReportUserActiveDailyDO userActiveDailyDO = new ReportUserActiveDailyDO();
        userActiveDailyDO.setMemberCount(10);
        userActiveDailyDO.setMerchantCount(20);
        userActiveDailyDO.setGmtCreate(new Date());
        userActiveDailyDO.setGmtReport(DateUtil.getDayAfter(DateUtil.getNowDate()));
        reportUserActiveDailyDOMapper.insertSelective(userActiveDailyDO);
		String beginTime = "";
		String endTime = "";
		beginTime = DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth());
		endTime = DateUtil.getDateFormat(DateUtil.getLastDayOfMonth());
		List<ReportUserActiveBO> list = reportUserActiveDailyService.getUserActiveListDaily(beginTime, endTime);
		Assert.assertEquals(1, list.size());
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getReportUserActiveAreaDailyList() {
		Date date = DateUtil.getDayBefore(DateUtil.getNowDate());
		String reportDate = DateUtil.getDateFormat(date);
		List<ReportUserActiveAreaDailyBO> list = reportUserActiveDailyService.getReportUserActiveAreaDailyList(reportDate);
		Assert.assertEquals(0, list.size());
	}

	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getAgentUserActiveListDaily() {
		List<UserActiveDTO> userActiveDTOS = new ArrayList<UserActiveDTO>();
		for(int i = 0 ; i < 5 ; i++) {
			UserActiveDTO dto = new UserActiveDTO();
			dto.setCityId(4403);
			dto.setCityName("深圳市");
			dto.setUserCount(i);
			dto.setVisitDate(new Date());
			userActiveDTOS.add(dto);
		}
		reportUserActiveAreaDailyService.saveUserActiveAreaDaily(userActiveDTOS);
		
		AgentReportParam param = new AgentReportParam();
		param.setRegionPath("1/4403/1");
		param.setBeginTime(DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth()));
		param.setEndTime(DateUtil.getDateFormat(DateUtil.getLastDayOfMonth()));
		List<ReportUserActiveAreaDailyBO> list = reportUserActiveDailyService.getAgentUserActiveListDaily(param);
		Assert.assertEquals(5, list.size());
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getAgentUserActiveListMonth() {
		
		List<UserActiveDTO> list = new ArrayList<>();
		UserActiveDTO dto = new UserActiveDTO();
		dto.setCityId(4403);
		dto.setCityName("");
		dto.setUserCount(1);
		dto.setVisitDate(new Date());
		list.add(dto);
		reportUserActiveAreaMonthService.saveUserActiveAreaMonth(list);
		AgentReportParam param = new AgentReportParam();
		param.setRegionPath("1/4403/1");
		param.setBeginTime(DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth()));
		param.setEndTime(DateUtil.getDateFormat(DateUtil.getLastDayOfMonth()));
		
		List<ReportUserActiveAreaMonthBO> list1 = reportUserActiveDailyService.getAgentUserActiveListMonth(param);
		Assert.assertEquals(1, list.size());
	}
}

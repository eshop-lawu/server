package com.lawu.eshop.statistics.srv.service.impl;

import java.math.BigDecimal;
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

import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.param.AgentReportRechargeSaveParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.bo.AgentAreaRechargeQReturnBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaRechargeDailyBO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaRechargeDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaRechargeMonthDO;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaRechargeDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaRechargeMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportAreaRechargeService;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class ReportAreaRechargeServiceImplTest {

	
	@Autowired
	private ReportAreaRechargeService reportAreaRechargeService;
	
	@Autowired
	private ReportAreaRechargeDailyDOMapper  reportAreaRechargeDailyDOMapper;
	
	@Autowired
	private ReportAreaRechargeMonthDOMapper  reportAreaRechargeMonthDOMapper;
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void saveDaily() {
		List<AgentReportRechargeSaveParam> saveParams = new ArrayList<>();
		AgentReportRechargeSaveParam param = new AgentReportRechargeSaveParam();
		param.setAreaId(1);
		param.setCityId(1);
		param.setProvinceId(1);
		param.setGmtCreate(new Date());
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		param.setMemberRechargeBalance(new BigDecimal(1));
		param.setMemberRechargePoint(new BigDecimal(1));
		param.setMerchantRechargeBalance(new BigDecimal(1));
		param.setMerchantRechargePoint(new BigDecimal(1));
		param.setTotalRechargeBalance(new BigDecimal(2));
		param.setTotalRechargePoint(new BigDecimal(2));
		saveParams.add(param);
		reportAreaRechargeService.saveDaily(saveParams);
		List<ReportAreaRechargeDailyDO> list = reportAreaRechargeDailyDOMapper.selectByExample(null);
		Assert.assertEquals(1, list.size());
	}
	
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void saveMonth() {
		List<AgentReportRechargeSaveParam> saveParams = new ArrayList<>();
		AgentReportRechargeSaveParam param = new AgentReportRechargeSaveParam();
		param.setAreaId(1);
		param.setCityId(1);
		param.setProvinceId(1);
		param.setGmtCreate(new Date());
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		param.setMemberRechargeBalance(new BigDecimal(1));
		param.setMemberRechargePoint(new BigDecimal(1));
		param.setMerchantRechargeBalance(new BigDecimal(1));
		param.setMerchantRechargePoint(new BigDecimal(1));
		param.setTotalRechargeBalance(new BigDecimal(2));
		param.setTotalRechargePoint(new BigDecimal(2));
		saveParams.add(param);
		reportAreaRechargeService.saveMonth(saveParams);
		List<ReportAreaRechargeMonthDO> list = reportAreaRechargeMonthDOMapper.selectByExample(null);
		Assert.assertEquals(1, list.size());
	}
	
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void deleteDailyByReportDate() {
		saveDaily();
		reportAreaRechargeService.deleteDailyByReportDate(DateUtil.getDateFormat(DateUtil.getMonthBefore(new Date())));
		List<ReportAreaRechargeDailyDO> list = reportAreaRechargeDailyDOMapper.selectByExample(null);
		Assert.assertEquals(0, list.size());
	}
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void deleteMonthByReportDate() {
		saveMonth();
		reportAreaRechargeService.deleteMonthByReportDate(DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		reportAreaRechargeMonthDOMapper.selectByExample(null);
	}
	
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void getAreaRechargeList() {
		saveDaily();
		AgentReportParam param = new AgentReportParam();
		param.setRegionPath("1/1/1");
		param.setBeginTime(DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		param.setEndTime(DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		AgentAreaRechargeQReturnBO bo = reportAreaRechargeService.getAreaRechargeList(param);
		Assert.assertEquals(1, (int)bo.getCityId());
	}
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void getDailyList() {
		for(int i = 0 ; i < 2 ; i++) {
			List<AgentReportRechargeSaveParam> saveParams = new ArrayList<>();
			AgentReportRechargeSaveParam param = new AgentReportRechargeSaveParam();
			param.setAreaId(1);
			param.setCityId(1);
			param.setProvinceId(1);
			param.setGmtCreate(new Date());
			param.setGmtReport(DateUtil.getMonthBefore(new Date()));
			param.setMemberRechargeBalance(new BigDecimal(1));
			param.setMemberRechargePoint(new BigDecimal(1));
			param.setMerchantRechargeBalance(new BigDecimal(1));
			param.setMerchantRechargePoint(new BigDecimal(1));
			param.setTotalRechargeBalance(new BigDecimal(2));
			param.setTotalRechargePoint(new BigDecimal(2));
			saveParams.add(param);
			reportAreaRechargeService.saveDaily(saveParams);
		}
		AgentReportParam param = new AgentReportParam();
		param.setRegionPath("1/1/1");
		param.setBeginTime(DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		param.setEndTime(DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		List<ReportAreaRechargeDailyBO> list = reportAreaRechargeService.getDailyList(DateUtil.getDateFormat(new Date()));
	}
}

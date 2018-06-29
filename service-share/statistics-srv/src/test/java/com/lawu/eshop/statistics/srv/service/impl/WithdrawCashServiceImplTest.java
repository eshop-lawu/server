package com.lawu.eshop.statistics.srv.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import com.lawu.eshop.statistics.param.AgentWithdrawCashParam;
import com.lawu.eshop.statistics.param.ReportKCommonParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.bo.ReportAreaWithdrawDailyBO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaWithdrawDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaWithdrawMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportWithdrawDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportWithdrawMonthDO;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaWithdrawDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaWithdrawMonthDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportWithdrawDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportWithdrawMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.WithdrawCashService;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class WithdrawCashServiceImplTest {

	@Autowired
	private WithdrawCashService WithdrawCashService;
	
	@Autowired
	private ReportWithdrawDailyDOMapper reportWithdrawDailyDOMapper;
	
	@Autowired
	private ReportWithdrawMonthDOMapper reportWithdrawMonthDOMapper;
	
	@Autowired
	private ReportAreaWithdrawDailyDOMapper reportAreaWithdrawDailyDOMapper;
	
	@Autowired
	private ReportAreaWithdrawMonthDOMapper reportAreaWithdrawMonthDOMapper;
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveDaily() {
		ReportKCommonParam param = new ReportKCommonParam();
		param.setMemberMoney(new BigDecimal(10));
		param.setMerchantMoney(new BigDecimal(10));
		param.setTotalMoney(new BigDecimal(20));
		param.setGmtCreate(new Date());
		param.setGmtReport(new Date());
		WithdrawCashService.saveDaily(param);
		List<ReportWithdrawDailyDO> list = reportWithdrawDailyDOMapper.selectByExample(null);
		Assert.assertEquals(1, list.size());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveMonth() {
		ReportKCommonParam param = new ReportKCommonParam();
		param.setMemberMoney(new BigDecimal(10));
		param.setMerchantMoney(new BigDecimal(10));
		param.setTotalMoney(new BigDecimal(20));
		param.setGmtCreate(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date())));
		param.setGmtReport(new Date());
		WithdrawCashService.saveMonth(param);
		List<ReportWithdrawMonthDO> list = reportWithdrawMonthDOMapper.selectByExample(null);
		Assert.assertEquals(1, list.size());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getDailyList() {
		saveDaily();
		String reportDate = "2017-7";
		WithdrawCashService.getDailyList(reportDate);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void deleteDailyByReportDate() {
		saveDaily();
		WithdrawCashService.deleteDailyByReportDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		List<ReportWithdrawDailyDO> list = reportWithdrawDailyDOMapper.selectByExample(null);
		Assert.assertEquals(0, list.size());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void deleteMonthByReportDate() {
		saveMonth();
		WithdrawCashService.deleteMonthByReportDate(new SimpleDateFormat("yyyy-MM-dd").format(DateUtil.getMonthBefore(new Date())));
		List<ReportWithdrawDailyDO> list = reportWithdrawDailyDOMapper.selectByExample(null);
		Assert.assertEquals(0, list.size());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectReport() {
		saveDaily();
		saveMonth();
		String bdate = "";
		String edate = "";
		WithdrawCashService.selectReport(bdate, edate);
		bdate = new SimpleDateFormat("yyyy-MM-dd").format(DateUtil.getFirstDayOfMonth());
		edate = new SimpleDateFormat("yyyy-MM-dd").format(DateUtil.getFirstDayOfMonth());
		WithdrawCashService.selectReport(bdate, edate);
		bdate = new SimpleDateFormat("yyyy-MM").format(DateUtil.getMonthBefore(new Date()));
		edate = new SimpleDateFormat("yyyy-MM").format(new Date());
		WithdrawCashService.selectReport(bdate, edate);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveAgentDaily() {
		AgentWithdrawCashParam param = new AgentWithdrawCashParam();
		param.setCityId(1);
		param.setCityName("");
		param.setGmtReport(new Date());
		param.setMemberMoney(new BigDecimal(1));
		param.setMerchantMoney(new BigDecimal(1));
		param.setTotalMoney(new BigDecimal(2));
		WithdrawCashService.saveAgentDaily(param);
		List<ReportAreaWithdrawDailyDO> list = reportAreaWithdrawDailyDOMapper.selectByExample(null);
		Assert.assertEquals(1, list.size());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectReportAreaWithdrawCashList() {
		saveAgentDaily();
		List<ReportAreaWithdrawDailyBO> list = WithdrawCashService.selectReportAreaWithdrawCashList(DateUtil.getDateFormat(new Date(), "yyyy-MM"), 1);
		Assert.assertEquals(1, list.size());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveAgentMonth() {
		AgentWithdrawCashParam param = new AgentWithdrawCashParam();
		param.setCityId(1);
		param.setCityName("");
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		param.setMemberMoney(new BigDecimal(1));
		param.setMerchantMoney(new BigDecimal(1));
		param.setTotalMoney(new BigDecimal(2));
		WithdrawCashService.saveAgentMonth(param);
		List<ReportAreaWithdrawMonthDO> list = reportAreaWithdrawMonthDOMapper.selectByExample(null);
		Assert.assertEquals(1, list.size());
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectAreaWithdrawDailyReport() {
		saveAgentDaily();
		AgentReportParam param = new AgentReportParam();
		param.setBeginTime(DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth()));
		param.setEndTime(DateUtil.getDateFormat(DateUtil.getLastDayOfMonth()));
		param.setRegionPath("1/1/1");
		WithdrawCashService.selectAreaWithdrawDailyReport(param);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getLastReportWithdraw() {
		ReportWithdrawDailyDO DO = new ReportWithdrawDailyDO();
		DO.setGmtCreate(new Date());
		DO.setGmtReport(new Date());
		DO.setMemberMoney(new BigDecimal("0"));
		DO.setMerchantMoney(new BigDecimal("0"));
		DO.setTotalMoney(new BigDecimal("0"));
		reportWithdrawDailyDOMapper.insertSelective(DO);
		WithdrawCashService.getLastReportWithdraw();
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getLastReportWithdrawMonth() {
		ReportWithdrawMonthDO DO = new ReportWithdrawMonthDO();
		DO.setGmtCreate(new Date());
		DO.setGmtReport(new Date());
		DO.setMemberMoney(new BigDecimal("0"));
		DO.setMerchantMoney(new BigDecimal("0"));
		DO.setTotalMoney(new BigDecimal("0"));
		reportWithdrawMonthDOMapper.insertSelective(DO);
		WithdrawCashService.getLastReportWithdrawMonth();
	}
	
	
}

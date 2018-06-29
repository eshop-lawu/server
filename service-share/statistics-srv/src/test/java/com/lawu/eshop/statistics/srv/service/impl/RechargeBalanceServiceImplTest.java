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

import com.lawu.eshop.statistics.dto.ReportCommonBackDTO;
import com.lawu.eshop.statistics.param.ReportKCommonParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.bo.RechargeBalanceDailyBO;
import com.lawu.eshop.statistics.srv.domain.ReportRechargeBalanceDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportRechargeBalanceMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportRechargeBalanceMonthDOExample;
import com.lawu.eshop.statistics.srv.mapper.ReportRechargeBalanceDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportRechargeBalanceMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.RechargeBalanceService;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class RechargeBalanceServiceImplTest {
	
	@Autowired
	private RechargeBalanceService rechargeBalanceService;
	
	@Autowired
	private ReportRechargeBalanceDailyDOMapper reportRechargeBalanceDailyDOMapper;
	
	@Autowired
	private ReportRechargeBalanceMonthDOMapper reportRechargeBalanceMonthDOMapper;

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveDaily() {
		ReportKCommonParam param = new ReportKCommonParam();
		param.setGmtCreate(new Date());
		param.setGmtReport(new Date());
		param.setMemberMoney(new BigDecimal(0.01));
		param.setMerchantMoney(new BigDecimal(0.01));
		param.setTotalMoney(new BigDecimal(0.02));
		rechargeBalanceService.saveDaily(param);
		List<ReportRechargeBalanceDailyDO> list = reportRechargeBalanceDailyDOMapper.selectByExample(null);
		Assert.assertEquals(1, list.size());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveMonth() {
		ReportKCommonParam param = new ReportKCommonParam();
		param.setGmtCreate(DateUtil.getFirstDayOfMonth());
		param.setGmtReport(DateUtil.getFirstDayOfMonth());
		param.setMemberMoney(new BigDecimal(0.01));
		param.setMerchantMoney(new BigDecimal(0.01));
		param.setTotalMoney(new BigDecimal(0.02));
		rechargeBalanceService.saveMonth(param);
		List<ReportRechargeBalanceMonthDO> list = reportRechargeBalanceMonthDOMapper.selectByExample(null);
		Assert.assertEquals(1, list.size());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getDailyList() {
		ReportKCommonParam param = new ReportKCommonParam();
		param.setGmtCreate(new Date());
		param.setGmtReport(new Date());
		param.setMemberMoney(new BigDecimal(0.01));
		param.setMerchantMoney(new BigDecimal(0.01));
		param.setTotalMoney(new BigDecimal(0.02));
		
		ReportRechargeBalanceDailyDO record = new ReportRechargeBalanceDailyDO();
		record.setGmtCreate(param.getGmtCreate());
		record.setGmtReport(param.getGmtReport());
		record.setMemberMoney(param.getMemberMoney());
		record.setMerchantMoney(param.getMerchantMoney());
		record.setTotalMoney(param.getTotalMoney());
		
		reportRechargeBalanceDailyDOMapper.insert(record);
		reportRechargeBalanceDailyDOMapper.insert(record);
		reportRechargeBalanceDailyDOMapper.insert(record);
		String reportDate = new SimpleDateFormat("yyyy-MM").format(new Date());
		List<RechargeBalanceDailyBO> list = rechargeBalanceService.getDailyList(reportDate);
		Assert.assertEquals(3, list.size());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void deleteDailyByReportDate() {
		ReportKCommonParam param = new ReportKCommonParam();
		param.setGmtCreate(new Date());
		param.setGmtReport(new Date());
		param.setMemberMoney(new BigDecimal(0.01));
		param.setMerchantMoney(new BigDecimal(0.01));
		param.setTotalMoney(new BigDecimal(0.02));
		
		ReportRechargeBalanceDailyDO record = new ReportRechargeBalanceDailyDO();
		record.setGmtCreate(param.getGmtCreate());
		record.setGmtReport(param.getGmtReport());
		record.setMemberMoney(param.getMemberMoney());
		record.setMerchantMoney(param.getMerchantMoney());
		record.setTotalMoney(param.getTotalMoney());
		
		reportRechargeBalanceDailyDOMapper.insert(record);
		reportRechargeBalanceDailyDOMapper.insert(record);
		reportRechargeBalanceDailyDOMapper.insert(record);
		
		
		String reportDate1 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		rechargeBalanceService.deleteDailyByReportDate(reportDate1);
		String reportDate = new SimpleDateFormat("yyyy-MM").format(new Date());
		List<RechargeBalanceDailyBO> list = rechargeBalanceService.getDailyList(reportDate);
		Assert.assertEquals(0, list.size());
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void deleteMonthByReportDate() {
		ReportKCommonParam param = new ReportKCommonParam();
		param.setGmtCreate(DateUtil.getFirstDayOfMonth());
		param.setGmtReport(DateUtil.getFirstDayOfMonth());
		param.setMemberMoney(new BigDecimal(0.01));
		param.setMerchantMoney(new BigDecimal(0.01));
		param.setTotalMoney(new BigDecimal(0.02));
		
		ReportRechargeBalanceMonthDO record = new ReportRechargeBalanceMonthDO();
		record.setGmtCreate(param.getGmtCreate());
		record.setGmtReport(param.getGmtReport());
		record.setMemberMoney(param.getMemberMoney());
		record.setMerchantMoney(param.getMerchantMoney());
		record.setTotalMoney(param.getTotalMoney());
		
		reportRechargeBalanceMonthDOMapper.insertSelective(record);
		reportRechargeBalanceMonthDOMapper.insertSelective(record);
		reportRechargeBalanceMonthDOMapper.insertSelective(record);
		
		ReportRechargeBalanceMonthDOExample example = new ReportRechargeBalanceMonthDOExample();
		Date begin = DateUtil.formatDate("2017-7-1 00:00:00", "yyyy-MM-dd HH:mm:ss");
		Date end = DateUtil.getLastDayOfMonth(begin);
		example.createCriteria().andGmtReportBetween(begin, end);
		List<ReportRechargeBalanceMonthDO> rntList1 = reportRechargeBalanceMonthDOMapper.selectByExample(example);
		
		String reportDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		rechargeBalanceService.deleteMonthByReportDate(reportDate);
		
		List<ReportRechargeBalanceMonthDO> rntList2 = reportRechargeBalanceMonthDOMapper.selectByExample(example);
		Assert.assertEquals(0, rntList2.size());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectReport() {
		
		ReportKCommonParam param = new ReportKCommonParam();
		param.setGmtCreate(new Date());
		param.setGmtReport(new Date());
		param.setMemberMoney(new BigDecimal(0.01));
		param.setMerchantMoney(new BigDecimal(0.01));
		param.setTotalMoney(new BigDecimal(0.02));
		
		ReportRechargeBalanceMonthDO record = new ReportRechargeBalanceMonthDO();
		record.setGmtCreate(param.getGmtCreate());
		record.setGmtReport(param.getGmtReport());
		record.setMemberMoney(param.getMemberMoney());
		record.setMerchantMoney(param.getMerchantMoney());
		record.setTotalMoney(param.getTotalMoney());
		
		reportRechargeBalanceMonthDOMapper.insertSelective(record);
		reportRechargeBalanceMonthDOMapper.insertSelective(record);
		reportRechargeBalanceMonthDOMapper.insertSelective(record);
		
		ReportCommonBackDTO dto1 = rechargeBalanceService.selectReport("2017-7-1", "2017-7-31");
		ReportCommonBackDTO dto2 = rechargeBalanceService.selectReport("2017-7", "2017-8");
		ReportCommonBackDTO dto3 = rechargeBalanceService.selectReport("", "");
	}
}

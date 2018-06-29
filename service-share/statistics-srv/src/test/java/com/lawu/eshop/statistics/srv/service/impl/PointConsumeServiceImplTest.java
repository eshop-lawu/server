package com.lawu.eshop.statistics.srv.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
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
import com.lawu.eshop.statistics.srv.bo.PointConsumeDailyBO;
import com.lawu.eshop.statistics.srv.domain.ReportPointConsumeDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportPointConsumeDailyDOExample;
import com.lawu.eshop.statistics.srv.domain.ReportPointConsumeMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportPointConsumeMonthDOExample;
import com.lawu.eshop.statistics.srv.mapper.ReportPointConsumeDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportPointConsumeMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.PointConsumeService;
import com.lawu.eshop.statistics.srv.service.ReportEarningService;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class PointConsumeServiceImplTest {

	@Autowired
	private PointConsumeService pointConsumeService;

	@Autowired
	private ReportPointConsumeDailyDOMapper reportPointConsumeDailyDOMapper;

	@Autowired
	private ReportPointConsumeMonthDOMapper reportPointConsumeMonthDOMapper;

	@Autowired
	private ReportEarningService reportEarningService;

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
		pointConsumeService.saveDaily(param);
		List<ReportPointConsumeDailyDO> list = reportPointConsumeDailyDOMapper
				.selectByExample(new ReportPointConsumeDailyDOExample());
		Assert.assertEquals(1, list.size());
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveMonth() throws ParseException {
		ReportKCommonParam param = new ReportKCommonParam();
		param.setGmtCreate(DateUtil.getFirstDayOfMonth());
		param.setGmtReport(DateUtil.getFirstDayOfMonth());
		param.setMemberMoney(new BigDecimal(0.01));
		param.setMerchantMoney(new BigDecimal(0.01));
		param.setTotalMoney(new BigDecimal(0.02));
		pointConsumeService.saveMonth(param);
		List<ReportPointConsumeMonthDO> list = reportPointConsumeMonthDOMapper
				.selectByExample(new ReportPointConsumeMonthDOExample());
		Assert.assertEquals(1, list.size());
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getDailyList() {
		String reportDate = new SimpleDateFormat("yyyy-MM").format(new Date());
		saveDaily();
		List<PointConsumeDailyBO> list = pointConsumeService.getDailyList(reportDate);
		Assert.assertEquals(1, list.size());
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void deleteDailyByReportDate() {
		saveDaily();
		int i = reportPointConsumeDailyDOMapper.countByExample(null);
		Assert.assertEquals(1, i);
		String reportDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		pointConsumeService.deleteDailyByReportDate(reportDate);
		int y = reportPointConsumeDailyDOMapper.countByExample(null);
		Assert.assertEquals(0, y);
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void deleteMonthByReportDate() throws ParseException {
		saveMonth();
		int i = reportPointConsumeMonthDOMapper.countByExample(null);
		Assert.assertEquals(1, i);
		String reportDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		pointConsumeService.deleteMonthByReportDate(reportDate);
		i = reportPointConsumeMonthDOMapper.countByExample(null);
		Assert.assertEquals(0, i);
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectReport() {
		String bdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String edate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		saveDaily();
		ReportCommonBackDTO dto1 = pointConsumeService.selectReport(bdate, edate);
		ReportCommonBackDTO dto2 = pointConsumeService.selectReport("2017-7", "2017-7-12");
		ReportCommonBackDTO dto3 = pointConsumeService.selectReport("", "");
	}
}

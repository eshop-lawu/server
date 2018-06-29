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

import com.lawu.eshop.statistics.param.ReportAreaPointConsumeMonthParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.bo.ReportAreaPointConsumeMonthBO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaPointConsumeMonthDO;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaPointConsumeMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportAreaPointConsumeMonthService;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class ReportAreaPointConsumeMonthServiceImplTest {

	@Autowired
	private ReportAreaPointConsumeMonthService reportAreaPointConsumeMonthService;
	
	@Autowired
	private ReportAreaPointConsumeMonthDOMapper  reportAreaPointConsumeMonthDOMapper;
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void insertReportAreaAdPointDaily() {
		ReportAreaPointConsumeMonthParam param = new ReportAreaPointConsumeMonthParam();
		param.setAreaId(1);
		param.setCityId(1);
		param.setProvinceId(1);
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		param.setMemberPoint(new BigDecimal(1));
		param.setMemberRechargePoint(new BigDecimal(1));
		param.setMerchantPoint(new BigDecimal(1));
		param.setMerchantRechargePoint(new BigDecimal(1));
		reportAreaPointConsumeMonthService.saveReportAreaPointConsumeMonth(param);
		List<ReportAreaPointConsumeMonthDO> list = reportAreaPointConsumeMonthDOMapper.selectByExample(null);
		Assert.assertEquals(1, list.size());
	}
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void selectReportAreaPointConsumeMonth() {
		insertReportAreaAdPointDaily();
		List<ReportAreaPointConsumeMonthBO> list = reportAreaPointConsumeMonthService.selectReportAreaPointConsumeMonth(1, DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()))), DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		Assert.assertEquals(1, list.size());
	}
}

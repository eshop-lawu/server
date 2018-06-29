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

import com.lawu.eshop.statistics.param.ReportAreaVolumnMonthParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.bo.ReportAreaVolumnMonthBO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaVolumeMonthDO;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaVolumeMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportAreaVolumnMonthService;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class ReportAreaVolumnMonthServiceImplTest {

	
	@Autowired
	private ReportAreaVolumnMonthService reportAreaVolumnMonthService;
	
	@Autowired
	private ReportAreaVolumeMonthDOMapper reportAreaVolumeMonthDOMapper;
	
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void insertReportAreaVolumnMonth() {
		ReportAreaVolumnMonthParam reportAreaVolumnMonthParam = new ReportAreaVolumnMonthParam();
		reportAreaVolumnMonthParam.setAreaId(1);
		reportAreaVolumnMonthParam.setCityId(1);
		reportAreaVolumnMonthParam.setProvinceId(1);
		reportAreaVolumnMonthParam.setGmtCreate(new Date());
		reportAreaVolumnMonthParam.setReportTotalMoney(new BigDecimal(1));
		reportAreaVolumnMonthParam.setType((byte)1);
		reportAreaVolumnMonthParam.setGmtReport(DateUtil.getMonthBefore(new Date()));
		reportAreaVolumnMonthService.insertReportAreaVolumnMonth(reportAreaVolumnMonthParam);
		List<ReportAreaVolumeMonthDO> list = reportAreaVolumeMonthDOMapper.selectByExample(null);
		Assert.assertEquals(1, list.size());
	}
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void selectReportAreaVolumnMonth() {
		insertReportAreaVolumnMonth();
		List<ReportAreaVolumnMonthBO> list = reportAreaVolumnMonthService.selectReportAreaVolumnMonth(1,DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date())), DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date())));
		Assert.assertEquals(1, list.size());
	}
}

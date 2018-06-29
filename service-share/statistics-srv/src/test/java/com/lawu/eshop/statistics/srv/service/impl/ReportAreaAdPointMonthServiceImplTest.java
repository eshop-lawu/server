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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.statistics.param.AgentSelectAreaAdPointParam;
import com.lawu.eshop.statistics.param.ReportAreaAdPointMonthParams;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.bo.ReportAreaAdPointMonthBO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaAdPointMonthDO;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaAdPointMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportAreaAdPointMonthService;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class ReportAreaAdPointMonthServiceImplTest {

	@Autowired
	private ReportAreaAdPointMonthService ReportAreaAdPointMonthService;
	
	@Autowired
	private ReportAreaAdPointMonthDOMapper reportAreaAdPointMonthDOMapper;
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void insertReportAreaAdPointMonth() {
		ReportAreaAdPointMonthParams param = new ReportAreaAdPointMonthParams();
		param.setAreaId(1);
		param.setCityId(1);
		param.setProvinceId(1);
		param.setGmtCreate(new Date());
		param.setGmtReport(DateUtil.getMonthBefore(new Date()));
		param.setProvinceId(1);
		param.setReportTotalPoint(new BigDecimal(1));
		ReportAreaAdPointMonthService.insertReportAreaAdPointMonth(param);
		List<ReportAreaAdPointMonthDO> list = reportAreaAdPointMonthDOMapper.selectByExample(null);
		Assert.assertEquals(1, list.size());
	}
	
	@Test
	@Rollback
	@Transactional(rollbackFor = Exception.class)
	public void selectReportAreaAdPointMonth() {
		insertReportAreaAdPointMonth();
		AgentSelectAreaAdPointParam param = new AgentSelectAreaAdPointParam();
		param.setBdate(DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		param.setEdate(DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()))));
		param.setCityId(1);
		List<ReportAreaAdPointMonthBO> list = ReportAreaAdPointMonthService.selectReportAreaAdPointMonth(param);
		Assert.assertEquals(1, list.size());
	}
	
}

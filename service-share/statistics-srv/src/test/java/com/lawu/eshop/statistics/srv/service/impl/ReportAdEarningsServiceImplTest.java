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

import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.statistics.constants.AdStatusEnum;
import com.lawu.eshop.statistics.constants.ReportAdEarningsStatusEnum;
import com.lawu.eshop.statistics.param.ReportAdEarningsParam;
import com.lawu.eshop.statistics.param.ReportAdEarningsQueryParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.bo.ReportAdEarningsBO;
import com.lawu.eshop.statistics.srv.domain.ReportAdEarningsDO;
import com.lawu.eshop.statistics.srv.mapper.ReportAdEarningsDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportAdEarningsService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class ReportAdEarningsServiceImplTest {

	@Autowired
	private ReportAdEarningsService reportAdEarningsService;
	
	@Autowired 
	private ReportAdEarningsDOMapper reportAdEarningsDOMapper;
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveReportAdEarnings() {
		ReportAdEarningsParam reportAdEarningsParam = new ReportAdEarningsParam();
		reportAdEarningsParam.setAdId(1L);
		reportAdEarningsParam.setAdStatusEnum(AdStatusEnum.getEnum((byte)0x01));
		reportAdEarningsParam.setAdTitle("aaa");
		reportAdEarningsParam.setAdCreateTime(new Date());
		reportAdEarningsParam.setAdTotalPoint(new BigDecimal(100));
		reportAdEarningsParam.setAdTypeEnum(AdTypeEnum.getEnum((byte) 0x01));
		reportAdEarningsParam.setMerchantNum("B13025458808");
		reportAdEarningsParam.setLoveTotalPoint(new BigDecimal(1000));
		reportAdEarningsParam.setUserTotalPoint(new BigDecimal(1000));
		reportAdEarningsParam.setReportAdEarningsStatusEnum(ReportAdEarningsStatusEnum.getEnum((byte) 0x01));
		reportAdEarningsService.saveReportAdEarnings(reportAdEarningsParam);
		List<ReportAdEarningsDO> list = reportAdEarningsDOMapper.selectByExample(null);
		Assert.assertEquals(1, list.size());
		reportAdEarningsParam.setAdId(2L);
		reportAdEarningsService.saveReportAdEarnings(reportAdEarningsParam);
		list = reportAdEarningsDOMapper.selectByExample(null);
		Assert.assertEquals(2, list.size());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectReportAdEarnings() {
		saveReportAdEarnings();
		ReportAdEarningsQueryParam query = new ReportAdEarningsQueryParam();
		query.setAdTitle("aaa");
		query.setAdStatusEnum(AdStatusEnum.getEnum((byte)0x01));
		query.setBeginTime(DateUtil.getDateFormat(DateUtil.getDayBefore(new Date()), "yyyy-MM-dd HH:mm:ss"));
		query.setEndTime(DateUtil.getDateFormat(DateUtil.getDayAfter(new Date()), "yyyy-MM-dd HH:mm:ss"));
		query.setCurrentPage(1);
		query.setPageSize(10);
		query.setMerchantNum("B13025458808");
		Page<ReportAdEarningsBO> page = reportAdEarningsService.selectReportAdEarnings(query);
		Assert.assertEquals(2, (int)page.getTotalCount());
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getReportAdEarningsIds() {
		ReportAdEarningsParam reportAdEarningsParam = new ReportAdEarningsParam();
		reportAdEarningsParam.setAdId(1L);
		reportAdEarningsParam.setAdStatusEnum(AdStatusEnum.getEnum((byte)0x03));
		reportAdEarningsParam.setAdTitle("aaa");
		reportAdEarningsParam.setAdCreateTime(new Date());
		reportAdEarningsParam.setAdTotalPoint(new BigDecimal(100));
		reportAdEarningsParam.setAdTypeEnum(AdTypeEnum.getEnum((byte) 0x01));
		reportAdEarningsParam.setMerchantNum("B13025458808");
		reportAdEarningsParam.setLoveTotalPoint(new BigDecimal(1000));
		reportAdEarningsParam.setUserTotalPoint(new BigDecimal(1000));
		reportAdEarningsParam.setReportAdEarningsStatusEnum(ReportAdEarningsStatusEnum.getEnum((byte) 0x01));
		reportAdEarningsService.saveReportAdEarnings(reportAdEarningsParam);
		reportAdEarningsService.saveReportAdEarnings(reportAdEarningsParam);
		
		reportAdEarningsService.getReportAdEarningsIds();
	}
}

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

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureQueryParam;
import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureSaveParam;
import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureSaveWrapperParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.bo.ReportUserIncomeExpenditureBO;
import com.lawu.eshop.statistics.srv.mapper.ReportUserIncomeExpenditureDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportUserIncomeExpenditureService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class ReportUserIncomeExpenditureServiceImplTest {

	@Autowired
	private ReportUserIncomeExpenditureDOMapper reportUserIncomeExpenditureDOMapper;
	
	@Autowired
	private ReportUserIncomeExpenditureService reportUserIncomeExpenditureService;

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void save() {
		ReportUserIncomeExpenditureSaveParam param = new ReportUserIncomeExpenditureSaveParam();
		param.setAccount("13025458808");
		param.setExpenditure(new BigDecimal(199));
		param.setIncome(new BigDecimal(199));
		param.setUserNum("Mhqm");
		reportUserIncomeExpenditureService.save(param);
		long i = reportUserIncomeExpenditureDOMapper.countByExample(null);
		Assert.assertEquals(1, i);
	}
	
	/**
	 * 批量保存用户收支记录test
	 * 
	 * @date 2017年7月13日
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void batchSave() {
		ReportUserIncomeExpenditureSaveWrapperParam param = new ReportUserIncomeExpenditureSaveWrapperParam();
		List<ReportUserIncomeExpenditureSaveParam> list = new ArrayList<ReportUserIncomeExpenditureSaveParam>();
		for(int i = 0; i < 10; i++) {
			ReportUserIncomeExpenditureSaveParam r = new ReportUserIncomeExpenditureSaveParam();
			r.setAccount("1302545880" + i);
			r.setExpenditure(new BigDecimal(199));
			r.setIncome(new BigDecimal(199));
			r.setUserNum("Mhqm" + i);
			list.add(r);
		}
		param.setParams(list);
		reportUserIncomeExpenditureService.batchSave(param);
		long i = reportUserIncomeExpenditureDOMapper.countByExample(null);
		Assert.assertEquals(10, i);
	}
	
	/**
     * 分页查询用户收支记录test
     * 
     * @date 2017年7月13日
     */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void page() {
		batchSave();
		ReportUserIncomeExpenditureQueryParam param = new ReportUserIncomeExpenditureQueryParam();
		param.setAccount("13025458808");
		param.setCurrentPage(1);
		param.setEnd(DateUtil.getDayAfter(new Date()));
		param.setMax(new BigDecimal(100000));
		param.setPageSize(10);
		param.setUserType(UserTypeEnum.MEMBER);
		Page<ReportUserIncomeExpenditureBO> page = reportUserIncomeExpenditureService.page(param);
		param.setStart(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(DateUtil.getNowDate())));
		param.setEnd(null);
		param.setMin(null);
		page = reportUserIncomeExpenditureService.page(param);
		param.setEnd(DateUtil.getDayAfter(new Date()));
		param.setMin(new BigDecimal(0));
		page = reportUserIncomeExpenditureService.page(param);
		System.out.println(page.getRecords().size());
		Assert.assertEquals(1, (int)page.getTotalCount());
	}
	
}

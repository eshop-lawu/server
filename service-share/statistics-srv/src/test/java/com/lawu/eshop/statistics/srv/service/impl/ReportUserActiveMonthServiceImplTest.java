package com.lawu.eshop.statistics.srv.service.impl;

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

import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveBO;
import com.lawu.eshop.statistics.srv.mapper.ReportUserActiveMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportUserActiveDailyService;
import com.lawu.eshop.statistics.srv.service.ReportUserActiveMonthService;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class ReportUserActiveMonthServiceImplTest {

	@Autowired
	private ReportUserActiveMonthService reportUserActiveMonthService;
	
	@Autowired
    private ReportUserActiveMonthDOMapper reportUserActiveMonthDOMapper;

	@Autowired
    private ReportUserActiveDailyService reportUserActiveDailyService;
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveUserActiveMonth() {
        Integer memberCount = 10;
        Integer merchantCount = 20;
        String date = DateUtil.getDateFormat(new Date());
        for(int i = 0; i < 10; i++) {
        	reportUserActiveMonthService.saveUserActiveMonth(memberCount, merchantCount, date);
        }
        int i = reportUserActiveMonthDOMapper.countByExample(null);
        Assert.assertEquals(10, i);
    }

	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getUserActiveListMonth() {
		saveUserActiveMonth();
		String beginTime = "";
		String endTime = "";
		List<ReportUserActiveBO> list = reportUserActiveDailyService.getUserActiveListMonth(beginTime, endTime);
		Assert.assertEquals(10, list.size());
		beginTime = "2017-6";
		endTime = "2017-8";
		list = reportUserActiveDailyService.getUserActiveListMonth(beginTime, endTime);
		Assert.assertEquals(10, list.size());
	}
}

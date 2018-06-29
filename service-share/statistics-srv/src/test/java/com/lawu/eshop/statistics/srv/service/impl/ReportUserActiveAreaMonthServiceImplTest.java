package com.lawu.eshop.statistics.srv.service.impl;

import java.text.SimpleDateFormat;
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

import com.lawu.eshop.statistics.dto.UserActiveDTO;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveAreaMonthBO;
import com.lawu.eshop.statistics.srv.mapper.ReportUserActiveAreaMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportUserActiveAreaMonthService;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class ReportUserActiveAreaMonthServiceImplTest {
	
	@Autowired
	private ReportUserActiveAreaMonthService reportUserActiveAreaMonthService;
	
	@Autowired
    private ReportUserActiveAreaMonthDOMapper reportUserActiveAreaMonthDOMapper;
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
    public void saveUserActiveAreaMonth() {
		List<UserActiveDTO> userActiveDTOS = new ArrayList<UserActiveDTO>();
		for(int i = 0 ; i < 5 ; i++) {
			UserActiveDTO dto = new UserActiveDTO();
			dto.setCityId(4403);
			dto.setCityName("深圳市");
			dto.setUserCount(i);
			dto.setVisitDate(DateUtil.getFirstDayOfMonth());
			userActiveDTOS.add(dto);
		}
		reportUserActiveAreaMonthService.saveUserActiveAreaMonth(userActiveDTOS);
		long i = reportUserActiveAreaMonthDOMapper.countByExample(null);
		Assert.assertEquals(5, i);
    }
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
    public void saveMerchantActiveAreaMonth() {
		List<UserActiveDTO> userActiveDTOS = new ArrayList<UserActiveDTO>();
		for(int i = 0 ; i < 5 ; i++) {
			UserActiveDTO dto = new UserActiveDTO();
			dto.setCityId(4403);
			dto.setCityName("深圳市");
			dto.setUserCount(i);
			dto.setVisitDate(DateUtil.getFirstDayOfMonth());
			userActiveDTOS.add(dto);
		}
		reportUserActiveAreaMonthService.saveMerchantActiveAreaMonth(userActiveDTOS);
		reportUserActiveAreaMonthService.saveMerchantActiveAreaMonth(userActiveDTOS);
		long i = reportUserActiveAreaMonthDOMapper.countByExample(null);
		Assert.assertEquals(10, i);
    }

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
    public void getReportUserActiveAreaMonthList() {
		saveUserActiveAreaMonth();
		String reportDate = new SimpleDateFormat("yyyy-MM-dd").format(DateUtil.getMonthBefore(new Date()));
		List<ReportUserActiveAreaMonthBO> list = reportUserActiveAreaMonthService.getReportUserActiveAreaMonthList(reportDate);
		Assert.assertEquals(5, list.size());
	}
	
}

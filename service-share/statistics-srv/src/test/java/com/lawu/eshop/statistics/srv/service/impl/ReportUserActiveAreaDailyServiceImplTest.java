package com.lawu.eshop.statistics.srv.service.impl;

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
import com.lawu.eshop.statistics.srv.mapper.ReportUserActiveAreaDailyDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportUserActiveAreaDailyService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class ReportUserActiveAreaDailyServiceImplTest {
	
	@Autowired
	private ReportUserActiveAreaDailyService reportUserActiveAreaDailyService;
	
	@Autowired
    private ReportUserActiveAreaDailyDOMapper reportUserActiveAreaDailyDOMapper;
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
    public void saveUserActiveAreaDaily() {
		List<UserActiveDTO> userActiveDTOS = new ArrayList<UserActiveDTO>();
		for(int i = 0 ; i < 5 ; i++) {
			UserActiveDTO dto = new UserActiveDTO();
			dto.setCityId(4403);
			dto.setCityName("深圳市");
			dto.setUserCount(i);
			dto.setVisitDate(new Date());
			userActiveDTOS.add(dto);
		}
		reportUserActiveAreaDailyService.saveUserActiveAreaDaily(userActiveDTOS);
		int i = reportUserActiveAreaDailyDOMapper.countByExample(null);
		Assert.assertEquals(5, i);
    }
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
    public void saveMerchantActiveAreaDaily() {
		List<UserActiveDTO> userActiveDTOS = new ArrayList<UserActiveDTO>();
		for(int i = 0 ; i < 5 ; i++) {
			UserActiveDTO dto = new UserActiveDTO();
			dto.setCityId(4403);
			dto.setCityName("深圳市");
			dto.setUserCount(i);
			dto.setVisitDate(new Date());
			userActiveDTOS.add(dto);
		}
		reportUserActiveAreaDailyService.saveMerchantActiveAreaDaily(userActiveDTOS);
		int i = reportUserActiveAreaDailyDOMapper.countByExample(null);
		Assert.assertEquals(1, i);
    }
	
}

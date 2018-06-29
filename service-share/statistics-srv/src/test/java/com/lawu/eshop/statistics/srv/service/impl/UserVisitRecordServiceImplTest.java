package com.lawu.eshop.statistics.srv.service.impl;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.statistics.param.UserVisitRecordParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.service.UserVisitRecordService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class UserVisitRecordServiceImplTest {

	@Autowired
	private UserVisitRecordService userVisitRecordService;

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void addUserVisitRecord() {
		UserVisitRecordParam userVisitRecordParam = new UserVisitRecordParam();
		userVisitRecordParam.setAccount("13025458808");
		userVisitRecordParam.setUserNum("M13025458808");
		userVisitRecordParam.setUserType((byte) 1);
		userVisitRecordParam.setCityName("深圳市");
		userVisitRecordParam.setCityId(4403);
		userVisitRecordParam.setVisitCount(1);
		userVisitRecordParam.setVisitDate(new Date());
		userVisitRecordService.addUserVisitRecord(userVisitRecordParam);
	}
}

package com.lawu.eshop.statistics.srv.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.statistics.param.UserRegAreaParam;
import com.lawu.eshop.statistics.param.UserRegParam;
import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.service.UserRegService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class UserRegServiceImplTest {
	
	@Autowired
	private UserRegService userRegService;
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
    public void saveUserRegDaily() {
		Integer memberCount = 10;
		Integer merchantCount = 20;
		userRegService.saveUserRegDaily(memberCount, merchantCount);
    }

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
    public void saveUserRegMonth() {
		Integer memberCount = 10;
		Integer merchantCount = 20;
        userRegService.saveUserRegMonth(memberCount, merchantCount);
    }

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
    public void updateUserRegArea() {
		UserRegAreaParam param = new UserRegAreaParam();
		param.setMemberCount(10);
		param.setMerchantCount(20);
		param.setCityId(4403);
		param.setMerchantCommonCount(10);
		param.setMerchantEntityCount(1);
		userRegService.updateUserRegArea(param);
    }

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
    public void getReportUserRegDaily() {
		UserRegParam param = new UserRegParam();
		userRegService.getReportUserRegDaily(param);
    }

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
    public void getReportUserRegMonth() {
		UserRegParam param = new UserRegParam();
		userRegService.getReportUserRegMonth(param);
    }

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
    public void getReportUserRegArea() {
		userRegService.getReportUserRegArea();
    }
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
    public void addUserRegAreaDaily() {
		UserRegAreaParam param = new UserRegAreaParam();
		param.setCityId(1);
		param.setMemberCount(1);
		param.setMerchantCommonCount(1);
		param.setMerchantCount(1);
		param.setMerchantEntityCount(1);
		param.setName("");
		userRegService.addUserRegAreaDaily(param);
    }
    
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
    public void addUserRegAreaMonth() {
		UserRegAreaParam param = new UserRegAreaParam();
		param.setCityId(1);
		param.setMemberCount(1);
		param.setMerchantCommonCount(1);
		param.setMerchantCount(1);
		param.setMerchantEntityCount(1);
		param.setName("");
		userRegService.addUserRegAreaMonth(param);
    }
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
    public void getReportDateUserRegDaily() {
		userRegService.getReportDateUserRegDaily();
    }
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
    public void getReportDateUserRegMonth() {
		userRegService.getReportDateUserRegMonth();
    }
	
}

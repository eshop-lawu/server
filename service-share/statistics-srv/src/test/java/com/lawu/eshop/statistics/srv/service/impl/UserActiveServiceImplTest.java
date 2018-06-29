package com.lawu.eshop.statistics.srv.service.impl;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.statistics.srv.StatisticsSrvApplicationTest;
import com.lawu.eshop.statistics.srv.service.UserActiveService;
import com.lawu.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsSrvApplicationTest.class)
public class UserActiveServiceImplTest {


	@Autowired
	private UserActiveService userActiveService;

	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void collectionMemberActiveDaily() {
		userActiveService.collectionMemberActiveDaily(new Date());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void collectionMemberActiveAreaDaily() {
		userActiveService.collectionMemberActiveAreaDaily(DateUtil.getDate());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void collectionMerchantActiveDaily() {
		userActiveService.collectionMerchantActiveDaily(new Date());
    }

//	@Transactional(rollbackFor = Exception.class)
//	@Rollback
//	@Test
//    public void collectionMemberActiveMonth() {
//    	userActiveService.collectionMemberActiveAreaMonth();
//    }

//	@Transactional(rollbackFor = Exception.class)
//	@Rollback
//	@Test
//    public void collectionMerchantActiveMonth() {
//    	userActiveService.collectionMerchantActiveMonth();
//    }


	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
    public void collectionMerchantActiveAreaDaily() {
		userActiveService.collectionMerchantActiveAreaDaily(DateUtil.getDate());
    }

//	@Transactional(rollbackFor = Exception.class)
//	@Rollback
//	@Test
//    public void collectionMemberActiveAreaMonth() {
//		userActiveService.collectionMemberActiveAreaMonth();
//    }

//	@Transactional(rollbackFor = Exception.class)
//	@Rollback
//	@Test
//    public void collectionMerchantActiveAreaMonth() {
//		userActiveService.collectionMerchantActiveAreaMonth();
//    }
    
}

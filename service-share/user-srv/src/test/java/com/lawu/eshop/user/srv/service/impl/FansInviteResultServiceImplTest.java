package com.lawu.eshop.user.srv.service.impl;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.eshop.user.srv.domain.FansInviteResultDO;
import com.lawu.eshop.user.srv.mapper.FansInviteResultDOMapper;
import com.lawu.eshop.user.srv.service.FansInviteResultService;

/**
 * 
 * @author hongqm
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
public class FansInviteResultServiceImplTest {

	
	@Autowired
	private FansInviteResultService fansInviteResultService;
	
	@Autowired
	private FansInviteResultDOMapper fansInviteResultDOMapper;
	
	
	
	@Test
	@Rollback
	public void selectInviteResultById() {
		FansInviteResultDO fansInviteResultDO = new FansInviteResultDO();
		int status = 1;
		fansInviteResultDO.setMemberId(1L);
		fansInviteResultDO.setMerchantId(1L);
		fansInviteResultDO.setMessageId(1L);
		fansInviteResultDO.setStatus((byte)status);
		fansInviteResultDO.setGmtCreate(new Date());
		fansInviteResultDO.setGmtModified(new Date());
		fansInviteResultDOMapper.insertSelective(fansInviteResultDO);
		int i = fansInviteResultService.selectInviteResultById(1L);
		Assert.assertEquals(status, i);
	}
}

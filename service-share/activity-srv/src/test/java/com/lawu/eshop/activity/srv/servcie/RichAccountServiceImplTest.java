package com.lawu.eshop.activity.srv.servcie;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.bo.PersonalRichAccountBO;
import com.lawu.eshop.activity.srv.bo.RichDetailBO;
import com.lawu.eshop.activity.srv.bo.RichMyDiamondRecordInfoBO;
import com.lawu.eshop.activity.srv.bo.RichPowerInfoRecordBO;
import com.lawu.eshop.activity.srv.domain.RichAccountDO;
import com.lawu.eshop.activity.srv.domain.RichAccountDOExample;
import com.lawu.eshop.activity.srv.mapper.RichAccountDOMapper;

import junit.framework.Assert;

/** 
 * 
 * @author lihj
 * @date 2018年5月8日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class RichAccountServiceImplTest {

	private MockMvc mvc;
	
	@Autowired
	private RichAccountDOMapper richAccountDOMapper; 
	@Autowired
	private RichAccountService richAccountService;
	@Autowired
	private RichConfigCacheService richConfigCacheService; 
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getPersonalRichAccountInfo(){
		String memberNum ="M001";
		PersonalRichAccountBO bo = richAccountService.getPersonalRichAccountInfo(memberNum, null);
		
		RichAccountDOExample example = new RichAccountDOExample();
		example.createCriteria().andUserNumEqualTo(memberNum);
		List<RichAccountDO> list = richAccountDOMapper.selectByExample(example);
		Assert.assertTrue(list.size()>0);		
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getRichInfo(){
		RichDetailBO rich = richAccountService.getRichInfo(null);
		Assert.assertNotNull(rich);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getRichPowerInfoRecord(){
		String memberNum ="M001";
		PersonalRichAccountBO bo = richAccountService.getPersonalRichAccountInfo(memberNum, null);
		RichPowerInfoRecordBO rich = richAccountService.getRichPowerInfoRecord(memberNum);
		Assert.assertNotNull(rich);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getMyRichDiamondRecordInfo(){
		String memberNum ="M001";
		PersonalRichAccountBO bo = richAccountService.getPersonalRichAccountInfo(memberNum, null);
		RichMyDiamondRecordInfoBO rich = richAccountService.getMyRichDiamondRecordInfo(memberNum);
		Assert.assertNotNull(rich);
	}

	
}

package com.lawu.eshop.activity.srv.servcie;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.bo.RichPowerTaskDetailBO;
import com.lawu.eshop.activity.srv.domain.RichPowerTaskRecordDO;
import com.lawu.eshop.activity.srv.domain.RichPowerTaskRecordDOExample;
import com.lawu.eshop.activity.srv.mapper.RichPowerTaskRecordDOMapper;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月7日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class RichPowerTaskRecordServiceImplTest {
	
	@Autowired
	private RichPowerTaskRecordService richPowerTaskRecordService;
	
	@Autowired
	private RichPowerTaskRecordDOMapper richPowerTaskRecordDOMapper;
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveRichPowerTaskRecord() {
		RichPowerTaskRecordParam param = new RichPowerTaskRecordParam();
		param.setMemberNum("M949121843325829209");
		param.setType(PowerTaskTypeEnum.AD);
		richPowerTaskRecordService.saveRichPowerTaskRecord(param);

		RichPowerTaskRecordDOExample example = new RichPowerTaskRecordDOExample();
		example.createCriteria().andUserNumEqualTo(param.getMemberNum());
		List<RichPowerTaskRecordDO> list = richPowerTaskRecordDOMapper.selectByExample(example);
		Assert.assertTrue(list.size() > 0);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getPowerTasks() {
		saveRichPowerTaskRecord();
		List<RichPowerTaskDetailBO> list = richPowerTaskRecordService.getPowerTasks("M949121843325829209");
		Assert.assertTrue(list.size() > 0);
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getPowerTaskRecordListCount(){
		int total = richPowerTaskRecordService.getPowerTaskRecordListCount();
		Assert.assertTrue(total == 0);
	}

	@Transactional(rollbackFor = Exception.class)
	@Test
	@Rollback
	public void resetTaskRecord(){
		RichPowerTaskRecordDO recordDO = new RichPowerTaskRecordDO();
		recordDO.setGmtModified(new Date());
		recordDO.setGmtCreate(new Date());
		recordDO.setShoppingAmount(1);
		recordDO.setUserNum("M123456");
		recordDO.setGameCount(1);
		recordDO.setAdCount(1);
		recordDO.setFriendInviteCount(1);
		recordDO.setIsBindingAlipay(false);
		recordDO.setIsLogin(true);
		richPowerTaskRecordDOMapper.insertSelective(recordDO);

		richPowerTaskRecordService.resetTaskRecord(1,10);
		List<RichPowerTaskRecordDO> list = richPowerTaskRecordDOMapper.selectByExample(null);
		Assert.assertEquals(0,list.get(0).getAdCount().intValue());
		Assert.assertEquals(0,list.get(0).getShoppingAmount().intValue());
		Assert.assertEquals(0,list.get(0).getGameCount().intValue());
		Assert.assertEquals(0,list.get(0).getFriendInviteCount().intValue());
		Assert.assertEquals(false,list.get(0).getIsLogin());



	}

}

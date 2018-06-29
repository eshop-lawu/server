package com.lawu.eshop.activity.srv.servcie;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.constants.RichPowerRecordDirectionEnum;
import com.lawu.eshop.activity.param.RichPowerRecordParam;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.domain.RichPowerRecordDO;
import com.lawu.eshop.activity.srv.domain.RichPowerRecordDOExample;
import com.lawu.eshop.activity.srv.mapper.RichPowerRecordDOMapper;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月7日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class RichPowerRecordServiceImplTest {
	
	@Autowired
	private RichPowerRecordService richPowerRecordService;
	
	@Autowired
	private RichPowerRecordDOMapper richPowerRecordDOMapper;
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveRichPowerRecord() {
		RichPowerRecordParam param = new RichPowerRecordParam();
		param.setMemberNum("M949121843325829209");
		param.setTypeEnum(PowerTaskTypeEnum.AD);
		param.setPower(20);
		param.setDirectionEnum(RichPowerRecordDirectionEnum.IN);
		richPowerRecordService.saveRichPowerRecord(param);

		RichPowerRecordDOExample example = new RichPowerRecordDOExample();
		example.createCriteria().andUserNumEqualTo(param.getMemberNum());
		List<RichPowerRecordDO> list = richPowerRecordDOMapper.selectByExample(example);
		Assert.assertTrue(list.size() > 0);
	}


}

package com.lawu.eshop.activity.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.lawu.eshop.activity.constants.RichDiamondRecordSourceEnum;
import com.lawu.eshop.activity.constants.RichPowerRecordDirectionEnum;
import com.lawu.eshop.activity.srv.bo.RichPowerRecordBO;
import com.lawu.eshop.activity.srv.domain.RichPowerRecordDO;

import junit.framework.Assert;

/** 
 * 
 * @author lihj
 * @date 2018年5月8日
 */
public class RichPowerRecordConvertTest {

	@Test
	public void convertRichPowerRecordBO(){
		List<RichPowerRecordDO> list = new ArrayList<RichPowerRecordDO>();
		RichPowerRecordDO rich = new RichPowerRecordDO();
		rich.setDirection(RichPowerRecordDirectionEnum.IN.getVal());
		rich.setGmtCreate(new Date());
		rich.setId(1L);
		rich.setPower(100);
		rich.setSource(RichDiamondRecordSourceEnum.DAYGET.getVal());
		rich.setTitle("title");
		rich.setUserNum("username");
		list.add(rich);
		List<RichPowerRecordBO> lt = RichPowerRecordConvert.convertRichPowerRecordBO(list);
		Assert.assertNotNull(lt);
	} 
	
}

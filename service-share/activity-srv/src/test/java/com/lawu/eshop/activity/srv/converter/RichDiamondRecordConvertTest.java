package com.lawu.eshop.activity.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.lawu.eshop.activity.constants.RichDiamondRecordSourceEnum;
import com.lawu.eshop.activity.constants.RichDiamondRecordTypeEnum;
import com.lawu.eshop.activity.constants.RichPowerRecordDirectionEnum;
import com.lawu.eshop.activity.srv.bo.RichDiamondRecordBO;
import com.lawu.eshop.activity.srv.domain.RichDiamondRecordDO;

/** 
 * 
 * @author lihj
 * @date 2018年5月8日
 */
public class RichDiamondRecordConvertTest {

	@Test
	public void convertRichDiamondRecordBO(){
		List<RichDiamondRecordDO> list = new ArrayList<RichDiamondRecordDO>();
		RichDiamondRecordDO rich = new RichDiamondRecordDO();
		rich.setAmount(new BigDecimal(100));
		rich.setDirection(RichPowerRecordDirectionEnum.IN.getVal());
		rich.setGmtCreate(new Date());
		rich.setId(1L);
		rich.setSource(RichDiamondRecordSourceEnum.DAYGET.getVal());
		rich.setStatus((byte)0x01);
		rich.setTakeTime(new Date());
		rich.setTitle("title");
		rich.setType(RichDiamondRecordTypeEnum.ECOIN.getVal());
		rich.setUserNum("M001");
		list.add(rich);
		List<RichDiamondRecordBO> lt = RichDiamondRecordConvert.convertRichDiamondRecordBO(list);
	}
}

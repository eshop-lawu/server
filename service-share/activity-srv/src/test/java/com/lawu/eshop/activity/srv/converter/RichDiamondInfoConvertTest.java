package com.lawu.eshop.activity.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.lawu.eshop.activity.srv.bo.RichDiamondInfoBO;
import com.lawu.eshop.activity.srv.domain.RichDiamondInfoDO;

import junit.framework.Assert;

/** 
 * 
 * @author lihj
 * @date 2018年5月8日
 */
public class RichDiamondInfoConvertTest {

	@Test
	public void convertRichDiamondInfoBO(){
		List<RichDiamondInfoDO> list = new ArrayList<RichDiamondInfoDO>();
		RichDiamondInfoDO rich = new RichDiamondInfoDO();
		rich.setDiamondLuckySent(new BigDecimal(100));
		rich.setDiamondSent(new BigDecimal(200));
		rich.setGmtCreate(new Date());
		rich.setGmtModified(new Date());
		rich.setId(1L);
		list.add(rich);
		RichDiamondInfoBO info = RichDiamondInfoConvert.convertRichDiamondInfoBO(list);
		Assert.assertNotNull(info);
	}
}

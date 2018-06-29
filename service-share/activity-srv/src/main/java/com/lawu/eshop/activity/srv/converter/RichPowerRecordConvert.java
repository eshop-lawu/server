package com.lawu.eshop.activity.srv.converter;

import java.util.List;

import com.google.common.collect.Lists;
import com.lawu.eshop.activity.constants.RichPowerRecordDirectionEnum;
import com.lawu.eshop.activity.srv.bo.RichPowerRecordBO;
import com.lawu.eshop.activity.srv.domain.RichPowerRecordDO;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;

/** 
 * 
 * @author lihj
 * @date 2018年5月3日
 */
public class RichPowerRecordConvert {

	public static List<RichPowerRecordBO> convertRichPowerRecordBO(List<RichPowerRecordDO> list) {
		List<RichPowerRecordBO> lt = Lists.newArrayList();
		if(list.size()==0){
			return lt;
		}
		for(RichPowerRecordDO rich : list){
			RichPowerRecordBO bo = new RichPowerRecordBO();
			bo.setDirectionEnum(RichPowerRecordDirectionEnum.getEnum(rich.getDirection()));
			bo.setPower(rich.getPower());
			bo.setTitle(rich.getTitle());
			bo.setTypeEnum(PowerTaskTypeEnum.getEnum(rich.getSource()));
			bo.setDate(rich.getGmtCreate());
			lt.add(bo);
		}
		return lt;
	}

}

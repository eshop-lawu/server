package com.lawu.eshop.activity.srv.converter;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.eshop.activity.srv.bo.RichDiamondInfoBO;
import com.lawu.eshop.activity.srv.domain.RichDiamondInfoDO;

/** 
 * 
 * @author lihj
 * @date 2018年5月3日
 */
public class RichDiamondInfoConvert {

	public static RichDiamondInfoBO convertRichDiamondInfoBO(List<RichDiamondInfoDO> list) {
		RichDiamondInfoBO bo =new RichDiamondInfoBO();
		if(list.size()==0){
			bo.setDiamondLuckySent(BigDecimal.ZERO);
			bo.setDiamondSent(BigDecimal.ZERO);
			return bo;
		}
		bo.setDiamondLuckySent(list.get(0).getDiamondLuckySent());
		bo.setDiamondSent(list.get(0).getDiamondSent());
		return bo;
	}

}

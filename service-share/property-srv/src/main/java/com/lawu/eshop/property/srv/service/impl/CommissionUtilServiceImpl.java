package com.lawu.eshop.property.srv.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.srv.bo.CommissionUtilBO;
import com.lawu.eshop.property.srv.service.CommissionUtilService;
import com.lawu.eshop.property.srv.service.PropertyService;

@Service
public class CommissionUtilServiceImpl implements CommissionUtilService{

	@Autowired
	private PropertyService propertyService;
	
	/**
	 * 获取用户点击广告，自己的收益余额与爱心账户
	 * @param clickMoney
	 * @return
	 * @author yangqh
	 * @date 2017年5月24日 上午11:52:31
	 */
	@Override
	public CommissionUtilBO getClickAdMine(BigDecimal clickMoney) {
		String ad_commission_0 = propertyService.getValue(PropertyType.ad_commission_0);
		String love_account_scale = propertyService.getValue(PropertyType.love_account_scale);
		double d_acture_in = 1 - Double.parseDouble(love_account_scale); // 用户实际进账比例：1-爱心账户比例
		
		BigDecimal b_ad_commission_0 = new BigDecimal(ad_commission_0);
		BigDecimal b_love_account_scale = new BigDecimal(love_account_scale);
		BigDecimal b_acture_in = BigDecimal.valueOf(d_acture_in);
		
		BigDecimal actureMoneyIn = clickMoney.multiply(b_ad_commission_0).multiply(b_acture_in).setScale(6, BigDecimal.ROUND_HALF_UP);//实际进余额
		BigDecimal actureLoveIn = clickMoney.multiply(b_ad_commission_0).multiply(b_love_account_scale).setScale(6, BigDecimal.ROUND_HALF_UP);//爱心账户
		
		CommissionUtilBO bo = new CommissionUtilBO();
		bo.setActureMoneyIn(actureMoneyIn);
		bo.setActureLoveIn(actureLoveIn);
		
		return bo;
	}

	/**
	 * 获取用户收入余额，按指定比例计算，计算爱心账户
	 * @param clickMoney
	 * @return
	 * @author yangqh
	 * @date 2017年5月24日 上午11:52:31
	 */
	@Override
	public CommissionUtilBO getIncomeMoney(BigDecimal clickMoney) {
		String love_account_scale = propertyService.getValue(PropertyType.love_account_scale);
		double d_acture_in = 1 - Double.parseDouble(love_account_scale); // 用户实际进账比例：1-爱心账户比例
		
		BigDecimal b_love_account_scale = new BigDecimal(love_account_scale);
		BigDecimal b_acture_in = BigDecimal.valueOf(d_acture_in);
		
		BigDecimal actureMoneyIn = clickMoney.multiply(b_acture_in).setScale(6, BigDecimal.ROUND_HALF_UP);//实际进余额
		BigDecimal actureLoveIn = clickMoney.multiply(b_love_account_scale).setScale(6, BigDecimal.ROUND_HALF_UP);//爱心账户
		
		CommissionUtilBO bo = new CommissionUtilBO();
		bo.setActureMoneyIn(actureMoneyIn);
		bo.setActureLoveIn(actureLoveIn);
		
		return bo;
	}

}

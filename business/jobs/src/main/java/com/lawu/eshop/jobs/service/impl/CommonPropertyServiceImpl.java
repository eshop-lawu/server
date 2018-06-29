package com.lawu.eshop.jobs.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lawu.eshop.jobs.service.CommonPropertyService;
import com.lawu.eshop.jobs.service.PropertySrvService;
import com.lawu.eshop.property.dto.PropertyDTO;
import com.lawu.framework.web.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.property.constants.PropertyType;

/**
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月24日 下午7:42:46
 *
 */
@Service
public class CommonPropertyServiceImpl implements CommonPropertyService {

	@Autowired
	private PropertySrvService propertyService;

	@Override
	public Map<String, BigDecimal> getAdCommissionPropertys_bak() {
		Result result = propertyService.getValue(PropertyType.ad_commission_0);
		String ad_commission_0 = result.getModel().toString();
		result = propertyService.getValue(PropertyType.ad_commission_1);
		String ad_commission_1 = result.getModel().toString();
		result = propertyService.getValue(PropertyType.ad_commission_2);
		String ad_commission_2 = result.getModel().toString();
		result = propertyService.getValue(PropertyType.ad_commission_3);
		String ad_commission_3 = result.getModel().toString();
		result = propertyService.getValue(PropertyType.love_account_scale);
		String love_account_scale = result.getModel().toString();
		double d_love_account_scale = Double.parseDouble(love_account_scale);
		double d_acture_in = 1 - d_love_account_scale; // 用户实际进账比例：1-爱心账户比例

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("ad_commission_0", new BigDecimal(ad_commission_0)); //
		map.put("ad_commission_1", new BigDecimal(ad_commission_1)); // 上1级提成比例
		map.put("ad_commission_2", new BigDecimal(ad_commission_2)); // 上2级提成比例
		map.put("ad_commission_3", new BigDecimal(ad_commission_3)); // 上3级提成比例
		map.put("acture_in_scale", new BigDecimal(String.valueOf(d_acture_in)));// 实际收入比例
		map.put("love_account_scale", new BigDecimal(love_account_scale)); // 爱心账户比例

		return map;
	}

	@Override
	public Map<String, BigDecimal> getAdCommissionPropertys() {
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		Result<List<PropertyDTO>> result = propertyService.getAll();
		for(PropertyDTO dto : result.getModel()){
			if(PropertyType.ad_commission_0.equals(dto.getName())){
				map.put(PropertyType.ad_commission_0, new BigDecimal(dto.getValue().toString())); // 基础比例
			} else if(PropertyType.ad_commission_1.equals(dto.getName())){
				map.put(PropertyType.ad_commission_1, new BigDecimal(dto.getValue().toString())); // 上1级提成比例
			} else if(PropertyType.ad_commission_2.equals(dto.getName())){
				map.put(PropertyType.ad_commission_2, new BigDecimal(dto.getValue().toString())); // 上2级提成比例
			} else if(PropertyType.ad_commission_3.equals(dto.getName())){
				map.put(PropertyType.ad_commission_3, new BigDecimal(dto.getValue().toString())); // 上3级提成比例
			} else if(PropertyType.love_account_scale.equals(dto.getName())){
				String love_account_scale = dto.getValue().toString();
				map.put(PropertyType.love_account_scale, new BigDecimal(love_account_scale)); // 爱心账户比例
				double d_love_account_scale = Double.parseDouble(love_account_scale);
				double d_acture_in = 1 - d_love_account_scale; // 用户实际进账比例：1-爱心账户比例
				map.put("acture_in_scale", new BigDecimal(String.valueOf(d_acture_in))); // 实际收入比例
			}
		}
		return map;
	}

	@Override
	public Map<String, BigDecimal> getSaleCommissionPropertys_bak() {
		Result result = propertyService.getValue(PropertyType.sale_commission_1);
		String sale_commission_1 = result.getModel().toString();
		result = propertyService.getValue(PropertyType.sale_commission_2);
		String sale_commission_2 = result.getModel().toString();
		result = propertyService.getValue(PropertyType.sale_commission_3);
		String sale_commission_3 = result.getModel().toString();
		result = propertyService.getValue(PropertyType.sale_commission_add_scope);
		String sale_commission_add_scope = result.getModel().toString();

		result = propertyService.getValue(PropertyType.love_account_scale);
		String love_account_scale = result.getModel().toString();
		double d_love_account_scale = Double.parseDouble(love_account_scale);
		double d_acture_in = 1 - d_love_account_scale; // 用户实际进账比例：1-爱心账户比例

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("sale_commission_1", new BigDecimal(sale_commission_1)); // 上1级提成比例
		map.put("sale_commission_2", new BigDecimal(sale_commission_2)); // 上2级提成比例
		map.put("sale_commission_3", new BigDecimal(sale_commission_3)); // 上3级提成比例
		map.put("acture_in_scale", new BigDecimal(String.valueOf(d_acture_in))); // 实际收入比例
		map.put("love_account_scale", new BigDecimal(love_account_scale)); // 爱心账户比例
		map.put("sale_commission_add_scope", new BigDecimal(sale_commission_add_scope)); // 上一个等级提成提升幅度

		return map;
	}

	@Override
	public Map<String, BigDecimal> getSaleCommissionPropertys() {
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		Result<List<PropertyDTO>> result = propertyService.getAll();
		for(PropertyDTO dto : result.getModel()){
			if(PropertyType.sale_commission_0.equals(dto.getName())){
				map.put(PropertyType.sale_commission_0, new BigDecimal(dto.getValue().toString())); // 基础比例
			} else if(PropertyType.sale_commission_1.equals(dto.getName())){
				map.put(PropertyType.sale_commission_1, new BigDecimal(dto.getValue().toString())); // 上1级提成比例
			} else if(PropertyType.sale_commission_2.equals(dto.getName())){
				map.put(PropertyType.sale_commission_2, new BigDecimal(dto.getValue().toString())); // 上2级提成比例
			} else if(PropertyType.sale_commission_3.equals(dto.getName())){
				map.put(PropertyType.sale_commission_3, new BigDecimal(dto.getValue().toString())); // 上3级提成比例
			} else if(PropertyType.sale_commission_add_scope.equals(dto.getName())){
				map.put(PropertyType.sale_commission_add_scope, new BigDecimal(dto.getValue().toString())); // 上一个等级提成提升幅度
			} else if(PropertyType.love_account_scale.equals(dto.getName())){
				String love_account_scale = dto.getValue().toString();
				map.put(PropertyType.love_account_scale, new BigDecimal(love_account_scale)); // 爱心账户比例
				double d_love_account_scale = Double.parseDouble(love_account_scale);
				double d_acture_in = 1 - d_love_account_scale; // 用户实际进账比例：1-爱心账户比例
				map.put("acture_in_scale", new BigDecimal(String.valueOf(d_acture_in))); // 实际收入比例
			}
		}
		return map;
	}

	@Override
	public Map<String, Object> getPropertys() {
		Result<List<PropertyDTO>> result = propertyService.getAll();
		List<PropertyDTO> dtos = result.getModel();
		Map<String, Object> map = new HashMap<>();
		for(PropertyDTO dto : dtos){
			map.put(dto.getName(),dto.getValue());
		}
		return map;
	}

}

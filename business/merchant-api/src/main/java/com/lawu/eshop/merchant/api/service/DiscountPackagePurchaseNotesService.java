package com.lawu.eshop.merchant.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.mall.dto.DiscountPackagePurchaseNotesDTO;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/3/27
 */
@FeignClient(value= "mall-srv", path = "discountPackagePurchaseNotes/")
public interface DiscountPackagePurchaseNotesService {
	
	/**
	 * 查询所有的购买须知
	 * 
	 * @return
	 * @author Sunny
	 * @date 2017年7月31日
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	Result<List<DiscountPackagePurchaseNotesDTO>> list();
	
}

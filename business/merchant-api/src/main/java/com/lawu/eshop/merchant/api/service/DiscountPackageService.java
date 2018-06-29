package com.lawu.eshop.merchant.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.dto.DiscountPackageDetailDTO;
import com.lawu.eshop.mall.dto.DiscountPackageQueryDTO;
import com.lawu.eshop.mall.param.DiscountPackageSaveParam;
import com.lawu.eshop.mall.param.DiscountPackageUpdateParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageQueryForeignParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/3/27
 */
@FeignClient(value= "mall-srv", path = "discountPackage/")
public interface DiscountPackageService {
	
	/**
	 * 根据商家id查询商家的所有优惠套餐<p>
	 * 商家端
	 * 
	 * @param merchantId 商家id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@RequestMapping(value = "merchant/list/{merchantId}", method = RequestMethod.POST)
	Result<Page<DiscountPackageQueryDTO>> listForMerchant(@PathVariable("merchantId") Long merchantId, @RequestBody DiscountPackageQueryForeignParam param);
	
	/**
	 * 根据优惠套餐id查询优惠套餐详情
	 * 
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	Result<DiscountPackageDetailDTO> get(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId);
	
	/**
	 * 保存优惠套餐
	 * 
	 * @param merchantId 商家id
	 * @param discountPackageSaveParam 保存参数
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "{merchantId}", method = RequestMethod.POST)
	Result save(@PathVariable("merchantId")Long merchantId, @RequestBody DiscountPackageSaveParam discountPackageSaveParam);
	
	/**
	 * 更新优惠套餐
	 * 
	 * @param id
	 * @param discountPackageUpdateParam
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "{merchantId}/{id}", method = RequestMethod.PUT)
	Result update(@PathVariable("merchantId")Long merchantId, @PathVariable("id")Long id, @RequestBody DiscountPackageUpdateParam discountPackageUpdateParam);
	
	/**
	 * 删除优惠套餐
	 * 
	 * @param merchantId
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "{merchantId}/delete/{id}", method = RequestMethod.PUT)
	Result delete(@PathVariable("merchantId")Long merchantId, @PathVariable("id")Long id);
	
	/**
	 * 上架优惠套餐
	 * 
	 * @param merchantId
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "{merchantId}/up/{id}", method = RequestMethod.PUT)
	Result up(@PathVariable("merchantId")Long merchantId, @PathVariable("id")Long id);
	
	/**
	 * 上架优惠套餐
	 * 
	 * @param merchantId
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "{merchantId}/down/{id}", method = RequestMethod.PUT)
	Result down(@PathVariable("merchantId")Long merchantId, @PathVariable("id") Long id);
	
	/**
	 * 删除优惠套餐
	 * 
	 * @param merchantId
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "{merchantId}/delete", method = RequestMethod.PUT)
	Result delete(@PathVariable("merchantId")Long merchantId, @RequestBody List<Long> idList);
	
	/**
	 * 批量上架优惠套餐
	 * 
	 * @param merchantId
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "{merchantId}/up", method = RequestMethod.PUT)
	Result up(@PathVariable("merchantId")Long merchantId, @RequestBody List<Long> idList);
	
	/**
	 * 批量上架优惠套餐
	 * 
	 * @param merchantId
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "{merchantId}/down", method = RequestMethod.PUT)
	Result down(@PathVariable("merchantId")Long merchantId, @RequestBody List<Long> idList);
	
}

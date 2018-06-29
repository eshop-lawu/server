package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.mall.dto.DiscountPackageDetailDTO;
import com.lawu.eshop.mall.dto.DiscountPackageDetailForMemberDTO;
import com.lawu.eshop.mall.dto.DiscountPackageQueryDTO;
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
	 * 用户端
	 * 
	 * @param merchantId 商家id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@RequestMapping(value = "member/list/{merchantId}", method = RequestMethod.GET)
	Result<Page<DiscountPackageQueryDTO>> listForMember(@PathVariable("merchantId") Long merchantId);
	
	/**
	 * 根据优惠套餐id查询优惠套餐详情
	 * 
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@RequestMapping(value = "member/{id}", method = RequestMethod.GET)
	Result<DiscountPackageDetailForMemberDTO> getByMember(@PathVariable("id") Long id);
}

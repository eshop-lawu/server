package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.autotest.client.AutoTesting;
import com.lawu.eshop.mall.dto.DiscountPackageDetailForMemberDTO;
import com.lawu.eshop.mall.dto.DiscountPackageQueryDTO;
import com.lawu.eshop.member.api.service.DiscountPackageService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author Sunny
 * @date 2017/3/27
 */
@Api(tags = "discountPackage")
@RestController
@RequestMapping(value = "discountPackage/")
public class DiscountPackageController extends BaseController {

	@Autowired
	private DiscountPackageService discountPackageService;

	/**
	 * 根据商家id查询商家的所有优惠套餐
	 * 
	 * @param merchantId 商家id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@AutoTesting
	@Audit(date = "2017-07-04", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询优惠套餐列表", notes = "根据商家id查询商家全部优惠套餐列表,不分页。[]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "list/{merchantId}", method = RequestMethod.GET)
	public Result<Page<DiscountPackageQueryDTO>> list(@ApiParam(value = "商家id") @PathVariable("merchantId") Long merchantId) {
		Result<Page<DiscountPackageQueryDTO>> result = discountPackageService.listForMember(merchantId);
		return successGet(result);
	}
	
	/**
	 * 根据优惠套餐id查询优惠套餐详情
	 * 
	 * @param id 优惠套餐id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@AutoTesting
	@Audit(date = "2017-07-04", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询优惠套餐详情", notes = "查询单个优惠套餐详情。[]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Result<DiscountPackageDetailForMemberDTO> get(@ApiParam(value = "优惠套餐id") @PathVariable("id") Long id) {
		Result<DiscountPackageDetailForMemberDTO> result = discountPackageService.getByMember(id);
		return successGet(result);
	}
}

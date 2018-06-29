package com.lawu.eshop.merchant.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.dto.DiscountPackagePurchaseNotesDTO;
import com.lawu.eshop.merchant.api.service.DiscountPackagePurchaseNotesService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 
 * @author jiangxinjun
 * @date 2017年7月31日
 */
@Api(tags = "discountPackagePurchaseNotes")
@RestController
@RequestMapping(value = "discountPackagePurchaseNotes/")
public class DiscountPackagePurchaseNotesController extends BaseController {
	
	@Autowired
	private DiscountPackagePurchaseNotesService discountPackagePurchaseNotesService;
	
	/**
	 * 根据商家id查询商家的所有优惠套餐
	 * 
	 * @param merchantId 商家id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@Audit(date = "2017-08-01", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询优惠套餐购买须知", notes = "查询所有的优惠套餐购买须知。[]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Result<List<DiscountPackagePurchaseNotesDTO>> list(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
		Result<List<DiscountPackagePurchaseNotesDTO>> result = discountPackagePurchaseNotesService.list();
		return successGet(result);
	}
	
}

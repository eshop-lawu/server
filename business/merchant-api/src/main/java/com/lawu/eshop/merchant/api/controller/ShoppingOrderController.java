package com.lawu.eshop.merchant.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.dto.ExpressCompanyDTO;
import com.lawu.eshop.merchant.api.service.ExpressCompanyService;
import com.lawu.eshop.merchant.api.service.ShoppingOrderService;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressInfoDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExtendDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemRefundForMerchantDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderNumberOfOrderStatusForMerchantForeignDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderQueryToMerchantDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderStatusDTO;
import com.lawu.eshop.order.param.ShoppingOrderLogisticsInformationParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderLogisticsInformationForeignParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToMerchantParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundQueryForeignParam;
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
 * 
 * @author Sunny
 * @date 2017/04/12
 */
@Api(tags = "shoppingOrder")
@RestController
@RequestMapping(value = "shoppingOrder/")
public class ShoppingOrderController extends BaseController {

	@Autowired
	private ShoppingOrderService shoppingOrderService;

	@Autowired
	private ExpressCompanyService expressCompanyService;

	@SuppressWarnings("unchecked")
	@Audit(date = "2017-04-15", reviewer = "孙林青")
	@ApiOperation(value = "分页查询订单", notes = "根据查询参数分页查询。[]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "selectPageByMerchantId", method = RequestMethod.GET)
	public Result<Page<ShoppingOrderQueryToMerchantDTO>> selectPageByMerchantId(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(name = "param", value = "查询参数") ShoppingOrderQueryForeignToMerchantParam param) {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());

		Result<Page<ShoppingOrderQueryToMerchantDTO>> result = shoppingOrderService.selectPageByMerchantId(merchantId, param);
		if (!isSuccess(result)) {
			return successGet(result.getRet());
		}

		return successGet(result);
	}
	
	/**
	 * @deprecated
	 * @see #detai
	 * @param token
	 * @param id
	 * @return
	 * @author jiangxinjun
	 * @date 2017年9月6日
	 */
	@Deprecated
	@Audit(date = "2017-04-15", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询购物订单详情", notes = "根据购物订单id查询购物订单详情。[1100|1024]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public Result<ShoppingOrderExtendDetailDTO> get(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(value = "购物订单id", required = true) Long id) {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		Result<ShoppingOrderExtendDetailDTO> result = shoppingOrderService.get(id, merchantId);
		return successGet(result);
	}

	@Audit(date = "2017-09-07", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询购物订单详情", notes = "根据购物订单id查询购物订单详情。[1100|1024]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public Result<ShoppingOrderDetailDTO> detail(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(value = "购物订单id", required = true) Long id) {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		Result<ShoppingOrderDetailDTO> result = shoppingOrderService.detail(id, merchantId);
		return successGet(result);
	}

	@Audit(date = "2017-04-15", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "填写物流信息", notes = "填写物流信息。[1004|1100|1024|4026]（蒋鑫俊）", httpMethod = "PUT")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "fillLogisticsInformation/{id}", method = RequestMethod.PUT)
	public Result fillLogisticsInformation(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable @ApiParam(value = "购物订单id", required = true) Long id, @ModelAttribute @ApiParam(name = "param", value = "物流参数") @Validated ShoppingOrderLogisticsInformationForeignParam param, BindingResult bindingResult) {
		// 校验参数
		String message = validate(bindingResult);
		if (message != null) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
		}
		ShoppingOrderLogisticsInformationParam shoppingOrderLogisticsInformationParam = new ShoppingOrderLogisticsInformationParam();
		shoppingOrderLogisticsInformationParam.setIsNeedsLogistics(param.getIsNeedsLogistics());
		if (param.getIsNeedsLogistics()) {
			Result<ExpressCompanyDTO> getResult = expressCompanyService.get(param.getExpressCompanyId());
			if (!isSuccess(getResult)) {
				return successCreated(getResult.getRet());
			}
			shoppingOrderLogisticsInformationParam.setExpressCompanyId(getResult.getModel().getId());
			shoppingOrderLogisticsInformationParam.setExpressCompanyCode(getResult.getModel().getCode());
			shoppingOrderLogisticsInformationParam.setExpressCompanyName(getResult.getModel().getName());
			shoppingOrderLogisticsInformationParam.setWaybillNum(param.getWaybillNum());
		}
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		Result result = shoppingOrderService.fillLogisticsInformation(id, merchantId, shoppingOrderLogisticsInformationParam);
		return successCreated(result);
	}
	
	@Audit(date = "2017-05-02", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "分页查询退款记录", notes = "分页查询退款记录。[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "selectRefundPageByMerchantId", method = RequestMethod.GET)
    public Result<Page<ShoppingOrderItemRefundForMerchantDTO>> selectRefundPageByMerchantId(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(name = "param", value="查询参数") ShoppingRefundQueryForeignParam param) {
    	Long merchantId = UserUtil.getCurrentUserId(getRequest());
    	
    	Result<Page<ShoppingOrderItemRefundForMerchantDTO>> result = shoppingOrderService.selectRefundPageByMerchantId(merchantId, param);
    	
    	if (!isSuccess(result)) {
    		return successGet(result.getRet());
    	}
    	return successGet(result);
    }
	
	@Audit(date = "2017-05-02", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询订单各种状态的数量", notes = "查询订单各种状态的数量。[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "numberOfOrderStartusByMerchant", method = RequestMethod.GET)
    public Result<ShoppingOrderNumberOfOrderStatusForMerchantForeignDTO> numberOfOrderStartusByMerchant(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
    	Long merchantId = UserUtil.getCurrentUserId(getRequest());
    	
    	Result<ShoppingOrderNumberOfOrderStatusForMerchantForeignDTO> result = shoppingOrderService.numberOfOrderStartusByMerchant(merchantId);
    	
    	if (!isSuccess(result)) {
    		return successGet(result.getRet());
    	}
    	return successGet(result);
    }
	
	/**
	 * @deprecated
	 * @see #expressInfo(String, Long)
	 * @param token
	 * @param id
	 * @return
	 * @author jiangxinjun
	 * @date 2017年9月6日
	 */
	@Deprecated
	@Audit(date = "2017-05-03", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "查询物流动态", notes = "查询物流动态。[1002|1003]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getExpressInfo/{id}", method = RequestMethod.GET)
    public Result<ShoppingOrderExpressDTO> getExpressInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(value = "购物订单id", required = true) Long id) {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		Result<ShoppingOrderExpressDTO> resultShoppingOrderExpressDTO = shoppingOrderService.getExpressInfo(id, merchantId);
    	return successGet(resultShoppingOrderExpressDTO);
    }

	@Audit(date = "2017-09-07", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "查询物流动态", notes = "查询物流动态。[1002|1003]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "expressInfo/{id}", method = RequestMethod.GET)
    public Result<ShoppingOrderExpressInfoDTO> expressInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(value = "购物订单id", required = true) Long id) {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		Result<ShoppingOrderExpressInfoDTO> result = shoppingOrderService.expressInfo(id, merchantId);
    	return successGet(result);
    }

	@Audit(date = "2018-04-27", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "查询订单状态", notes = "查询订单状态[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "orderStatus/{id}", method = RequestMethod.GET)
    public Result<ShoppingOrderStatusDTO> orderStatus(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
            @PathVariable("id") @ApiParam(name = "id", value = "购物订单id") Long id) {
        Long merchantId = UserUtil.getCurrentUserId(getRequest());
        Result<ShoppingOrderStatusDTO> result = shoppingOrderService.orderStatus(id, merchantId);
        return successGet(result);
    }
}

package com.lawu.eshop.merchant.api.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.FileDirConstant;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.merchant.api.MerchantApiConfig;
import com.lawu.eshop.merchant.api.service.AddressService;
import com.lawu.eshop.merchant.api.service.ShoppingRefundDetailService;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressInfoDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingRefundDetailDTO;
import com.lawu.eshop.order.param.ShoppingRefundDetailRerurnAddressParam;
import com.lawu.eshop.order.param.foreign.RefuseRefundForeignParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundDetailAgreeToApplyForeignParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundDetailAgreeToRefundForeignParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundDetailRerurnAddressForeignParam;
import com.lawu.eshop.user.constants.UploadFileTypeConstant;
import com.lawu.eshop.user.dto.AddressDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.media.util.UploadFileUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author Sunny 
 * @date 2017/04/06
 */
@Api(tags = "shoppingRefundDetail")
@RestController
@RequestMapping(value = "shoppingRefundDetail/")
public class ShoppingRefundDetailController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(ShoppingRefundDetailController.class);
	@Autowired
	private ShoppingRefundDetailService shoppingRefundDetailService;
	
	@Autowired
	private AddressService addressService;

	@Autowired
	private MerchantApiConfig merchantApiConfig;
	
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
	@Audit(date = "2017-04-15", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询退货的物流信息", notes = "查询退货的物流信息。[1003]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getExpressInfo/{id}", method = RequestMethod.GET)
    public Result<ShoppingOrderExpressDTO> getExpressInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(value = "退款详情id", required = true) Long id) {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		Result<ShoppingOrderExpressDTO> result = shoppingRefundDetailService.getExpressInfo(id, merchantId);
    	return successCreated(result);
    }

	@Audit(date = "2017-09-07", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询退货的物流信息", notes = "查询退货的物流信息。[1003]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "expressInfo/{id}", method = RequestMethod.GET)
    public Result<ShoppingOrderExpressInfoDTO> expressInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(value = "退款详情id", required = true) Long id) {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		Result<ShoppingOrderExpressInfoDTO> result = shoppingRefundDetailService.expressInfo(id, merchantId);
    	return successCreated(result);
    }
	
	@Audit(date = "2017-04-15", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询退款详情", notes = "根据购物订单项id查询退款详情。[1100|1024]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getRefundDetail/{shoppingOrderItemId}", method = RequestMethod.GET)
    public Result<ShoppingRefundDetailDTO> getRefundDetail(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("shoppingOrderItemId") @ApiParam(name = "shoppingOrderItemId", value = "购物订单项id") Long shoppingOrderItemId) {
    	Long merchantId = UserUtil.getCurrentUserId(getRequest());
		Result<ShoppingRefundDetailDTO> result = shoppingRefundDetailService.getRefundDetail(shoppingOrderItemId, merchantId);
    	return successCreated(result);
    }
	
	@Deprecated
	@Audit(date = "2017-04-15", reviewer = "孙林青")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "商家确认买家的退货申请[Deprecated]", notes = "商家是否同意买家的退货申请。[1004|1100|1024|4027]（蒋鑫俊）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "agreeToApply/{id}", method = RequestMethod.PUT)
    public Result<ShoppingOrderExpressDTO> agreeToApply(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(value = "退款详情id") @Validated @NotNull Long id, @ModelAttribute @ApiParam(value = "申请审核参数") @Validated ShoppingRefundDetailAgreeToApplyForeignParam param, BindingResult bindingResult) {
		String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	Long merchantId = UserUtil.getCurrentUserId(getRequest());
    	Result result = shoppingRefundDetailService.agreeToApply(id, merchantId, param);
    	return successCreated(result);
    }
	
	@Audit(date = "2017-04-15", reviewer = "孙林青")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "商家填写退货地址 ", notes = "商家填写退货地址 。[1004|1100|1024|4029]（蒋鑫俊）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "fillReturnAddress/{id}", method = RequestMethod.PUT)
    public Result<ShoppingOrderExpressDTO> fillReturnAddress(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(value = "退款详情id", required = true) Long id, @ModelAttribute @ApiParam(value = "退货地址参数") ShoppingRefundDetailRerurnAddressForeignParam foreignParam, BindingResult bindingResult) {
		String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
		ShoppingRefundDetailRerurnAddressParam param = new ShoppingRefundDetailRerurnAddressParam();
    	if (foreignParam.getAddressId() != null && foreignParam.getAddressId() > 0) {
    		Result<AddressDTO> getResult = addressService.get(foreignParam.getAddressId());
    		if (!isSuccess(getResult)) {
    			return successCreated(getResult);
    		}
    		param.setConsigneeName(getResult.getModel().getName());
    		param.setConsigneeMobile(getResult.getModel().getMobile());
    		param.setConsigneeAddress(getResult.getModel().getRegionName() + getResult.getModel().getAddr());
    	}
    	Long merchantId = UserUtil.getCurrentUserId(getRequest());
    	Result result = shoppingRefundDetailService.fillReturnAddress(id, merchantId, param);
    	return successCreated(result);
    }
	
	@Audit(date = "2017-04-15", reviewer = "孙林青")
	@SuppressWarnings({ "rawtypes" })
	@ApiOperation(value = "商家是否同意退款", notes = "商家是否同意退款。[1002|1003|4011|4013]（蒋鑫俊）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "agreeToRefund/{id}", method = RequestMethod.PUT)
    public Result agreeToRefund(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(value = "退款详情id", required = true) Long id, @ModelAttribute @ApiParam(value = "同意退款参数") @Validated ShoppingRefundDetailAgreeToRefundForeignParam param, BindingResult bindingResult) {
		String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	Long merchantId = UserUtil.getCurrentUserId(getRequest());
    	Result result = shoppingRefundDetailService.agreeToRefund(id, merchantId, param);
    	return successCreated(result);
    }

	@SuppressWarnings("rawtypes")
	@Deprecated
	@Audit(date = "2017-08-01", reviewer = "孙林青")
	@ApiOperation(value = "商家拒绝退款", notes = "商家拒绝退款。。[1002|1003|4011|4013|4028]（章勇）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "refuseRefund/{id}", method = RequestMethod.POST)
    public Result refuseRefund(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
							   @PathVariable("id") @ApiParam(required = true, value = "退款详情id") Long id,
							   @RequestParam("refusalReasons") @ApiParam(required = true, value = "拒绝原因") String refusalReasons){
		if(StringUtils.isEmpty(refusalReasons)){
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
		}
		HttpServletRequest request = getRequest();
		Long merchantId = UserUtil.getCurrentUserId(request);
		Collection<Part> parts = null;
		try {
			parts = request.getParts();

		} catch (IOException e) {
			logger.info("IOException {}",e);
			return successCreated(e.getMessage());
		} catch (ServletException ex) {
			logger.info("ServletException:{}",ex);
		}
		StringBuilder refuseImages = new StringBuilder();
		if (parts != null && StringUtils.isNotEmpty(parts.toString())) {
			for (Part part : parts) {
				Map<String, String> map = UploadFileUtil.uploadImages(request, FileDirConstant.DIR_ORDER, part, merchantApiConfig.getImageUploadUrl());
				String flag = map.get("resultFlag");
				if (UploadFileTypeConstant.UPLOAD_RETURN_TYPE.equals(flag)) {
					//有图片上传成功返回,拼接图片url
					String imgUrl = map.get("imgUrl");
					if (StringUtils.isNotEmpty(imgUrl)) {
						refuseImages.append(imgUrl + ",");
					}
				} else {
					return successCreated(Integer.valueOf(flag));
				}
			}
		}
		ShoppingRefundDetailAgreeToRefundForeignParam param = new ShoppingRefundDetailAgreeToRefundForeignParam();
		param.setIsAgree(false);
		if(StringUtils.isNotEmpty(refusalReasons)){
			param.setRefusalReasons(refusalReasons);
		}
		param.setRefuseImages(refuseImages.toString());
		Result result = shoppingRefundDetailService.agreeToRefund(id, merchantId, param);
		return successCreated(result);
	}

	@Audit(date = "2017-08-08", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "商家拒绝退款", notes = "商家拒绝退款。。[1002|1003|4011|4013|4028]（蒋鑫俊）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "rejectRefund/{id}", method = RequestMethod.POST)
    public Result rejectRefund(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(required = true, value = "退款详情id") Long id, @ModelAttribute @ApiParam(value = "同意退款参数") @Validated RefuseRefundForeignParam param, BindingResult bindingResult){
		String message = validate(bindingResult);
		if(message != null){
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
		}
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		ShoppingRefundDetailAgreeToRefundForeignParam shoppingRefundDetailAgreeToRefundForeignParam = new ShoppingRefundDetailAgreeToRefundForeignParam();
		shoppingRefundDetailAgreeToRefundForeignParam.setIsAgree(false);
		shoppingRefundDetailAgreeToRefundForeignParam.setRefusalReasons(param.getRefusalReasons());
		shoppingRefundDetailAgreeToRefundForeignParam.setRefuseImages(param.getRefuseImages());
		Result result = shoppingRefundDetailService.agreeToRefund(id, merchantId, shoppingRefundDetailAgreeToRefundForeignParam);
		return successCreated(result);
	}
}

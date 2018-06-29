package com.lawu.eshop.member.api.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

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
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.FileDirConstant;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.MemberApiConfig;
import com.lawu.eshop.member.api.service.PropertyInfoService;
import com.lawu.eshop.member.api.service.ShoppingOrderService;
import com.lawu.eshop.order.dto.ShoppingOrderPaymentDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressInfoDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExtendDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExtendQueryDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemRefundDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderPaymentForeignDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderStatusDTO;
import com.lawu.eshop.order.param.ShoppingOrderRequestRefundParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToMemberParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderRequestRefundForeignParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundQueryForeignParam;
import com.lawu.eshop.property.dto.PropertyBalanceDTO;
import com.lawu.eshop.user.constants.UploadFileTypeConstant;
import com.lawu.framework.core.page.Page;
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
@Api(tags = "shoppingOrder")
@RestController
@RequestMapping(value = "shoppingOrder/")
public class ShoppingOrderController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(ShoppingOrderController.class);

	@Autowired
	private ShoppingOrderService shoppingOrderService;

	@Autowired
	private PropertyInfoService propertyInfoService;

	@Autowired
	private MemberApiConfig memberApiConfig;

	@Audit(date = "2017-04-12", reviewer = "孙林青")
	@ApiOperation(value = "分页查询购物订单", notes = "根据订单状态和是否评论分页查询购物订单。[]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "selectPageByMemberId", method = RequestMethod.GET)
	public Result<Page<ShoppingOrderExtendQueryDTO>> page(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(name = "param", value = "购物订单查询参数") ShoppingOrderQueryForeignToMemberParam param) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());

		Result<Page<ShoppingOrderExtendQueryDTO>> result = shoppingOrderService.selectPageByMemberId(memberId, param);

		if (!isSuccess(result)) {
			return successGet(result.getRet());
		}

		return successGet(result.getModel());
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
	@Audit(date = "2017-04-12", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询购物订单详情", notes = "根据购物订单id查询购物订单详情。[1100|1024]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public Result<ShoppingOrderExtendDetailDTO> get(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(value = "购物订单id", required = true) Long id) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result<ShoppingOrderExtendDetailDTO> result = shoppingOrderService.get(id, memberId);
		return successGet(result);
	}


	@Audit(date = "2017-09-07", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询购物订单详情", notes = "根据购物订单id查询购物订单详情。[1100|1024]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public Result<ShoppingOrderExtendDetailDTO> detail(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(value = "购物订单id", required = true) Long id) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result<ShoppingOrderDetailDTO> result = shoppingOrderService.detail(id, memberId);
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
	@Audit(date = "2017-04-12", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询物流动态", notes = "查询物流动态。[1002|1003]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "getExpressInfo/{id}", method = RequestMethod.GET)
	public Result<ShoppingOrderExpressDTO> getExpressInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(name = "id", value = "购物订单id") Long id) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result<ShoppingOrderExpressDTO> result = shoppingOrderService.getExpressInfo(id, memberId);
		return successGet(result);
	}

	@Audit(date = "2017-09-07", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询物流动态", notes = "查询物流动态。[1002|1003]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "expressInfo/{id}", method = RequestMethod.GET)
	public Result<ShoppingOrderExpressInfoDTO> expressInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(name = "id", value = "购物订单id") Long id) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result<ShoppingOrderExpressInfoDTO> result = shoppingOrderService.expressInfo(id, memberId);
		return successGet(result);
	}

	@Audit(date = "2017-04-12", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "取消购物订单", notes = "取消购物订单。[1100|1024|4002]（蒋鑫俊）", httpMethod = "PUT")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "cancelOrder/{id}", method = RequestMethod.PUT)
	public Result cancelOrder(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(value = "购物订单id", required = true) Long id) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result result = shoppingOrderService.cancelOrder(memberId, id);
		return successCreated(result);
	}

	@Audit(date = "2017-04-12", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "删除购物订单", notes = "根据购物订单id删除购物订单。[1100|1024|4003]（蒋鑫俊）", httpMethod = "DELETE")
	@ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
	@Authorization
	@RequestMapping(value = "deleteOrder/{id}", method = RequestMethod.DELETE)
	public Result deleteOrder(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(name = "id", value = "购物订单id") Long id) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result result = shoppingOrderService.deleteOrder(memberId, id);
		if (!isSuccess(result)) {
			return successCreated(result);
		}
		return successDelete();
	}

	@Audit(date = "2017-04-12", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "确认收货", notes = "根据购物订单id确认收货。[1002|1003|4005]（蒋鑫俊）", httpMethod = "PUT")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "tradingSuccess/{id}", method = RequestMethod.PUT)
	public Result tradingSuccess(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(name = "id", value = "购物订单id") Long id) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result result = shoppingOrderService.tradingSuccess(id, memberId);
		return successCreated(result);
	}

	@Deprecated
	@Audit(date = "2017-04-12", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "申请退款[Deprecated]", notes = "根据购物订单项id申请退款。[1002|1003|4005]（蒋鑫俊）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "requestRefund/{shoppingOrderitemId}", method = RequestMethod.POST)
	public Result requestRefund(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("shoppingOrderitemId") @ApiParam(name = "shoppingOrderitemId", value = "购物订单项id", required = true) Long shoppingOrderitemId, @ModelAttribute @ApiParam(name = "param", value = "退款参数") @Validated ShoppingOrderRequestRefundForeignParam param, BindingResult bindingResult) {
		String message = validate(bindingResult);
		if (message != null) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
		}
		HttpServletRequest request = getRequest();
		StringBuilder headImg = new StringBuilder();
		Collection<Part> parts = null;
		try {
			parts = request.getParts();
		} catch (IOException | ServletException e) {
			logger.error("读取上传文件异常", e);
			return successCreated(e.getMessage());
		}
		if (parts != null && StringUtils.isNotEmpty(parts.toString())) {
			for (Part part : parts) {
				Map<String, String> map = UploadFileUtil.uploadImages(request, FileDirConstant.DIR_ORDER, part, memberApiConfig.getImageUploadUrl());
				String flag = map.get("resultFlag");
				if (UploadFileTypeConstant.UPLOAD_RETURN_TYPE.equals(flag)) {
					// 有图片上传成功返回,拼接图片url
					String imgUrl = map.get("imgUrl");
					if (headImg.length() > 0) {
						headImg.append(",");
					}
					headImg.append(imgUrl);
				} else {
					return successCreated(Integer.valueOf(flag));
				}
			}
		}
		ShoppingOrderRequestRefundParam shoppingOrderRequestRefundParam = new ShoppingOrderRequestRefundParam();
		shoppingOrderRequestRefundParam.setDescription(param.getDescription());
		shoppingOrderRequestRefundParam.setReason(param.getReason());
		shoppingOrderRequestRefundParam.setType(param.getType());
		shoppingOrderRequestRefundParam.setVoucherPicture(headImg.toString());
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result result = shoppingOrderService.requestRefund(shoppingOrderitemId, memberId, shoppingOrderRequestRefundParam);
		return successCreated(result);
	}

	@Audit(date = "2017-08-08", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "申请退款", notes = "申请退款。[1002|1003|4005]（蒋鑫俊）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "applyRefund/{shoppingOrderitemId}", method = RequestMethod.POST)
	public Result applyRefund(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("shoppingOrderitemId") @ApiParam(name = "shoppingOrderitemId", value = "购物订单项id", required = true) Long shoppingOrderitemId, @ModelAttribute @ApiParam(name = "param", value = "退款参数") @Validated ShoppingOrderRequestRefundForeignParam param, BindingResult bindingResult) {
		String message = validate(bindingResult);
		if (message != null) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
		}
		ShoppingOrderRequestRefundParam shoppingOrderRequestRefundParam = new ShoppingOrderRequestRefundParam();
		shoppingOrderRequestRefundParam.setDescription(param.getDescription());
		shoppingOrderRequestRefundParam.setReason(param.getReason());
		shoppingOrderRequestRefundParam.setType(param.getType());
		shoppingOrderRequestRefundParam.setVoucherPicture(param.getVoucherPicture());
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result result = shoppingOrderService.requestRefund(shoppingOrderitemId, memberId, shoppingOrderRequestRefundParam);
		return successCreated(result);
	}

	@Audit(date = "2017-04-12", reviewer = "孙林青")
	@ApiOperation(value = "分页查询退款记录", notes = "分页查询退款记录。[]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "selectRefundPageByMemberId", method = RequestMethod.GET)
	public Result<Page<ShoppingOrderItemRefundDTO>> selectRefundPageByMemberId(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(name = "param", value = "查询参数") ShoppingRefundQueryForeignParam param) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result<Page<ShoppingOrderItemRefundDTO>> result = shoppingOrderService.selectRefundPageByMemberId(memberId, param);
		if (!isSuccess(result)) {
			return successGet(result.getRet());
		}
		return successGet(result.getModel());
	}

	@SuppressWarnings("unchecked")
	@Audit(date = "2017-05-05", reviewer = "孙林青")
	@ApiOperation(value = "待支付订单支付", notes = "用于对已生成但未支付的订单进行支付。[]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "orderPayment/{id}", method = RequestMethod.GET)
	public Result<ShoppingOrderPaymentForeignDTO> orderPayment(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(name = "id", value = "购物订单id") Long id) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result<ShoppingOrderPaymentDTO> result = shoppingOrderService.orderPayment(id, memberId);
		if (!isSuccess(result)) {
			return successGet(result);
		}
		String memberNum = UserUtil.getCurrentUserNum(getRequest());
		Result<PropertyBalanceDTO> propertyBalanceDTOResult = propertyInfoService.getPropertyBalance(memberNum);
		if (!isSuccess(propertyBalanceDTOResult)) {
			return successGet(propertyBalanceDTOResult.getRet());
		}
		ShoppingOrderPaymentForeignDTO shoppingOrderPaymentForeignDTO = new ShoppingOrderPaymentForeignDTO();
		shoppingOrderPaymentForeignDTO.setId(result.getModel().getId());
		shoppingOrderPaymentForeignDTO.setOrderNum(result.getModel().getOrderNum());
		shoppingOrderPaymentForeignDTO.setOrderTotalPrice(result.getModel().getOrderTotalPrice());
		shoppingOrderPaymentForeignDTO.setBalance(propertyBalanceDTOResult.getModel().getBalance());
		return successGet(shoppingOrderPaymentForeignDTO);
	}

	@Audit(date = "2018-04-27", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "查询订单状态", notes = "查询订单状态[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "orderStatus/{id}", method = RequestMethod.GET)
    public Result<ShoppingOrderStatusDTO> orderStatus(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
            @PathVariable("id") @ApiParam(name = "id", value = "购物订单id") Long id) {
        Long memberId = UserUtil.getCurrentUserId(getRequest());
        Result<ShoppingOrderStatusDTO> result = shoppingOrderService.orderStatus(id, memberId);
        return successGet(result);
    }

}

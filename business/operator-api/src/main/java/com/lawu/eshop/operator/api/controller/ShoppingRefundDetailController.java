package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.LogService;
import com.lawu.eshop.operator.api.service.ShoppingRefundDetailService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.order.dto.foreign.ShoppingRefundDetailDTO;
import com.lawu.eshop.order.param.foreign.ShoppingRefundDetailAgreeToRefundForeignParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

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

	@Autowired
	private ShoppingRefundDetailService shoppingRefundDetailservice;

	@Autowired
	private LogService logService;

	/**
	 * 退款给买家
	 * 
	 * @param id
	 *            退款详情id
	 * @return
	 */
	 @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.ORDER_REFUNDING_AGREE,idParamName ="id")
	@SuppressWarnings({ "rawtypes" })
	@ApiOperation(value = "退款给买家", notes = "退款给买家。[1002|1003|4011|4013]（蒋鑫俊）", httpMethod = "PUT")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequiresPermissions("refund:refundToUser")
	@RequestMapping(value = "agreeToRefund/{id}", method = RequestMethod.PUT)
	public Result agreeToRefund(@PathVariable("id") @ApiParam(value = "退款详情id") Long id) {
		if (id == null || id <= 0) {
			return successCreated(ResultCode.ID_EMPTY);
		}

		ShoppingRefundDetailAgreeToRefundForeignParam param = new ShoppingRefundDetailAgreeToRefundForeignParam();
		param.setIsAgree(true);

		Result result = shoppingRefundDetailservice.agreeToRefund(id, param);

		if (!isSuccess(result)) {
			return successGet(result.getRet());
		}

		return successCreated(result);
	}

	/**
	 * 买家撤销退款申请
	 * 
	 * @param id
	 *            退款详情id
	 * @return
	 */
	 @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.ORDER_REFUNDING_CANCEL,idParamName ="id")
	@ApiOperation(value = "撤销退款申请", notes = "买家撤销退款申请。[1002|1003|1004|4011|4014]（蒋鑫俊）", httpMethod = "DELETE")
	@ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
	@SuppressWarnings("rawtypes")
	@RequiresPermissions("refund:cancel")
	@RequestMapping(value = "revokeRefundRequest/{id}", method = RequestMethod.DELETE)
	public Result revokeRefundRequest(@PathVariable("id") Long id) {

		Result result = shoppingRefundDetailservice.revokeRefundRequest(id);

		if (!isSuccess(result)) {
			return successCreated(result.getRet());
		}

		return successDelete();
	}

	/**
	 * 查看退款申请
	 * 
	 * @param id
	 *            购物订单项id
	 * @return
	 * @author Sunny
	 * @date 2017年6月29日
	 */
	@ApiOperation(value = "查看退款详情", notes = "查看退款详情。[]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@SuppressWarnings({ "unchecked" })
	@RequiresPermissions("refund:detail")
	@RequestMapping(value = "{shoppingOrderItemId}", method = RequestMethod.GET)
	public Result<ShoppingRefundDetailDTO> get(@PathVariable("shoppingOrderItemId") Long shoppingOrderItemId) {
		Result<ShoppingRefundDetailDTO> result = shoppingRefundDetailservice.getRefundDetail(shoppingOrderItemId);
		return successGet(result);
	}
}

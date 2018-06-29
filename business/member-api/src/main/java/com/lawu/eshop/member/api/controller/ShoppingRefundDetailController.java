package com.lawu.eshop.member.api.controller;

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
import com.lawu.eshop.member.api.service.ExpressCompanyService;
import com.lawu.eshop.member.api.service.ShoppingRefundDetailService;
import com.lawu.eshop.order.dto.foreign.RefundInformationDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingRefundDetailDTO;
import com.lawu.eshop.order.param.ShoppingRefundDetailLogisticsInformationParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundDetailLogisticsInformationForeignParam;
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
 * @date 2017/04/06
 */
@Api(tags = "shoppingRefundDetail")
@RestController
@RequestMapping(value = "shoppingRefundDetail/")
public class ShoppingRefundDetailController extends BaseController {

	@Autowired
	private ShoppingRefundDetailService shoppingRefundDetailservice;
	
	@Autowired
	private ExpressCompanyService expressCompanyService;
	
	@Audit(date = "2017-04-15", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询退款详情", notes = "根据购物订单项id查询退款详情。[1100|1024]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getRefundDetail/{shoppingOrderItemId}", method = RequestMethod.GET)
    public Result<ShoppingRefundDetailDTO> getRefundDetail(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("shoppingOrderItemId") @ApiParam(name = "shoppingOrderItemId", value = "购物订单项id") Long shoppingOrderItemId) {
    	Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result<ShoppingRefundDetailDTO> result = shoppingRefundDetailservice.getRefundDetail(shoppingOrderItemId, memberId);
    	return successCreated(result);
    }
	
	@Audit(date = "2017-04-12", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "更新退货物流", notes = "根据购物退款详情更新退货物流信息。[1004|1100|1024|4023]（蒋鑫俊）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
	@RequestMapping(value = "fillLogisticsInformation/{id}", method = RequestMethod.PUT)
	public Result fillLogisticsInformation(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(name = "id", value = "购物退款详情id", required = true) Long id, @ModelAttribute @ApiParam(name = "param", value = "退货物流参数") @Validated ShoppingRefundDetailLogisticsInformationForeignParam param, BindingResult bindingResult) {
		String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
		Result<ExpressCompanyDTO> resultExpressCompanyDTO = expressCompanyService.get(param.getExpressCompanyId());
		if (!isSuccess(resultExpressCompanyDTO)) {
			return successGet(resultExpressCompanyDTO.getRet());
		}
		ShoppingRefundDetailLogisticsInformationParam shoppingRefundDetailLogisticsInformationParam = new ShoppingRefundDetailLogisticsInformationParam();
		shoppingRefundDetailLogisticsInformationParam.setWaybillNum(param.getWaybillNum());
		shoppingRefundDetailLogisticsInformationParam.setExpressCompanyId(resultExpressCompanyDTO.getModel().getId());
		shoppingRefundDetailLogisticsInformationParam.setExpressCompanyCode(resultExpressCompanyDTO.getModel().getCode());
		shoppingRefundDetailLogisticsInformationParam.setExpressCompanyName(resultExpressCompanyDTO.getModel().getName());
		// 修改订单项的退款状态为待退款状态，更新退款详情的物流信息
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result result = shoppingRefundDetailservice.fillLogisticsInformation(id, memberId, shoppingRefundDetailLogisticsInformationParam);
		return successCreated(result);
	}
	
	@Audit(date = "2017-04-15", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "申请平台介入", notes = "申请平台介入。[1100|1024|4024]（蒋鑫俊）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
	@RequestMapping(value = "platformIntervention/{id}", method = RequestMethod.PUT)
	public Result platformIntervention(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(name = "id", value = "购物退款详情id") Long id) {
		// 买家是否申请平台介入
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result result = shoppingRefundDetailservice.platformIntervention(id, memberId);
		return successCreated(result);
	}
	
	@Audit(date = "2017-04-15", reviewer = "孙林青")
	@ApiOperation(value = "撤销退款申请", notes = "买家撤销退款申请。[1100|1024|4025]（蒋鑫俊）", httpMethod = "DELETE")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @Authorization
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "revokeRefundRequest/{id}", method = RequestMethod.DELETE)
	public Result revokeRefundRequest(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") Long id) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result result = shoppingRefundDetailservice.revokeRefundRequest(id, memberId);
		if (!isSuccess(result)) {
			return successCreated(result);
		}
		return successDelete();
	}
	
    /**
     * 获取退款所需要的信息
     * 
     * @param shoppingOrderItemId
     * @param memberId
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月22日
     * @updateDate 2018年3月22日
     */
	@Audit(date = "2018-03-23", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取退款所需要的信息", notes = "获取退款所需要的信息。[1100|1024]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getRefundInformation/{shoppingOrderItemId}", method = RequestMethod.GET)
    public Result<RefundInformationDTO> getRefundInformation(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("shoppingOrderItemId") Long shoppingOrderItemId) {
        Long memberId = UserUtil.getCurrentUserId(getRequest());
        return successGet(shoppingRefundDetailservice.getRefundInformation(shoppingOrderItemId, memberId));
    }
}

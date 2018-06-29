package com.lawu.eshop.merchant.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.constants.WorkOrderTypeEnum;
import com.lawu.eshop.mall.param.WorkOrderParam;
import com.lawu.eshop.merchant.api.service.MerchantStoreService;
import com.lawu.eshop.merchant.api.service.WorkOrderService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
/**
 * 描述：提交工单
 *
 * @author hongqm
 * @date 2017/07/27
 */
@Api(tags = "workOrder", value = "工单接口")
@RestController
@RequestMapping(value = "workOrder/")
public class WorkOrderController extends BaseController{

	@Autowired
	private WorkOrderService workOrderService;

	@Autowired
	private MerchantStoreService merchantStoreService;
	
	@SuppressWarnings("rawtypes")
	@Audit(date = "2017-08-01", reviewer = "孙林青")
	@ApiOperation(value = "商家提交工单", notes = "商家提交工单,[]（洪钦明）", httpMethod = "POST")
	@Authorization
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequestMapping(value = "saveWorkOrder", method = RequestMethod.POST)
	public Result saveWorkOrder(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @RequestParam @ApiParam(name = "content", required = true, value = "工单内容") String content) {
		String account = UserUtil.getCurrentAccount(getRequest());
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		Result<String> name = merchantStoreService.getNameBymerchantId(merchantId);
		WorkOrderParam workOrderParam = new WorkOrderParam();
		workOrderParam.setAccount(account);
		workOrderParam.setContent(content);
		workOrderParam.setUserNum(userNum);
		workOrderParam.setWorkOrderTypeEnum(WorkOrderTypeEnum.MERCHANT);
		workOrderParam.setName("");
		if(name.getModel() != null)
			workOrderParam.setName(name.getModel());
		return workOrderService.saveWorkOrder(workOrderParam);
	}
}

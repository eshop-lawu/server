package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.mall.dto.WorkOrderDTO;
import com.lawu.eshop.mall.param.DealWorkOrderParam;
import com.lawu.eshop.mall.query.WorkOrderQuery;
import com.lawu.eshop.operator.api.service.UserService;
import com.lawu.eshop.operator.api.service.WorkOrderService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.shiro.util.UserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
@Api(tags = "workOrder", value = "工单接口")
@RestController
@RequestMapping(value = "workOrder/")
public class WorkOrderController extends BaseController{

	@Autowired
	private WorkOrderService workOrderService;
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "处理工单", notes = "处理工单,[]（洪钦明）", httpMethod = "PUT")
	@RequiresPermissions("workOrder:edit")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequestMapping(value = "updateWorkOrder", method = RequestMethod.PUT)
	public Result updateWorkOrder(@ModelAttribute DealWorkOrderParam dealWorkOrderParam) {
		dealWorkOrderParam.setAuditorId(userService.getUserByAccount(UserUtil.getCurrentUserAccount()).getModel().getId());
		dealWorkOrderParam.setAuditorName(UserUtil.getCurrentUserAccount());
		return workOrderService.updateWorkOrder(dealWorkOrderParam);
	}
	
	@ApiOperation(value = "查询工单", notes = "查询工单,[]（洪钦明）", httpMethod = "GET")
	@Authorization
	@PageBody
	@RequiresPermissions("workOrder:list")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "selectWorkOrder", method = RequestMethod.GET)
	public Result<Page<WorkOrderDTO>> selectWorkOrder(@ModelAttribute WorkOrderQuery workOrderQuery) {
		return workOrderService.selectWorkOrder(workOrderQuery);
	}
}

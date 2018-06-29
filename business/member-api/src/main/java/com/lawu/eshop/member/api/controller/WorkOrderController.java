package com.lawu.eshop.member.api.controller;

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
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.member.api.service.WorkOrderService;
import com.lawu.eshop.user.dto.UserDTO;
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
public class WorkOrderController extends BaseController {

	@Autowired
	private WorkOrderService workOrderService;

	@Autowired
	private MemberService memberService;
	
	@SuppressWarnings("rawtypes")
	@Audit(date = "2017-08-01", reviewer = "孙林青")
	@ApiOperation(value = "用户提交工单", notes = "用户提交工单,[]（洪钦明）", httpMethod = "POST")
	@Authorization
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequestMapping(value = "saveWorkOrder", method = RequestMethod.POST)
	public Result saveWorkOrder(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @RequestParam @ApiParam(name = "content", required = true, value = "工单内容") String content) {
		String account = UserUtil.getCurrentAccount(getRequest());
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result<UserDTO> result = memberService.findMemberInfo(memberId);
		WorkOrderParam workOrderParam = new WorkOrderParam();
		workOrderParam.setName("");
		if(result.getModel() != null) {
			workOrderParam.setName(result.getModel().getNickname());
		}
		workOrderParam.setWorkOrderTypeEnum(WorkOrderTypeEnum.MEMBER);
		workOrderParam.setAccount(account);
		workOrderParam.setUserNum(userNum);
		workOrderParam.setContent(content);
		return workOrderService.saveWorkOrder(workOrderParam);
	}
	
}

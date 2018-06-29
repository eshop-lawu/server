package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.IncomeSummaryService;
import com.lawu.eshop.property.dto.BroadcastDTO;
import com.lawu.eshop.property.dto.BroadcastListDTO;
import com.lawu.eshop.property.param.BroadcastListParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@Api(tags = "incomeSummary")
@RestController
@RequestMapping(value = "incomeSummary/")
public class IncomeSummaryController extends BaseController {

	@Autowired
	private IncomeSummaryService incomeSummaryService;

	@Audit(date = "2018-03-15", reviewer = "孙林青")
	@ApiOperation(value = "每日收益播报", notes = "每日收益播报[1100]（杨清华）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "getIncomeSummary", method = RequestMethod.GET)
	public Result<BroadcastDTO> getIncomeSummary(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		return incomeSummaryService.getIncomeSummary(userNum);
	}

	@Audit(date = "2018-03-22", reviewer = "孙林青")
	@ApiOperation(value = "播报详情列表", notes = "播报详情列表[]（杨清华）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "getBroadcastList", method = RequestMethod.GET)
	public Result<Page<BroadcastListDTO>> getBroadcastList(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(name = "broadcastListParam", value = "条件") BroadcastListParam broadcastListParam) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		Result<Page<BroadcastListDTO>> rtn = incomeSummaryService.getBroadcastList(userNum,broadcastListParam);
		return successGet(rtn);
	}

}

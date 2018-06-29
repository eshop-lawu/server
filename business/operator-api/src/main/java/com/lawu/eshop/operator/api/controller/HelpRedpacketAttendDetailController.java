package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.eshop.activity.dto.HelpRedpacketAttendDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketAttendPageDTO;
import com.lawu.eshop.activity.param.HelpRedpacketDetailOperatorParam;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.operator.api.service.HelpRedpacketAttendDetailService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 报名活动列表接口
 * @Description
 * @author zhangrc
 * @date 2017年12月28日
 */
@Api(tags = "helpRedpacketAttendDetail", value = "报名列表")
@RestController
@RequestMapping(value = "helpRedpacketAttendDetail/")
public class HelpRedpacketAttendDetailController extends BaseController{
	
	@Autowired
	private HelpRedpacketAttendDetailService helpRedpacketAttendDetailService;
	

	@SuppressWarnings("unchecked")
	@PageBody
	@ApiOperation(value = "报名列表", notes = "报名列表（张荣成）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequiresPermissions("helpRedpacketAttend:list")
	@RequestMapping(value = "helpRedpacketAttendPageOperator", method = RequestMethod.POST)
	public Result<HelpRedpacketAttendPageDTO> helpRedpacketAttendPageOperator(@RequestBody @ApiParam(value = "查询信息") HelpRedpacketDetailOperatorParam detailParam) {
		Result<Page<HelpRedpacketAttendPageDTO>> result = helpRedpacketAttendDetailService.helpRedpacketAttendPageOperator(detailParam);
		return successCreated(result);
	}


}
